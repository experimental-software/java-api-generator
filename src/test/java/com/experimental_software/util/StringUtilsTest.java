package com.experimental_software.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @see <a href="https://docs.brickhub.dev/brick-syntax#built-in-lambdas>Built-in Lambdas | docs.brickhub.dev</a>
 */
public class StringUtilsTest {

    @Test
    public void testPascalCaseTransformation() {
        assertEquals("HelloWorld", StringUtils.toPascalCase("HELLO_WORLD"));
        assertEquals("HelloWorld", StringUtils.toPascalCase("HelloWorld"));
        assertEquals("Iso8601Date", StringUtils.toPascalCase("Iso8601_date"));
    }
}
