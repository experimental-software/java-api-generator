package com.experimental_software.api_generator.code_generation;

import org.junit.jupiter.api.Test;
import org.openehr.rm.data_types.quantity.DvCount;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ClassFinderTest {

    @Test
    public void testInferImportPath() {
        var result = new ClassFinder("DvCount").getImport().orElseThrow();

        assertThat(result, is("org.openehr.rm.data_types.quantity.DvCount"));
    }

    @Test
    public void testInferClassObject() {
        var result = new ClassFinder("DvCount").getClassRepresentation().orElseThrow();

        assertThat(result, is(DvCount.class));
    }
}
