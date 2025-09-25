package edu.ccrm.domain;

import java.util.*;
public class Instructor extends Person {
    private String department;
    private final List<String> courses = new ArrayList<>();

    public Instructor(String id, String fullName, String email, String dept) {
        super(id, fullName, email);
        this.department = dept;
    }

    public String getDepartment() { return department; }
    public void setDepartment(String d) { this.department = d; }
    public void assignCourse(String code) { if (!courses.contains(code)) courses.add(code); }

    @Override
    public String profile() {
        return String.format("Instructor: %s | Dept: %s | Courses: %d", fullName, department, courses.size());
    }
}
