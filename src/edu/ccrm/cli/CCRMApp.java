package edu.ccrm.cli;
import edu.ccrm.config.AppConfig;
import edu.ccrm.service.*;
import edu.ccrm.service.InMemoryStudentService;
import edu.ccrm.service.InMemoryCourseService;
import edu.ccrm.service.InMemoryEnrollmentService;
import edu.ccrm.io.*;
import edu.ccrm.domain.*;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class CCRMApp {
    private final AppConfig cfg = AppConfig.getInstance();
    private final StudentService studentService = new InMemoryStudentService();
    private final CourseService courseService = new InMemoryCourseService();
    private final EnrollmentService enrollmentService = new InMemoryEnrollmentService();
    private final ImportExportService ioService = new NioImportExportService(studentService, courseService);
    private final BackupService backupService = new BackupService();
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        new CCRMApp().run();
    }

    public void run() throws Exception {
        seedDemoData();
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> manageStudents();
                case "2" -> manageCourses();
                case "3" -> manageEnrollment();
                case "4" -> importExport();
                case "5" -> backupData();
                case "6" -> reports();
                case "0" -> { System.out.println("Exiting..."); running = false; }
                default -> System.out.println("Unknown option");
            }
        }
    }

    private void printMenu() {
        System.out.println("=== CCRM Menu ===");
        System.out.println("1. Manage Students");
        System.out.println("2. Manage Courses");
        System.out.println("3. Enrollment & Grades");
        System.out.println("4. Import/Export Data");
        System.out.println("5. Backup Data");
        System.out.println("6. Reports");
        System.out.println("0. Exit");
        System.out.print("Choose: ");
    }

    private void manageStudents() {
        System.out.println("-- Students --");
        System.out.println("a. List students\nb. Add student\nc. Deactivate student\nChoose: ");
        String ch = scanner.nextLine().trim();
        switch (ch) {
            case "a" -> {
                studentService.listStudents().forEach(s-> System.out.println(s.profile()));
            }
            case "b" -> {
                System.out.print("id: "); String id = scanner.nextLine().trim();
                System.out.print("regNo: "); String reg = scanner.nextLine().trim();
                System.out.print("name: "); String name = scanner.nextLine().trim();
                System.out.print("email: "); String email = scanner.nextLine().trim();
                studentService.addStudent(id, reg, name, email);
                System.out.println("Added.");
            }
            case "c" -> {
                System.out.print("student id: "); String id = scanner.nextLine().trim();
                studentService.deactivate(id);
                System.out.println("Deactivated (if existed).");
            }
            default -> System.out.println("Unknown");
        }
    }

    private void manageCourses() {
        System.out.println("-- Courses --");
        System.out.println("a. List courses\nb. Add course\nChoose: ");
        String ch = scanner.nextLine().trim();
        switch (ch) {
            case "a" -> courseService.listCourses().forEach(System.out::println);
            case "b" -> {
                System.out.print("code: "); String code = scanner.nextLine().trim();
                System.out.print("title: "); String title = scanner.nextLine().trim();
                System.out.print("credits: "); int credits = Integer.parseInt(scanner.nextLine().trim());
                System.out.print("department: "); String dept = scanner.nextLine().trim();
                System.out.print("semester (SPRING/SUMMER/FALL): "); Semester sem = Semester.valueOf(scanner.nextLine().trim().toUpperCase());
                Course c = new Course.Builder().code(code).title(title).credits(credits).department(dept).semester(sem).build();
                courseService.addCourse(c);
                System.out.println("Added course.");
            }
            default -> System.out.println("Unknown");
        }
    }

    private void manageEnrollment() throws Exception {
        System.out.println("-- Enrollment --");
        System.out.println("a. Enroll\nb. List enrollments\nc. Record marks\nChoose: ");
        String ch = scanner.nextLine().trim();
        switch (ch) {
            case "a" -> {
                System.out.print("enrollmentId: "); String eid = scanner.nextLine().trim();
                System.out.print("studentId: "); String sid = scanner.nextLine().trim();
                System.out.print("courseCode: "); String cc = scanner.nextLine().trim();
                enrollmentService.enroll(eid, sid, cc);
                // attach to student
                var s = studentService.findById(sid);
                if (s != null) s.enroll(cc);
                System.out.println("Enrolled.");
            }
            case "b" -> enrollmentService.listEnrollments().forEach(System.out::println);
            case "c" -> {
                System.out.print("enrollmentId: "); String eid2 = scanner.nextLine().trim();
                System.out.print("marks: "); int marks = Integer.parseInt(scanner.nextLine().trim());
                enrollmentService.recordMarks(eid2, marks);
                System.out.println("Recorded.");
            }
            default -> System.out.println("Unknown");
        }
    }

    private void importExport() {
        System.out.print("import students CSV path (or blank): ");
        String sPath = scanner.nextLine().trim();
        if (!sPath.isBlank()) {
            try { ioService.importStudents(Path.of(sPath)); System.out.println("Imported students."); } catch (Exception e){ System.out.println("Import failed:"+e.getMessage()); }
        }
        System.out.print("import courses CSV path (or blank): ");
        String cPath = scanner.nextLine().trim();
        if (!cPath.isBlank()) {
            try { ioService.importCourses(Path.of(cPath)); System.out.println("Imported courses."); } catch (Exception e){ System.out.println("Import failed:"+e.getMessage()); }
        }
        System.out.print("export to directory (or blank): ");
        String out = scanner.nextLine().trim();
        if (!out.isBlank()) {
            try { ioService.exportData(Path.of(out)); System.out.println("Exported."); } catch (Exception e){ System.out.println("Export failed:"+e.getMessage()); }
        }
    }

    private void backupData() {
        System.out.print("source directory to backup: ");
        String src = scanner.nextLine().trim();
        try {
            var dest = backupService.backupFolder(Path.of(src));
            long size = backupService.sizeRecursive(dest);
            System.out.println("Backup created at: " + dest);
            System.out.println("Total size bytes: " + size);
        } catch (Exception e) { System.out.println("Backup failed:"+e.getMessage()); }
    }

    private void reports() {
        System.out.println("-- Reports --");
        System.out.println("1. Top students by GPA\n2. GPA distribution\nChoose: ");
        String ch = scanner.nextLine().trim();
        switch (ch) {
            case "1" -> {
                var top = studentService.listStudents().stream()
                    .sorted((a,b)-> Double.compare(b.getGpa(), a.getGpa()))
                    .limit(5).collect(Collectors.toList());
                top.forEach(s-> System.out.println(s.profile()));
            }
            case "2" -> {
                var map = studentService.listStudents().stream()
                    .collect(Collectors.groupingBy(s-> {
                        double g = s.getGpa();
                        if (g>=9) return "9+"; if (g>=7) return "7-8"; if (g>0) return "1-6"; return "0";
                    }, Collectors.counting()));
                map.forEach((k,v)-> System.out.println(k+" -> "+v));
            }
            default -> System.out.println("Unknown");
        }
    }

    private void seedDemoData() {
        studentService.addStudent("s1","REG001","Anita Sharma","anita@example.com");
        studentService.addStudent("s2","REG002","Ravi Kumar","ravi@example.com");
        courseService.addCourse(new Course.Builder().code("CS101").title("Intro to CS").credits(4).department("CSE").semester(Semester.SPRING).build());
        courseService.addCourse(new Course.Builder().code("MA101").title("Calculus").credits(3).department("Math").semester(Semester.FALL).build());
    }
}
