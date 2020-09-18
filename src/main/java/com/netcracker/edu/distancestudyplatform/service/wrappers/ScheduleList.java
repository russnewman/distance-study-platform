package com.netcracker.edu.distancestudyplatform.service.wrappers;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.netcracker.edu.distancestudyplatform.model.Schedule;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonAutoDetect
public class ScheduleList implements Serializable {
    private List<Schedule> schedules;

    public ScheduleList(){
        schedules = new ArrayList<>();
    }

    public ScheduleList(List<Schedule> schedule) {
        this.schedules = schedule;
    }

    public List<Schedule> getSchedule() {
        return schedules;
    }

    public void setSchedule(List<Schedule> schedule) {
        this.schedules = schedule;
    }
}
