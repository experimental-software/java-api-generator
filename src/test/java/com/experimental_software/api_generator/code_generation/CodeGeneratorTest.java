package com.experimental_software.api_generator.code_generation;

import com.experimental_software.api_generator.TestResource;
import com.experimental_software.api_generator.intermediate_representation.ClassModelFactory;
import org.junit.jupiter.api.Test;
import org.openehr.rm.common.change_control.VersionedObject;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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
        assertThat(result, containsString("Boolean lessThan(Object other);"));
    }

    @Test
    public void testTestOpenEhrImports() {
        var json = new TestResource("VersionedFolder.json").readString();
        var classModel = new ClassModelFactory(json).create();

        var result = CodeGenerator.generateInterface("org.openehr.test", classModel).toString();

        var expectedImport = String.format("import %s;", VersionedObject.class.getName());
        assertThat(result, containsString(expectedImport));
    }
}
