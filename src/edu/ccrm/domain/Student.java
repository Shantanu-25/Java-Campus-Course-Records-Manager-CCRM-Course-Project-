package edu.ccrm.domain;

import java.util.*;
import java.time.LocalDateTime;
public class Student extends Person {
    private final String regNo;
    private final List<String> enrolledCourseCodes = new ArrayList<>();
    private LocalDateTime lastUpdated;
    private double gpa = 0.0;

    public Student(String id, String regNo, String fullName, String email) {
        super(id, fullName, email);
        this.regNo = regNo;
        this.lastUpdated = LocalDateTime.now();
    }

    public String getRegNo() { return regNo; }
    public List<String> getEnrolledCourseCodes() { return Collections.unmodifiableList(enrolledCourseCodes); }

    public void enroll(String courseCode) {
        if (!enrolledCourseCodes.contains(courseCode)) {
            enrolledCourseCodes.add(courseCode);
            lastUpdated = LocalDateTime.now();
        }
    }
    public void unenroll(String courseCode) {
        enrolledCourseCodes.remove(courseCode);
        lastUpdated = LocalDateTime.now();
    }

    public void setGpa(double gpa) { this.gpa = gpa; }
    public double getGpa() { return gpa; }

    @Override
    public String profile() {
        return String.format("Student: %s | Reg: %s | GPA: %.2f | Courses: %d", fullName, regNo, gpa, enrolledCourseCodes.size());
    }

    @Override
    public String toString() {
        return String.format("Student[id=%s, regNo=%s, name=%s, email=%s, gpa=%.2f]", id, regNo, fullName, email, gpa);
    }
}
