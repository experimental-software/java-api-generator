package com.experimental_software.api_generator.openehr_element;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Builder
public class Attribute {

    private final Multiplicity multiplicity;

    private final String description;

    private final String name;

    private final Type type;
}
