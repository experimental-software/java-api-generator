package com.experimental_software.api_generator.openehr_element;

import static com.experimental_software.api_generator.util.StringUtils.toLowerCamelCase;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

// TODO: Rename to FunctionModel
@RequiredArgsConstructor
@Getter
@Builder
public class Function {

    private final String description;

    private final List<Parameter> parameters;

    @NonNull
    private final String name;

    private final Type returnType;

    public List<Parameter> getParameters() {
        return parameters != null ? parameters : new ArrayList<>();
    }

    public String getName() {
        return toLowerCamelCase(name);
    }
}
