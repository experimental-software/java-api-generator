package com.experimental_software.element;

import static com.experimental_software.util.StringUtils.toPascalCase;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Type {

    private final String name;

    public String getName() {
        return toPascalCase(name);
    }
}
