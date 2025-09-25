package edu.ccrm.service;
import edu.ccrm.domain.Course;
import java.util.*;
public class InMemoryCourseService implements CourseService {
    private final Map<String, Course> courses = new LinkedHashMap<>();

    @Override
    public Course addCourse(Course course) {
        courses.put(course.getCode(), course);
        return course;
    }

    @Override
    public List<Course> listCourses() {
        return new ArrayList<>(courses.values());
    }

    @Override
    public Course findByCode(String code) {
        return courses.get(code);
    }
}
