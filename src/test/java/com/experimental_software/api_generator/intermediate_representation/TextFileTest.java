package com.experimental_software.api_generator.intermediate_representation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

import java.io.File;

import org.junit.jupiter.api.Test;

public class TextFileTest {

    @Test
    public void testReadResource() {
        var result = new TextFile(new File("src/test/resources/DvDate.json")).readAsString();
        assertThat(result, containsString("Numeric value of the dat"));
    }
}
