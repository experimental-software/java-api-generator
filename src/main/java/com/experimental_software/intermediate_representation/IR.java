package com.experimental_software.intermediate_representation;

import java.util.List;

/**
 * Intermediate representation of the openEHR specs as generated from the ADOC parser.
 */
record IR(
    List<Attribute> attributes,
    List<Function> functions,
    List<Object> constants,
    MetaInfo meta_info
) {}
