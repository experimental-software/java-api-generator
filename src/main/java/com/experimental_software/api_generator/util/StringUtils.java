package com.experimental_software.api_generator.util;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtils {

    /**
     * @see <a href="https://en.wiktionary.org/wiki/Pascal_case">Pascal case</a>
     */
    public static String toPascalCase(@NonNull final String string) {
        if (string.contains("<")) {
            var parts = string.split("<");
            var beforeTypeParameters = toPascalCase(parts[0]);
            var typeParameters = typeParameterToPascalCase("<" + parts[1]);

            return beforeTypeParameters + typeParameters;
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

    private static String typeParameterToPascalCase(String string) {
        List<String> types = new ArrayList<>();

        var typeParameterDetails = Pattern.compile("<(.*)>")
            .matcher(string)
            .results()
            .findFirst()
            .orElseThrow()
            .group(1);

        for (var type : typeParameterDetails.split(",")) {
            var normalizedType = type.strip();
            normalizedType = toPascalCase(normalizedType);
            types.add(normalizedType);
        }

        return String.format("<%s>", String.join(",", types));
    }
}
