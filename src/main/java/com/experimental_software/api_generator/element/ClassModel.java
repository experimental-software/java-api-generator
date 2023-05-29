package com.experimental_software.api_generator.element;

import java.util.ArrayList;
import java.util.List;

import com.experimental_software.api_generator.util.StringUtils;

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
        return StringUtils.toPascalCase(name);
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
