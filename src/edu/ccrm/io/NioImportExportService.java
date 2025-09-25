package edu.ccrm.io;
import edu.ccrm.service.StudentService;
import edu.ccrm.service.CourseService;
import edu.ccrm.domain.Student;
import edu.ccrm.domain.Course;
import edu.ccrm.domain.Semester;
import java.nio.file.*;
import java.util.stream.*;
import java.util.*;
import java.io.IOException;

public class NioImportExportService implements ImportExportService {
    private final StudentService studentService;
    private final CourseService courseService;

    public NioImportExportService(StudentService ss, CourseService cs) {
        this.studentService = ss;
        this.courseService = cs;
    }

    @Override
    public void importStudents(Path csv) throws IOException {
        try (Stream<String> lines = Files.lines(csv)) {
            lines.filter(l-> !l.isBlank()).forEach(l->{
                String[] parts = l.split(",");
                // id,regNo,fullName,email
                if (parts.length >= 4) {
                    studentService.addStudent(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim());
                }
            });
        }
    }

    @Override
    public void importCourses(Path csv) throws IOException {
        try (Stream<String> lines = Files.lines(csv)) {
            lines.filter(l-> !l.isBlank()).forEach(l->{
                String[] p = l.split(",");
                // code,title,credits,department,semester
                try {
                    var c = new Course.Builder()
                            .code(p[0].trim())
                            .title(p[1].trim())
                            .credits(Integer.parseInt(p[2].trim()))
                            .department(p[3].trim())
                            .semester(Semester.valueOf(p[4].trim().toUpperCase()))
                            .build();
                    courseService.addCourse(c);
                } catch (Exception ex) { /* ignore malformed line */ }
            });
        }
    }

    @Override
    public void exportData(Path exportDir) throws IOException {
        Files.createDirectories(exportDir);
        // simple student export
        Path sFile = exportDir.resolve("students_export.csv");
        List<String> sLines = studentService.listStudents().stream()
            .map(s-> String.join(",", s.getId(), s.getRegNo(), s.getFullName(), s.getEmail()))
            .collect(Collectors.toList());
        Files.write(sFile, sLines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        // courses
        Path cFile = exportDir.resolve("courses_export.csv");
        List<String> cLines = courseService.listCourses().stream()
            .map(c-> String.join(",", c.getCode(), c.getTitle(), String.valueOf(c.getCredits()), c.getDepartment(), c.getSemester().name()))
            .collect(Collectors.toList());
        Files.write(cFile, cLines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}
