package com.experimental_software.elements;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Parameter {

    private final Type type;

    private final String name;
}
