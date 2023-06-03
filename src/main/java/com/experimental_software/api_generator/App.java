package com.experimental_software.api_generator;

import java.io.File;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.experimental_software.api_generator.code_generation.CodeGenerator;
import com.experimental_software.api_generator.code_generation.JavaFileWriter;
import com.experimental_software.api_generator.intermediate_representation.ClassModelFactory;
import com.experimental_software.api_generator.intermediate_representation.Collector;

public class App {

    public static void main(String[] argv) {
        Args args = new Args();
        JCommander.newBuilder()
            .addObject(args)
            .build()
            .parse(argv);

        var irModels = Collector.findIrModels(new File(args.projectRoot));
        for (var ir : irModels) {
            var classModel = new ClassModelFactory(ir.json()).create();
            var javaCode = CodeGenerator.generateInterface(ir.packageName(), classModel);

            new JavaFileWriter(ir.getJavaPath(), javaCode).writeToDisk();
        }
    }

    static class Args {
        @Parameter(names={"--project-root", "-r"}, required = true)
        String projectRoot;
    }
}
