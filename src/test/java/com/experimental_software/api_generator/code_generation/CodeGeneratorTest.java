package com.experimental_software.api_generator.code_generation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.not;

import org.junit.jupiter.api.Test;

import com.experimental_software.api_generator.TestResource;
import com.experimental_software.api_generator.intermediate_representation.ClassModelFactory;

public class CodeGeneratorTest {

    @Test
    public void testGenerateInterfaceWithoutMethods() {
        var json = new TestResource("VersionedFolder.json").readString();
        var classModel = new ClassModelFactory(json).create();

        var result = CodeGenerator.generateInterface("org.openehr.test", classModel).toString();

        assertThat(result, not(emptyString()));
        assertThat(result, containsString("extends VersionedObject"));
    }

    @Test
    public void testGenerateInterfaceWithMethod() {
        var json = new TestResource("DvCount.json").readString();
        var classModel = new ClassModelFactory(json).create();

        var result = CodeGenerator.generateInterface("org.openehr.test", classModel).toString();

        assertThat(result, not(emptyString()));
    }
}
