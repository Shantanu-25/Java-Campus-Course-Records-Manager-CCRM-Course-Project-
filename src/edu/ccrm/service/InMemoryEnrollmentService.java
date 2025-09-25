package edu.ccrm.service;
import edu.ccrm.domain.Enrollment;
import java.util.*;
public class InMemoryEnrollmentService implements EnrollmentService {
    private final Map<String, Enrollment> map = new LinkedHashMap<>();

    @Override
    public Enrollment enroll(String enrollmentId, String studentId, String courseCode) throws Exception {
        String key = enrollmentId;
        if (map.containsKey(key)) throw new Exception("Duplicate enrollment id");
        Enrollment e = new Enrollment(enrollmentId, studentId, courseCode);
        map.put(key, e);
        return e;
    }

    @Override
    public List<Enrollment> listEnrollments() {
        return new ArrayList<>(map.values());
    }

    @Override
    public void recordMarks(String enrollmentId, int marks) {
        Enrollment e = map.get(enrollmentId);
        if (e != null) e.recordMarks(marks);
    }
}
