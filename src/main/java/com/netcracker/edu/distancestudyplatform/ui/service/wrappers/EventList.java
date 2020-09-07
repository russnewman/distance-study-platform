package com.netcracker.edu.distancestudyplatform.ui.service.wrappers;

import com.netcracker.edu.distancestudyplatform.model.Event;


import java.util.List;

public class EventList {

    public EventList(List<Event> events) {
        this.events = events;
    }

    public EventList() {
    }

    private List<Event> events;

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
