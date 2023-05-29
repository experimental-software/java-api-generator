package com.experimental_software.api_generator.element;

import com.experimental_software.api_generator.util.StringUtils;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Type {

    private final String name;

    public String getName() {
        return StringUtils.toPascalCase(name);
    }
}
