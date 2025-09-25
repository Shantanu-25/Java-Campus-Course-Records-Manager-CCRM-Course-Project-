package edu.ccrm.domain;
import java.time.LocalDateTime;

public class Enrollment {
    private final String id;
    private final String studentId;
    private final String courseCode;
    private int marks = -1;
    private Grade grade = Grade.F;
    private final LocalDateTime enrolledAt;

    public Enrollment(String id, String studentId, String courseCode) {
        this.id = id; this.studentId = studentId; this.courseCode = courseCode; this.enrolledAt = LocalDateTime.now();
    }

    public String getId() { return id; }
    public String getStudentId() { return studentId; }
    public String getCourseCode() { return courseCode; }
    public int getMarks() { return marks; }
    public Grade getGrade() { return grade; }

    public void recordMarks(int marks) {
        this.marks = marks;
        this.grade = Grade.fromMarks(marks);
    }

    @Override
    public String toString() {
        return String.format("Enrollment[%s: student=%s, course=%s, marks=%d, grade=%s]", id, studentId, courseCode, marks, grade);
    }
}
