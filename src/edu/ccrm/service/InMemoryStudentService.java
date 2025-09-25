package edu.ccrm.service;
import edu.ccrm.domain.Student;
import java.util.*;
import java.util.stream.Collectors;

public class InMemoryStudentService implements StudentService {
    private final Map<String, Student> map = new LinkedHashMap<>();

    @Override
    public Student addStudent(String id, String regNo, String fullName, String email) {
        Student s = new Student(id, regNo, fullName, email);
        map.put(id, s);
        return s;
    }

    @Override
    public List<Student> listStudents() {
        return new ArrayList<>(map.values());
    }

    @Override
    public Student findById(String id) {
        return map.get(id);
    }

    @Override
    public void deactivate(String id) {
        Student s = map.get(id);
        if (s != null) s.deactivate();
    }
}
