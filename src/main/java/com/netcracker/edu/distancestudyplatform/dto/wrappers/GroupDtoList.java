package com.netcracker.edu.distancestudyplatform.dto.wrappers;


import com.netcracker.edu.distancestudyplatform.dto.GroupDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class GroupDtoList implements Serializable {
    private List<GroupDto> groups;
}

