package com.experimental_software.api_generator.openehr_element;

import com.experimental_software.api_generator.util.StringUtils;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Type {

    @NonNull
    private final String name;

    public String getName() {
        return StringUtils.toPascalCase(name);
    }
}
