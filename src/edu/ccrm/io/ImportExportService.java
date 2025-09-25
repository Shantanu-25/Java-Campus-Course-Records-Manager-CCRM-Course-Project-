package edu.ccrm.io;
import java.nio.file.Path;
public interface ImportExportService {
    void exportData(Path exportDir) throws Exception;
    void importStudents(Path csv) throws Exception;
    void importCourses(Path csv) throws Exception;
}
