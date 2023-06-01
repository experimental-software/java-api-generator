package com.experimental_software.api_generator.code_generation;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Modifier;

import com.experimental_software.api_generator.util.StringUtils;
import com.experimental_software.api_generator.element.ClassModel;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

public class CodeGenerator {

    public static String generateInterface(String packageName, ClassModel classModel) {
        List<MethodSpec> methodSpecs = new ArrayList<>();

        for (var a : classModel.getAttributes()) {
            var getter = MethodSpec.methodBuilder("get" + StringUtils.toPascalCase(a.getName()))
                .addModifiers(Modifier.PUBLIC)
                .addModifiers(Modifier.ABSTRACT)
                .addJavadoc(a.getDescription())
                .build();
            methodSpecs.add(getter);
        }

        for (var f : classModel.getFunctions()) {
            var method = MethodSpec.methodBuilder(f.getName())
                .addModifiers(Modifier.PUBLIC)
                .addModifiers(Modifier.ABSTRACT)
                .addJavadoc(f.getDescription())
                .build();
            methodSpecs.add(method);
        }

        List<ClassName> superinterfaces = new ArrayList<>();
        for (var t : classModel.getBaseTypes()) {
            var n = ClassName.bestGuess(t.getName());
            superinterfaces.add(n);
        }

        var typeSpec = TypeSpec
            .interfaceBuilder(classModel.getName())
            .addSuperinterfaces(superinterfaces)
            .addModifiers(Modifier.PUBLIC)
            .addMethods(methodSpecs)
            .addJavadoc(classModel.getDescription())
            .build();

        JavaFile javaFile = JavaFile.builder(packageName, typeSpec)
            .build();
        return javaFile.toString();
    }
}
