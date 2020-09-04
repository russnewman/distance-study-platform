package com.netcracker.edu.distancestudyplatform.ui.service;

import com.netcracker.edu.distancestudyplatform.model.Schedule;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class ScheduleList implements Serializable{
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
