package com.experimental_software.api_generator.intermediate_representation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

public class CollectorTest {

    @Test
    public void testParsePackageName() {
        var filePath = "../openehr-rm-java/openehr-rm-ehr/src/main/java/org/openehr/rm/ehr/ehr/EhrAccess.json";

        var result = Collector.parsePackageName(filePath);

        assertThat(result, is("org.openehr.rm.ehr.ehr"));
    }
}
