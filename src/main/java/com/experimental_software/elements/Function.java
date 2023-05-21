package com.experimental_software.elements;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Function {

    private final String description;

    private final List<Parameter> parameters;

    private final String name;

    private final Type returnType;
}
