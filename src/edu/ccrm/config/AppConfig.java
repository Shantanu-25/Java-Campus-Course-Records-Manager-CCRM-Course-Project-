package edu.ccrm.config;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

// Singleton AppConfig
public final class AppConfig {
    private static AppConfig instance;
    private final Path dataDir;
    private final int maxCreditsPerSemester;

    private AppConfig() {
        this.dataDir = Paths.get(System.getProperty("user.home"), "ccrm-data");
        this.maxCreditsPerSemester = 24;
        try { Files.createDirectories(dataDir); } catch (Exception e) { /* ignore */ }
    }

    public static synchronized AppConfig getInstance() {
        if (instance == null) instance = new AppConfig();
        return instance;
    }

    public Path getDataDir() { return dataDir; }
    public int getMaxCreditsPerSemester() { return maxCreditsPerSemester; }

    public String timestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
    }
}
