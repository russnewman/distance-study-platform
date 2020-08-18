package com.netcracker.edu.distancestudyplatform.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class ClassTimeDTO {
    private LocalTime startTime;
    private LocalTime endTime;
}
