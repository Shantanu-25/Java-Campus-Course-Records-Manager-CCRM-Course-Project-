package edu.ccrm.service;
import edu.ccrm.domain.Enrollment;
import java.util.List;

public interface EnrollmentService {
    Enrollment enroll(String enrollmentId, String studentId, String courseCode) throws Exception;
    List<Enrollment> listEnrollments();
    void recordMarks(String enrollmentId, int marks);
}
