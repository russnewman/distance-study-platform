package com.netcracker.edu.distancestudyplatform.dto.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetStudentEventsResponseDto {
    private List<EventStudentDto> events;
    private int pageCount;
}
