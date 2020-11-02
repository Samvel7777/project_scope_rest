package com.example.projectscoperest.service;

import com.example.projectscoperest.model.Project;
import com.example.projectscoperest.model.User;
import com.example.projectscoperest.repository.ProjectRepository;
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

    public List<Project> findAllProjects(List<Integer> ids) {
        return projectRepository.findAllByIdIn(ids);
    }

    public List<Project> filterByDate(LocalDateTime startDate, LocalDateTime endDate) {
        return projectRepository.findAllByDateBetween(startDate, endDate);
    }

    public Project findByName(String name) {
        return projectRepository.findByName(name);
    }

    public Project findById(int id) {
        return projectRepository.findById(id).orElse(null);
    }

    public void updateProject(Project project) {
        projectRepository.save(project);
    }
}
