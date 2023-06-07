package com.experimental_software.api_generator.util;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtils {

    // TODO: Refactor to `new CaseTransformer(string).toPascalCase()`
    /**
     * @see <a href="https://en.wiktionary.org/wiki/Pascal_case">Pascal case</a>
     */
    public static String toPascalCase(@NonNull final String string) {
        if (string.contains("<")) {
            var normalizedTypeParameters = typeParametersToPascalCase(parseTypeParameters(string));
            var normalizedType = toPascalCase(string.substring(0, string.indexOf("<")));

            return String.format("%s<%s>", normalizedType, normalizedTypeParameters);
        }

        List<String> words = new ArrayList<>();
        for (var word : string.split("[_\s]")) {
            var normalizedWord = word;
            if (normalizedWord.matches("[A-Z]+")) {
                normalizedWord = word.toLowerCase();
            }
            normalizedWord = org.apache.commons.lang3.StringUtils.capitalize(normalizedWord);
            words.add(normalizedWord);
        }

        return String.join("", words);
    }

    private static String[] parseTypeParameters(String string) {
        var m = Pattern.compile("<(.*)>")
            .matcher(string)
            .results()
            .findFirst();
        if (m.isEmpty()) {
            throw new IllegalArgumentException("Could not parse type parameters from: " + string);
        }
        return m.get().group(1).split(",");
    }

    private static String typeParametersToPascalCase(String[] originalTypeParameters) {
        var normalizedTypeParameters = new StringBuilder();
        for (int i = 0; i < originalTypeParameters.length; i++) {
            var originalType = originalTypeParameters[i];
            var normalizedType = originalType.strip();
            normalizedType = toPascalCase(normalizedType);
            normalizedTypeParameters.append(normalizedType);
            if (i + 1 < originalTypeParameters.length) {
                normalizedTypeParameters.append(",");
            }
        }
        return normalizedTypeParameters.toString();
    }

    /**
     * @see <a href="https://wiki.c2.com/?LowerCamelCase">Lower Camel Case | wiki.c2.com</a>
     */
    public static String toLowerCamelCase(@NonNull String string) {
        var sb = new StringBuilder();
        var parts = string.split("_");
        for (int i = 0; i < parts.length; i++) {
            var part = parts[i];
            String normalizedPart;
            if (i == 0) {
                normalizedPart = part.toLowerCase();
            } else {
                normalizedPart = org.apache.commons.lang3.StringUtils.capitalize(part);
            }
            sb.append(normalizedPart);
        }
        return sb.toString();
    }
}
