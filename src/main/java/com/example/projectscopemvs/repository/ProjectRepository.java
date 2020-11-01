package com.example.projectscopemvs.repository;

import com.example.projectscopemvs.model.Project;
import com.example.projectscopemvs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    List<Project> findAllByDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Project> findAllByTeamLead(User teamLead);

    List<Project> findAllByMembers(User teamLead);

    Project findByName(String name);

    void deleteAllByIdIn(Iterable<Integer> ids);
}
