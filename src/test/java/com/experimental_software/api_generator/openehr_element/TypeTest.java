package com.experimental_software.api_generator.openehr_element;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

public class TypeTest {

    @Test
    public void testGetTypeName() {
        var t = new Type("HELLO_WORLD");

        var result = t.getName();

        assertThat(result, is("HelloWorld"));
    }

    @Test
    public void testListTypeParameter_01() {
        var t = new Type("List<REVISION_HISTORY_ITEM>");

        var result = t.getName();

        assertThat(result, is("List<RevisionHistoryItem>"));
    }

    @Test
    public void testListTypeParameter_02() {
        var t = new Type("List<String>");

        var result = t.getName();

        assertThat(result, is("List<String>"));
    }

    @Test
    public void testOpenEhrTypeParameter() {
        var t = new Type("DV_INTERVAL<DV_DATE_TIME>");

        var result = t.getName();

        assertThat(result, is("DvInterval<DvDateTime>"));
    }

    @Test
    public void testHashTypeParameter() {
        var t = new Type("Hash<String,RESOURCE_DESCRIPTION_ITEM>");

        var result = t.getName();

        assertThat(result, is("Hash<String,ResourceDescriptionItem>"));
    }
}
