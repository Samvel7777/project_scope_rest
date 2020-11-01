package com.example.projectscopemvs.service;

import com.example.projectscopemvs.model.Project;
import com.example.projectscopemvs.model.User;
import com.example.projectscopemvs.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectService {

    private final ProjectRepository projectRepository;


    public void addProject(Project project) {
        projectRepository.save(project);
    }

    public void delete(List<Integer> ids) {
        projectRepository.deleteAllByIdIn(ids);
    }

    public List<Project> findAllProjects(User teamLead) {
        return projectRepository.findAllByTeamLead(teamLead);
    }

    public List<Project> findAllByMember(User member) {
        return projectRepository.findAllByMembers(member);
    }

    public List<Project> filterByDate(LocalDateTime startDate, LocalDateTime endDate) {
        return projectRepository.findAllByDateBetween(startDate, endDate);
    }

    public Project findByName(String name) {
        return projectRepository.findByName(name);
    }
}
