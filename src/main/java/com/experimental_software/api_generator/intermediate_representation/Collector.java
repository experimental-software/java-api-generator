package com.experimental_software.api_generator.intermediate_representation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Collector {

    @Getter
    private final List<IrModel> result = new ArrayList<>();

    public static List<IrModel> findIrModels(File projectRoot) {
        var collector = new Collector();
        collector.collectIrModels(projectRoot);
        return collector.getResult();
    }

    void collectIrModels(File projectRoot) {
        File[] files = projectRoot.listFiles();
        if (files != null) {
            collectIrModels(files);
        }
    }

    void collectIrModels(File[] files) {
        for (var file : files) {
            if (file.isDirectory()) {
                File[] f = file.listFiles();
                if (f != null) {
                    collectIrModels(f);
                }
            } else {
                // FIXME: This won't work on Windows
                if (file.getAbsolutePath().contains("src/main/java") && file.getAbsolutePath().endsWith(".json")) {
                    result.add(IrModel.builder()
                        .path(file)
                        .build());
                }
            }
        }
    }
}
