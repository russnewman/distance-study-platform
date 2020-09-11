package com.netcracker.edu.distancestudyplatform.service.impl;

import com.netcracker.edu.distancestudyplatform.dto.GroupDto;
import com.netcracker.edu.distancestudyplatform.dto.ScheduleDto;
import com.netcracker.edu.distancestudyplatform.mappers.GroupMapper;
import com.netcracker.edu.distancestudyplatform.model.Group;
import com.netcracker.edu.distancestudyplatform.model.Schedule;
import com.netcracker.edu.distancestudyplatform.repository.GroupRepository;
import com.netcracker.edu.distancestudyplatform.service.GroupService;
import com.netcracker.edu.distancestudyplatform.service.ScheduleService;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class GroupServiceImpl implements GroupService {

    GroupRepository groupRepository;
    ScheduleService scheduleService;

    public GroupServiceImpl(GroupRepository groupRepository, ScheduleService scheduleService) {
        this.groupRepository = groupRepository;
        this.scheduleService = scheduleService;

    }

    @Override
    public Group findById(Long id) {
        return groupRepository.findById(id).orElseGet(Group::new);
    }

    @Override
    public Group findGroupByGroupName(String groupName) {
        return groupRepository.findGroupByGroupName(groupName);
    }


    @Override
    public List<GroupDto> findGroupsByTeacherAndSubject(Long teacherId, String subjectName) {
        List<ScheduleDto> schedules = scheduleService.getSubjectTeacherSchedule(teacherId, subjectName);
        List<GroupDto> groups = new ArrayList<>();
        for (ScheduleDto sch: schedules) {
            if (!groups.contains(sch.getGroupDto()))
                groups.add(sch.getGroupDto());
        }


        return groups;

    }
}
