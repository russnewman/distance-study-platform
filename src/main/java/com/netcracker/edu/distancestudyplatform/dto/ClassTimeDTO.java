package com.netcracker.edu.distancestudyplatform.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class ClassTimeDto {
    private LocalTime startTime;
    private LocalTime endTime;
}
