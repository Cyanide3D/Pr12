package com.example.Pr12;

import lombok.SneakyThrows;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootApplication
public class Pr12Application {

    private static Path firstFilePath;
    private static Path secondFilePath;

    public static void main(String[] args) throws Exception {
        commandLineRunner().run(args);
    }

    private static CommandLineRunner commandLineRunner() {
        return args -> {
            firstFilePath = Path.of(args[0]);
            secondFilePath = Path.of(args[1]);
            SpringApplication.run(Pr12Application.class, args);
        };
    }

    @PostConstruct
    @SneakyThrows
    public void init() {
        String value = "";
        if (Files.isRegularFile(firstFilePath)) {
            value = Files.readString(firstFilePath);
        }

        value = value.isEmpty() ? "null" : value;
        Files.write(secondFilePath, value.getBytes());
    }

    @SneakyThrows
    @PreDestroy
    public void destroy() {
        Files.deleteIfExists(firstFilePath);
    }

}
