package com.example.projectscopemvs.service;

import com.example.projectscopemvs.model.Log;
import com.example.projectscopemvs.repository.LogRepository;
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
}
