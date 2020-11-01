package com.example.projectscopemvs.controller;

import com.example.projectscopemvs.dto.AuthRequest;
import com.example.projectscopemvs.dto.AuthResponse;
import com.example.projectscopemvs.dto.UserDto;
import com.example.projectscopemvs.exceptions.UserNotFoundException;
import com.example.projectscopemvs.model.Type;
import com.example.projectscopemvs.model.User;
import com.example.projectscopemvs.service.UserService;
import com.example.projectscopemvs.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    @Value("${file.upload.dir}")
    private String uploadDir;

    private final JwtTokenUtil tokenUtil;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @PostMapping("/user/add")
    public ResponseEntity<User> registerUser(@RequestBody UserDto userDto)  {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = modelMapper.map(userDto, User.class);
        userService.save(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/user/{userId}/image")
    public ResponseEntity<Optional<User>> uploadImage(@PathVariable("userId") int userId, @RequestParam(value = "image", required = false) MultipartFile file) {
        try {
            Optional<User> byId = userService.findOneById(userId);
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File uploadFile = new File(uploadDir, fileName);
            file.transferTo(uploadFile);
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/user/auth")
    public ResponseEntity auth(@RequestBody AuthRequest authRequest) {
        Optional<User> byEmail = userService.findByEmail(authRequest.login);

        if (byEmail.isPresent()) {
            User user = byEmail.get();
            if (passwordEncoder.matches(authRequest.password, user.getPassword())) {
                String token = tokenUtil.generateToken(user.getEmail());
                return ResponseEntity.ok(AuthResponse.builder()
                        .token(token)
                        .name(user.getName())
                        .surname(user.getSurname())
                        .type(user.getType())
                        .build());
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }

    @GetMapping("user/members")
    public ResponseEntity<List<User>> getMembers() {
        return ResponseEntity.ok(userService.findAllByType(Type.TEAM_MEMBER));
    }
}
