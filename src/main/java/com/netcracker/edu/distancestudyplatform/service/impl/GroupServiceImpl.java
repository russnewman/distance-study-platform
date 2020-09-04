package com.netcracker.edu.distancestudyplatform.service.impl;

import com.netcracker.edu.distancestudyplatform.model.Group;
import com.netcracker.edu.distancestudyplatform.model.Schedule;
import com.netcracker.edu.distancestudyplatform.repository.GroupRepository;
import com.netcracker.edu.distancestudyplatform.service.GroupService;
import com.netcracker.edu.distancestudyplatform.service.ScheduleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


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
    public List<Group> findGroupsByTeacherAndSubject(Long teacherId, String subjectName) {
        List<Schedule> schedules = scheduleService.getSubjectTeacherSchedule(teacherId, subjectName);
        List<Group> groups = new ArrayList<>();
        for (Schedule sch: schedules) {
            if (!groups.contains(sch.getGroup()))
                groups.add(sch.getGroup());
        }
        return groups;
    }
}
