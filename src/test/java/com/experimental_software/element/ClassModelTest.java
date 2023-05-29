package com.experimental_software.element;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.experimental_software.api_generator.element.ClassModel;

public class ClassModelTest {

    @Test
    public void testNormalizeClassName() {
        var result = ClassModel.builder()
            .name("VERSIONED_FOLDER")
            .build();
        assertEquals("VersionedFolder", result.getName());
    }
}
