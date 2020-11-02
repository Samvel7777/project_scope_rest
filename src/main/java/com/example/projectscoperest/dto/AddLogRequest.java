package com.example.projectscoperest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddLogRequest {

    private LocalDateTime date;
    private int projectId;
    private double hours;
}
