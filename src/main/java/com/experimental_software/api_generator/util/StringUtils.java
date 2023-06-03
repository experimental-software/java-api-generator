package com.experimental_software.api_generator.util;


import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtils {

    /**
     * @see <a href="https://en.wiktionary.org/wiki/Pascal_case">Pascal case</a>
     */
    public static String toPascalCase(@NonNull String string) {
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
}
