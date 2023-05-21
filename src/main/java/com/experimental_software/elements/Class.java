package com.experimental_software.elements;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Getter
public class Class {

    private final String name;

    private final String description;

    private final List<Type> baseTypes;

    private final List<Function> functions;

    private final List<Attribute> attributes;
}
