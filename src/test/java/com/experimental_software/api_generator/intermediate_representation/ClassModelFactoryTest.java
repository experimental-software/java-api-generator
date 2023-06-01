package com.experimental_software.api_generator.intermediate_representation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import com.experimental_software.TestResource;
import com.experimental_software.api_generator.element.Type;
import com.experimental_software.api_generator.intermediate_representation.ClassModelFactory;

public class ClassModelFactoryTest {

    @Test
    public void testLoadSimpleClass() {
        var json = new TestResource("VersionedFolder.json").readString();

        var result = new ClassModelFactory(json).create();

        assertThat(result.getName(), is("VersionedFolder"));
        assertThat(result.getDescription(), containsString("A version-controlled hierarchy"));
    }

    @Test
    public void testLoadClassWithAttributes() {
        var json = new TestResource("Contribution.json").readString();

        var result = new ClassModelFactory(json).create();

        assertThat(result.getAttributes().size(), is(3));
    }

    @Test
    public void testLoadClassWithoutAttributes() {
        var json = new TestResource("MeasurementService.json").readString();

        var result = new ClassModelFactory(json).create();

        assertThat(result.getAttributes().size(), is(0));
    }

    @Test
    public void testLoadClassWithFunctions() {
        var json = new TestResource("DvCount.json").readString();

        var result = new ClassModelFactory(json).create();

        assertThat(result.getFunctions().size(), is(5));
        assertThat(result.getFunctions().get(0).getDescription(), containsString("Sum of this `DV_COUNT` and `_other_`."));
        assertThat(result.getFunctions().get(0).getParameters().size(), is(1));
    }

    @Test
    public void testLoadClassWithBaseType() {
        var json = new TestResource("VersionedFolder.json").readString();

        var result = new ClassModelFactory(json).create();

        assertThat(result.getBaseTypes().size(), is(1));
        assertThat(result.getBaseTypes(), contains(new Type("VERSIONED_OBJECT")));
    }

    @Test
    public void testLoadClassWithMultipleBaseTypes() {
        var json = new TestResource("DvDate.json").readString();

        var result = new ClassModelFactory(json).create();

        assertThat(result.getBaseTypes().size(), is(2));
        assertThat(result.getBaseTypes(), containsInAnyOrder(new Type("DV_TEMPORAL"), new Type("Iso8601_date")));
    }
}
