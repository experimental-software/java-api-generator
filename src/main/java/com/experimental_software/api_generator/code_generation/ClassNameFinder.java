package com.experimental_software.api_generator.code_generation;

import java.io.IOException;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ClassNameFinder {

    private final String simpleClassName;

    /**
     * Returns the import path of the class e.g. "org.openehr.rm_data_types.quantity.DvCount".
     *
     * @see <a href="https://www.baeldung.com/jvm-list-all-classes-loaded">List All the Classes Loaded in the JVM | baeldung.com</a>
     * @see <a href="https://stackoverflow.com/questions/3222638/get-all-of-the-classes-in-the-classpath">Get all of the Classes in the Classpath | stackoverflow.com</a>
     */
    @SuppressWarnings("UnstableApiUsage")
    public String getImport() {
        return getAllOpenEhrClasses().stream()
            .map(ClassPath.ClassInfo::getName)
            .filter(name -> name.endsWith(simpleClassName))
            .findFirst()
            .orElseThrow();
    }
    // TODO: If running in strict mode, raise exception if multiple classes or no class are found.

    @SuppressWarnings("UnstableApiUsage")
    private static ImmutableSet<ClassPath.ClassInfo> getAllOpenEhrClasses() {
        var cl = ClassNameFinder.class.getClassLoader();
        try {
            return ClassPath.from(cl).getTopLevelClassesRecursive("org.openehr");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
