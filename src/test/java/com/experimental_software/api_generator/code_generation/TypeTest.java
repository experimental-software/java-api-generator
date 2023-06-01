package com.experimental_software.api_generator.code_generation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import com.experimental_software.api_generator.element.Type;

public class TypeTest {

    @Test
    public void testGetTypeName() {
        var t = new Type("HELLO_WORLD");

        var result = t.getName();

        assertThat(result, is("HelloWorld"));
    }
}
