package com.experimental_software.api_generator.code_generation;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Modifier;

import com.experimental_software.api_generator.openehr_element.ClassModel;
import com.experimental_software.api_generator.util.StringUtils;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
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
            var method = MethodSpec.methodBuilder(f.getName())
                .addModifiers(Modifier.PUBLIC)
                .addModifiers(Modifier.ABSTRACT)
                .addJavadoc(f.getDescription())
                .build();
            methodSpecs.add(method);
        }
    }

    private static void readSuperinterfaces(ClassModel classModel, List<ClassName> superinterfaces) {
        for (var t : classModel.getBaseTypes()) {
            try {
                var n = ClassName.bestGuess(t.getName());
                superinterfaces.add(n);
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
                .returns(ClassName.bestGuess(a.getType().getName()))
                .build();
            methodSpecs.add(getter);
        }
    }
}
