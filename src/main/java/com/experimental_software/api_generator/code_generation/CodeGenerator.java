package com.experimental_software.api_generator.code_generation;

import static com.experimental_software.api_generator.code_generation.CodeGenerator.ClassNameUtils.getClassName;
import static com.squareup.javapoet.ClassName.bestGuess;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Modifier;

import com.experimental_software.api_generator.openehr_element.ClassModel;
import com.experimental_software.api_generator.openehr_element.Function;
import com.experimental_software.api_generator.util.StringUtils;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

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
                var type = bestGuess(p.getType().getName());
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
        return bestGuess(function.getReturnType().getName());
    }

    private static void readSuperinterfaces(ClassModel classModel, List<ClassName> superinterfaces) {
        for (var t : classModel.getBaseTypes()) {
            try {
                superinterfaces.add(getClassName(t.getName()));
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
            return new ClassFinder(className).getClassRepresentation()
                .map(ClassName::get)
                .orElse(bestGuess(className));
        }
        // TODO: If running in strict mode, raise exception if class representation could not be found.
    }
}
