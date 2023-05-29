package com.experimental_software.api_generator.element;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Builder
public class Parameter {

    private final Type type;

    private final String name;
}
