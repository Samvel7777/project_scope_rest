package com.example.projectscoperest.repository;

import com.example.projectscoperest.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface LogRepository extends JpaRepository<Log, Integer> {

    @Transactional
    void deleteAllByIdIn(List<Integer> ids);

    List<Log> findAllByMemberId(int memberId);

    List<Log> findAllByProjectId(int projectId);

    List<Log> findAllByMemberIdAndProjectId(int id, int projectId);

}
