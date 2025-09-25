package edu.ccrm.io;
import edu.ccrm.config.AppConfig;
import java.nio.file.*;
import java.io.IOException;
import java.util.stream.Stream;

public class BackupService {
    private final AppConfig cfg = AppConfig.getInstance();

    public Path backupFolder(Path sourceDir) throws IOException {
        String ts = cfg.timestamp();
        Path dest = cfg.getDataDir().resolve("backup_" + ts);
        Files.createDirectories(dest);
        // copy files
        try (Stream<Path> stream = Files.walk(sourceDir)) {
            stream.forEach(p-> {
                try {
                    Path rel = sourceDir.relativize(p);
                    Path target = dest.resolve(rel.toString());
                    if (Files.isDirectory(p)) {
                        Files.createDirectories(target);
                    } else {
                        Files.copy(p, target, StandardCopyOption.REPLACE_EXISTING);
                    }
                } catch (IOException e) { /* ignore per-file errors */ }
            });
        }
        return dest;
    }

    public long sizeRecursive(Path dir) throws IOException {
        try (Stream<Path> s = Files.walk(dir)) {
            return s.filter(Files::isRegularFile).mapToLong(p-> {
                try { return Files.size(p); } catch (IOException e) { return 0L; }
            }).sum();
        }
    }
}
