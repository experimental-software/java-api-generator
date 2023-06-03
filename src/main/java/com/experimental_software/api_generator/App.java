package com.experimental_software.api_generator;

import java.io.File;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.experimental_software.api_generator.intermediate_representation.Collector;

public class App {

    public static void main(String[] argv) {
        Args args = new Args();
        JCommander.newBuilder()
            .addObject(args)
            .build()
            .parse(argv);
        System.out.printf("Project root: %s%n", args.projectRoot);

        var irModels = Collector.findIrModels(new File(args.projectRoot));
        for (var irModel : irModels) {
            System.out.println(irModel);



//            var classModel = new ClassModelFactory(json).create();
            // CodeGenerator.generateInterface(packageName, classModel);
        }

        // CodeGenerator.generateInterface()
    }

    static class Args {
        @Parameter(names={"--project-root", "-r"}, required = true)
        String projectRoot;
    }
}
