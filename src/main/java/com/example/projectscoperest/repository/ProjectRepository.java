package com.example.projectscoperest.repository;

import com.example.projectscoperest.model.Project;
import com.example.projectscoperest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    List<Project> findAllByDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Project> findAllByMembers(User teamLead);

    Project findByName(String name);

    @Transactional
    void deleteAllByIdIn(Iterable<Integer> ids);

    List<Project> findAllByIdIn(List<Integer> ids);
}
