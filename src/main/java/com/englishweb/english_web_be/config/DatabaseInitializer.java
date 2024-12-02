package com.englishweb.english_web_be.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class DatabaseInitializer implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;
    private final ApplicationContext applicationContext;

    public DatabaseInitializer(JdbcTemplate jdbcTemplate, ResourceLoader resourceLoader, ApplicationContext applicationContext) {
        this.jdbcTemplate = jdbcTemplate;
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(String... args) throws Exception {
        loadAndExecuteSQLFiles();
    }

    private void loadAndExecuteSQLFiles() {
        String migrationFolderPath = "classpath:db/migration/";

        try {
            Resource[] resources = applicationContext.getResources(migrationFolderPath + "*.sql");

            for (Resource resource : resources) {
                if (resource.exists()) {
                    executeSQLFromFile(resource);
                }
            }
        } catch (Exception e) {
            log.error("Error loading or executing migration files: {}", e.getMessage());
        }
    }

    private void executeSQLFromFile(Resource resource) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder sql = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                sql.append(line).append("\n");
            }
            jdbcTemplate.execute(sql.toString());
            log.info("Successfully executed: {}", resource.getFilename());
        } catch (Exception e) {
            log.error("Error executing file {}: {}", resource.getFilename(), e.getMessage());
        }
    }
}
