package com.example.projectscopemvs.controller;

import com.example.projectscopemvs.model.Log;
import com.example.projectscopemvs.model.User;
import com.example.projectscopemvs.service.LogService;
import com.example.projectscopemvs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LogController {


    private final LogService logService;
    private final UserService userService;


    @PostMapping("/log/add")
    public ResponseEntity addLog(@RequestBody Log log) {
        User currentUser = userService.getCurrentUser();
        log.setMemberId(currentUser.getId());
        logService.addLog(log);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/log/delete")
    public void deleteLogs(@RequestBody DeleteRequest request) {
        logService.delete(request.ids);
    }

    @GetMapping("/log/findAll")
    public ResponseEntity<List<Log>> findLogs() {
        User currentUser = userService.getCurrentUser();
        List<Log> logs = logService.findAllByMember(currentUser.getId());
        if (logs!=null){
            return ResponseEntity.ok(logs);
        }
        return ResponseEntity.notFound().build();

    }

}
