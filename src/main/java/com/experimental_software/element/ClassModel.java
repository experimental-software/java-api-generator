package com.experimental_software.element;

import static com.experimental_software.util.StringExtension.toPascalCase;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Getter
public class ClassModel {

    private final String name;

    private final String description;

    private final List<Type> baseTypes;

    private final List<Function> functions;

    private final List<Attribute> attributes;

    public String getName() {
        return toPascalCase(name);
    }

    public List<Type> getBaseTypes() {
        return baseTypes != null ? baseTypes : new ArrayList<>();
    }

    public List<Function> getFunctions() {
        return functions != null ? functions : new ArrayList<>();
    }

    public List<Attribute> getAttributes() {
        return attributes != null ? attributes : new ArrayList<>();
    }
}
