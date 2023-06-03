package com.experimental_software.api_generator.code_generation;

import java.io.File;
import java.io.FileWriter;

import com.squareup.javapoet.JavaFile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JavaFileWriter {

    private final String path;

    private final JavaFile javaFile;

    public void writeToDisk() {
        File f = new File(path);

        // Reset previously existing file
        if (f.exists()) {
            //noinspection ResultOfMethodCallIgnored
            f.delete();
        }

        // Create new file
        try {
            var s = javaFile.toString();
            FileWriter fw = new FileWriter(f);
            fw.write(s);
            fw.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Check file got created
        if (!f.exists()) {
            throw new RuntimeException("Failed to create file: " + f.getAbsolutePath());
        }
    }
}
