package com.netcracker.edu.distancestudyplatform.dto.wrappers;

import com.netcracker.edu.distancestudyplatform.dto.EventDto;

import java.util.List;

public class EventPage {
    List<EventDto> page;
    int totalPages;

    public EventPage() {}

    public List<EventDto> getPage() {
        return page;
    }

    public void setPage(List<EventDto> page) {
        this.page = page;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
