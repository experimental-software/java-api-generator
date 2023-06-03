package com.experimental_software.api_generator.intermediate_representation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.Test;

public class TextFileTest {

    @Test
    public void testReadResource() {
        var result = new TextFile("DvDate.json").readAsString();
        assertThat(result, containsString("Numeric value of the dat"));
    }
}
