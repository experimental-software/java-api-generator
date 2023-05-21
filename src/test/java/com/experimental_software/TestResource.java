package com.experimental_software;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import lombok.SneakyThrows;

public class TestResource {

    private final Path path;

    @SneakyThrows
    public TestResource(String path) {
        var resource = Thread.currentThread().getContextClassLoader().getResource(path);
        if (resource == null) {
            throw new IllegalArgumentException("Resource not found: " + path);
        }
        this.path = Paths.get(resource.toURI());
    }

    @SneakyThrows
    public String readString() {
        return Files.readString(path);
    }
}