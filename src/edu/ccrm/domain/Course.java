package edu.ccrm.domain;
import java.util.Objects;

// Example of Builder pattern and immutable-ish fields
public class Course {
    private final String code;
    private final String title;
    private final int credits;
    private final String instructorId;
    private final Semester semester;
    private final String department;

    private Course(Builder b) {
        this.code = b.code;
        this.title = b.title;
        this.credits = b.credits;
        this.instructorId = b.instructorId;
        this.semester = b.semester;
        this.department = b.department;
    }

    public String getCode() { return code; }
    public String getTitle() { return title; }
    public int getCredits() { return credits; }
    public String getInstructorId() { return instructorId; }
    public Semester getSemester() { return semester; }
    public String getDepartment() { return department; }

    @Override
    public String toString() {
        return String.format("Course[%s - %s | %dcr | %s | %s]", code, title, credits, department, semester);
    }

    public static class Builder {
        private String code;
        private String title;
        private int credits;
        private String instructorId;
        private Semester semester;
        private String department;

        public Builder code(String code) { this.code = code; return this; }
        public Builder title(String title) { this.title = title; return this; }
        public Builder credits(int c) { this.credits = c; return this; }
        public Builder instructorId(String id) { this.instructorId = id; return this; }
        public Builder semester(Semester s) { this.semester = s; return this; }
        public Builder department(String d) { this.department = d; return this; }

        public Course build() {
            Objects.requireNonNull(code);
            Objects.requireNonNull(title);
            return new Course(this);
        }
    }
}
