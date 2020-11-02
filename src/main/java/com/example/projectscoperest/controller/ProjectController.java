package com.example.projectscoperest.controller;

import com.example.projectscoperest.dto.DeleteRequest;
import com.example.projectscoperest.model.Project;
import com.example.projectscoperest.model.User;
import com.example.projectscoperest.service.ProjectService;
import com.example.projectscoperest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ProjectController {

    private final UserService userService;
    private final ProjectService projectService;

    @PostMapping("/private/project/add")
    @PreAuthorize("hasAuthority('TEAM_LEADER')")
    public ResponseEntity addProject(@RequestBody Project project) {
        User currentUser = userService.getCurrentUser();
        projectService.addProject(project);
        currentUser.getProjects().add(project);
        userService.updateUser(currentUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/private/project/attachProject")
    @PreAuthorize("hasAuthority('TEAM_LEADER')")
    public ResponseEntity attachProjectToMember(@RequestParam(value = "projectId") int projectId, @RequestParam(value = "memberId") int memberId) {
        Optional<User> currentUser = userService.findOneById(memberId);
        if (currentUser.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        User user = currentUser.get();
        Project project = projectService.findById(projectId);
        project.getMembers().add(user.getName());
        projectService.updateProject(project);
        user.getProjects().add(project);
        userService.updateUser(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/private/project/delete")
    public ResponseEntity deleteProject(@RequestBody DeleteRequest request) {
        projectService.delete(request.ids);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/private/project/findAll")
    public List<Project> findTeamLeadProject() {
        User currentUser = userService.getCurrentUser();
        List<Integer> projectsIds = currentUser.getProjects()
                .stream()
                .map(Project::getId)
                .collect(Collectors.toList());
        return projectService.findAllProjects(projectsIds);
    }

    @GetMapping("/private/project/findByName")
    public Project findByName(@RequestParam(value = "name") String name) {
        return projectService.findByName(name);
    }

    @GetMapping("/private/project/filterByDate")
    public ResponseEntity<List<Project>> filterByDate(@RequestParam(value = "startDate") String startDate, @RequestParam(value = "endDate") String endDate) {
        List<Project> projects = projectService.filterByDate(LocalDateTime.parse(startDate), LocalDateTime.parse(endDate));
        if (projects != null && !projects.isEmpty()) {
            return ResponseEntity.ok(projects);
        }
        return ResponseEntity.notFound().build();
    }
}
