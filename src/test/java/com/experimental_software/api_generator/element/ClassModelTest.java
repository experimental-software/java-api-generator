package com.experimental_software.api_generator.element;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ClassModelTest {

    @Test
    public void testNormalizeClassName() {
        var result = ClassModel.builder()
            .name("VERSIONED_FOLDER")
            .build();
        assertEquals("VersionedFolder", result.getName());
    }
}
