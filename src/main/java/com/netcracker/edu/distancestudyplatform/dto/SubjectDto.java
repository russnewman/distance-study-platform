package com.netcracker.edu.distancestudyplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDto{
    private Long id;
    private String name;
    private String description;
}
