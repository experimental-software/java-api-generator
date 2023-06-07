package com.experimental_software.api_generator.openehr_element;

import static com.experimental_software.api_generator.util.StringUtils.toLowerCamelCase;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

// TODO: Rename to ParameterModel
@RequiredArgsConstructor
@Getter
@Builder
public class Parameter {

    @NonNull
    private final Type type;

    @NonNull
    private final String name;

    public String getName() {
        return toLowerCamelCase(name);
    }
}
