package com.experimental_software.intermediate_representation;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.experimental_software.element.Attribute;
import com.experimental_software.element.ClassModel;
import com.experimental_software.element.Function;
import com.experimental_software.element.Multiplicity;
import com.experimental_software.element.Parameter;
import com.experimental_software.element.Type;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

@SuppressWarnings("unchecked")
public class ClassModelFactory {

    private final DocumentContext json;

    public ClassModelFactory(String json) {
        this.json = JsonPath.parse(json);
    }

    public ClassModel create() {
        List<String> baseTypesRaw = json.read("$.meta_info.base_types[*]");
        List<Map<String, String>> attributesRaw = json.read("$.attributes[*]");

        return ClassModel.builder()
            .name(json.read("$.meta_info.name"))
            .description(json.read("$.meta_info.description"))
            .baseTypes(baseTypesRaw.stream().map(Type::new).collect(toList()))
            .attributes(parseAttributes(attributesRaw))
            .functions(parseFunctions(json.read("$.functions[*]")))
            .build();
    }

    @SuppressWarnings("rawtypes")
    private static List<Function> parseFunctions(List functionsRaw) {
        List<Function> result = new ArrayList<>();
        for (int i = 0; i < functionsRaw.size(); i++) {
            var element = (Map) functionsRaw.get(i);
            var description = element.get("meaning").toString();
            var name = element.get("name").toString();
            var returnType = new Type(element.get("return_type").toString());

            List<Parameter> parameters = new ArrayList<>();
            var parametersRaw = (List) element.get("parameters");
            parametersRaw.forEach(p -> {
                var parameterRaw = (Map) p;
                parameters.add(
                  Parameter.builder()
                      .name(parameterRaw.get("name").toString())
                      .type(new Type(parameterRaw.get("name").toString()))
                      .build()
                );
            });

            result.add(
                Function.builder()
                    .name(name)
                    .description(description)
                    .returnType(returnType)
                    .parameters(parameters)
                    .build()
            );
        }
        return result;
    }

    private static List<Attribute> parseAttributes(List<Map<String, String>> attributesRaw) {
        List<Attribute> result = new ArrayList<>();
        for (var attributeRaw : attributesRaw) {
            result.add(
                Attribute.builder()
                    .description(attributeRaw.get("description"))
                    .name(attributeRaw.get("name"))
                    .multiplicity(parseMultiplicity(attributeRaw.get("multiplicity")))
                    .type(new Type(attributeRaw.get("type")))
                    .build()
            );
        }
        return result;
    }

    private static Multiplicity parseMultiplicity(String multiplicityRaw) {
        if (multiplicityRaw == null) {
            return Multiplicity.ONE;
        }

        return switch (multiplicityRaw) {
            case "0..1" -> Multiplicity.ZERO_OR_ONE;
            case "1..1" -> Multiplicity.ONE;
            case "0..*" -> Multiplicity.MANY;
            default -> throw new IllegalArgumentException("Unknown multiplicity: " + multiplicityRaw);
        };
    }
}
