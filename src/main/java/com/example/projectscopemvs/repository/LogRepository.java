package com.example.projectscopemvs.repository;

import com.example.projectscopemvs.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogRepository extends JpaRepository<Log, Integer> {

    void deleteAllByIdIn(List<Integer> ids);

    List<Log> findAllByMemberId(int memberId);
}
