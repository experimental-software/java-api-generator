package com.experimental_software.element;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Builder
public class Function {

    private final String description;

    private final List<Parameter> parameters;

    private final String name;

    private final Type returnType;

    public List<Parameter> getParameters() {
        return parameters != null ? parameters : new ArrayList<>();
    }
}
