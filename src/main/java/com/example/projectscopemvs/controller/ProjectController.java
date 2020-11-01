package com.example.projectscopemvs.controller;

import com.example.projectscopemvs.model.Project;
import com.example.projectscopemvs.service.ProjectService;
import com.example.projectscopemvs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProjectController {


    private final UserService userService;
    private final ProjectService projectService;

    @PostMapping("/project/add")
    public ResponseEntity addProject(@RequestBody Project project) {
        project.setTeamLead(userService.getCurrentUser());
        projectService.addProject(project);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/project/delete")
    public ResponseEntity deleteProject(@RequestBody DeleteRequest request) {
        projectService.delete(request.ids);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/project/findAll")
    public List<Project> findTeamLeadProject() {
        return projectService.findAllProjects(userService.getCurrentUser());
    }

    @GetMapping("/project/member/getAll")
    public List<Project> getMemberProjects() {
        return projectService.findAllByMember(userService.getCurrentUser());
    }

    @GetMapping("/project/findName")
    public Project findByName(@RequestParam(value = "name") String name) {
        return projectService.findByName(name);
    }

    @GetMapping("/project/filterByDate")
    public ResponseEntity<List<Project>> filterByDate(@RequestParam(value = "startDate") String startDate, @RequestParam(value = "endDate") String endDate) {
        List<Project> projects = projectService.filterByDate(LocalDateTime.parse(startDate), LocalDateTime.parse(endDate));
        if (projects != null && !projects.isEmpty()) {
            return ResponseEntity.ok(projects);
        }
        return ResponseEntity.notFound().build();
    }
}
