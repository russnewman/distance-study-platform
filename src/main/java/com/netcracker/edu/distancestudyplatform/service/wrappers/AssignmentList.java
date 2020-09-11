package com.netcracker.edu.distancestudyplatform.service.wrappers;


import com.netcracker.edu.distancestudyplatform.model.Assignment;

import java.util.List;


public class AssignmentList {
    public AssignmentList(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    public AssignmentList() {}

    private List<Assignment> assignments;

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignment) {
        this.assignments = assignments;
    }
}
