package com.example.projectscopemvs.service;

import com.example.projectscopemvs.model.Type;
import com.example.projectscopemvs.model.User;
import com.example.projectscopemvs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        try {
            return userRepository.findByEmail(email);
        } catch (EntityNotFoundException e) {
            log.error("User with {} dose not exists", email);
            return Optional.empty();
        }
    }

    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public List<User> findAllByType(Type type) {
        return userRepository.findAllByType(type);
    }

    public Optional<User> findOneById(int userId) {
        return userRepository.findById(userId);
    }
}
