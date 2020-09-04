package com.netcracker.edu.distancestudyplatform.service;

import com.netcracker.edu.distancestudyplatform.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class SubjectList {
    private List<Subject> subjects;

    public SubjectList(){
        this.subjects = new ArrayList<>();
    }

    public SubjectList(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
