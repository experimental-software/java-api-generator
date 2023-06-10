package com.experimental_software.api_generator.code_generation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;
import org.openehr.rm_data_types.quantity.DvCount;

public class ClassFinderTest {

    @Test
    public void testInferImportPath() {
        var result = new ClassFinder("DvCount").getImport();

        assertThat(result, is("org.openehr.rm_data_types.quantity.DvCount"));
    }

    @Test
    public void testInferClassObject() {
        var result = new ClassFinder("DvCount").getClassRepresentation().orElseThrow();

        assertThat(result, is(DvCount.class));
    }
}
