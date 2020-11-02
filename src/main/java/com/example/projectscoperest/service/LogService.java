package com.example.projectscoperest.service;

import com.example.projectscoperest.model.Log;
import com.example.projectscoperest.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LogService {

    private final LogRepository logRepository;


    public void addLog(Log log) {
        logRepository.save(log);
    }

    public void delete(List<Integer> ids) {
        logRepository.deleteAllByIdIn(ids);
    }

    public List<Log> findAllByMember(int memberId) {
        return logRepository.findAllByMemberId(memberId);
    }

    public List<Log> findAllByProject(int projectId) {
        return logRepository.findAllByProjectId(projectId);
    }

    public List<Log> findAllByMemberAndProject(int id, int projectId) {
        return logRepository.findAllByMemberIdAndProjectId(id,projectId);
    }
}

