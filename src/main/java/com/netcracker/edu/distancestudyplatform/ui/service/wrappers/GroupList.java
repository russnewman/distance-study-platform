package com.netcracker.edu.distancestudyplatform.ui.service.wrappers;

import com.netcracker.edu.distancestudyplatform.model.Group;
import java.util.List;

public class GroupList {


    public GroupList(List<Group> groups) {
        this.groups = groups;
    }


    public GroupList() {
    }

    private List<Group> groups;

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroup(List<Group> groups) {
        this.groups = groups;
    }
}
