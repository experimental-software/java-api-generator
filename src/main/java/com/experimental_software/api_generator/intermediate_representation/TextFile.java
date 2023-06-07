package com.experimental_software.api_generator.intermediate_representation;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

class TextFile {

    private final InputStream inputStream;

    public TextFile(File file) {
        try {
            inputStream = file.toURI().toURL().openStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (inputStream == null) {
            throw new RuntimeException("File not found: " + file.getAbsolutePath());
        }
    }

    public String readAsString() {
        try {
            return new String(inputStream.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
