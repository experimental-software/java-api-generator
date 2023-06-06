package com.experimental_software.api_generator.openehr_element;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

public class FunctionTest {

    @Test
    public void testNormalizeMethodName() {
        var f = Function.builder()
            .name("most_recent_version")
            .build();

        var result = f.getName();

        assertThat(result, is("mostRecentVersion"));
    }
}
