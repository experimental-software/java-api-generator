package com.experimental_software.api_generator.code_generation;

import com.experimental_software.api_generator.openehr_element.ClassModel;
import com.experimental_software.api_generator.openehr_element.Function;
import com.experimental_software.api_generator.util.StringUtils;
import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.experimental_software.api_generator.code_generation.CodeGenerator.ClassNameUtils.getClassName;

public class CodeGenerator {

    public static JavaFile generateInterface(String packageName, ClassModel classModel) {
        List<MethodSpec> methodSpecs = new ArrayList<>();
        List<ClassName> superinterfaces = new ArrayList<>();

        readAttributes(classModel, methodSpecs);
        readFunctions(classModel, methodSpecs);
        readSuperinterfaces(classModel, superinterfaces);

        var typeSpec = TypeSpec
            .interfaceBuilder(classModel.getName())
            .addSuperinterfaces(superinterfaces)
            .addModifiers(Modifier.PUBLIC)
            .addMethods(methodSpecs)
            .addJavadoc(classModel.getDescription())
            .build();

        return JavaFile.builder(packageName, typeSpec).build();
    }

    private static void readFunctions(ClassModel classModel, List<MethodSpec> methodSpecs) {
        for (var f : classModel.getFunctions()) {

            List<ParameterSpec> parameters = new ArrayList<>();
            for (var p : f.getParameters()) {
                var type = getClassName(p.getType().getName());
                var parameterSpec = ParameterSpec.builder(type, p.getName()).build();
                parameters.add(parameterSpec);
            }

            var method = MethodSpec.methodBuilder(f.getName())
                .addModifiers(Modifier.PUBLIC)
                .addModifiers(Modifier.ABSTRACT)
                .addJavadoc(f.getDescription())
                .returns(getReturnType(f))
                .addParameters(parameters)
                .build();

            methodSpecs.add(method);
        }
    }

    private static TypeName getReturnType(Function function) {
        if (function.getReturnType() == null) {
            return TypeName.VOID;
        }
        return getClassName(function.getReturnType().getName());
    }

    private static void readSuperinterfaces(ClassModel classModel, List<ClassName> superinterfaces) {
        for (var t : classModel.getBaseTypes()) {
            try {
                var className = getClassName(t.getName());
                if (className.simpleName().equals("String") || className.simpleName().equals("Object")) {
                    // TODO: Clarify how to handle base types
                    continue;
                }
                superinterfaces.add(className);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void readAttributes(ClassModel classModel, List<MethodSpec> methodSpecs) {
        for (var a : classModel.getAttributes()) {
            if (a.getName() == null) {
                // TODO: Log warning or throw exception in strict mode
                continue;
            }

            var getter = MethodSpec.methodBuilder("get" + StringUtils.toPascalCase(a.getName()))
                .addModifiers(Modifier.PUBLIC)
                .addModifiers(Modifier.ABSTRACT)
                .addJavadoc(a.getDescription())
                .returns(getClassName(a.getType().getName()))
                .build();
            methodSpecs.add(getter);
        }
    }

    static class ClassNameUtils {
        static ClassName getClassName(String className) {
            if (className.length() == 1) { // Generic type parameters like "T" and "V"
                // TODO Add support for generic types
                return ClassName.get(Object.class);
            }
            if (className.startsWith("List")) {
                return ClassName.get(List.class);
            }
            if (className.startsWith("Hash")) {
                return ClassName.get(Map.class);
            }
            if (className.equals("String")) {
                return ClassName.get(String.class);
            }
            if (className.equals("Boolean")) {
                return ClassName.get(Boolean.class);
            }
            if (className.equals("Any")) {
                return ClassName.get(Object.class);
            }

            return new ClassFinder(className).getClassRepresentation()
                .map(ClassName::get)
                .orElse(ClassName.get(Object.class));
        }
        // TODO: If running in strict mode, raise exception if class representation could not be found.
    }
}
