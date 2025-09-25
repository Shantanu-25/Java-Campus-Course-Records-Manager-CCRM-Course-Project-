package edu.ccrm.service;
import edu.ccrm.domain.Student;
import java.util.List;
public interface StudentService {
    Student addStudent(String id, String regNo, String fullName, String email);
    List<Student> listStudents();
    Student findById(String id);
    void deactivate(String id);
}
