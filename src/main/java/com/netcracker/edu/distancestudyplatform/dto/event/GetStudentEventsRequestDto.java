package com.netcracker.edu.distancestudyplatform.dto.event;

import lombok.Data;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;

@Data
public class GetStudentEventsRequestDto {
    private @NotNull Long studentId;
    private Long subjectId;
    private Pageable pageable;

}
