package com.example.projectscoperest.controller;

import com.example.projectscoperest.dto.AddLogRequest;
import com.example.projectscoperest.dto.DeleteRequest;
import com.example.projectscoperest.model.Log;
import com.example.projectscoperest.model.User;
import com.example.projectscoperest.service.LogService;
import com.example.projectscoperest.service.ProjectService;
import com.example.projectscoperest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LogController {

    private final LogService logService;
    private final UserService userService;
    private final ProjectService projectService;


    @PostMapping("/private/log/add")
    public ResponseEntity addLog(@RequestBody AddLogRequest logRequest) {
        User currentUser = userService.getCurrentUser();
        Log log = new Log();
        log.setMemberId(currentUser.getId());
        log.setDate(logRequest.getDate());
        log.setHours(logRequest.getHours());
        log.setProject(projectService.findById(logRequest.getProjectId()));
        logService.addLog(log);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/private/log/delete")
    public void deleteLogs(@RequestBody DeleteRequest request) {
        logService.delete(request.ids);
    }

    @GetMapping("/private/log/findAll")
    public ResponseEntity<List<Log>> findLogs() {
        User currentUser = userService.getCurrentUser();
        List<Log> logs = logService.findAllByMember(currentUser.getId());
        if (logs != null) {
            return ResponseEntity.ok(logs);
        }
        return ResponseEntity.notFound().build();

    }

    @GetMapping("/private/log/findByProject")
    public ResponseEntity<List<Log>> findLogs(@RequestParam(value = "projectId") int projectId) {
        User currentUser = userService.getCurrentUser();
        List<Log> logs = logService.findAllByMemberAndProject(currentUser.getId(), projectId);
        if (logs != null) {
            return ResponseEntity.ok(logs);
        }
        return ResponseEntity.notFound().build();

    }
}
