package com.experimental_software.api_generator.code_generation;

import java.io.IOException;
import java.util.Optional;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@SuppressWarnings("UnstableApiUsage")
public class ClassFinder {

    private final String simpleClassName;

    /**
     * @return the {@link Class} object for the given class name.
     */
    @SuppressWarnings("rawtypes")
    public Optional<? extends Class> getClassRepresentation() {
        return getAllOpenEhrClasses().stream()
            .filter(c -> c.getName().endsWith("." + simpleClassName))
            .map(ClassPath.ClassInfo::load)
            .findFirst();
    }
    // TODO: If running in strict mode, raise exception if multiple classes are found.

    /**
     * Returns the import path of the class e.g. "org.openehr.rm_data_types.quantity.DvCount".
     */
    public Optional<String> getImport() {
        return getAllOpenEhrClasses().stream()
            .map(ClassPath.ClassInfo::getName)
            .filter(name -> name.endsWith("." + simpleClassName))
            .findFirst();
    }
    // TODO: If running in strict mode, raise exception if multiple classes are found.

    /**
     * @see <a href="https://www.baeldung.com/jvm-list-all-classes-loaded">
     *     List All the Classes Loaded in the JVM | baeldung.com
     *     </a>
     * @see <a href="https://stackoverflow.com/questions/3222638/get-all-of-the-classes-in-the-classpath">
     *     Get all of the Classes in the Classpath | stackoverflow.com
     *     </a>
     */
    private static ImmutableSet<ClassPath.ClassInfo> getAllOpenEhrClasses() {
        var cl = ClassFinder.class.getClassLoader();
        try {
            return ClassPath.from(cl).getTopLevelClassesRecursive("org.openehr");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
