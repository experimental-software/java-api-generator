package com.experimental_software.util;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringExtension {

    /**
     * @see <a href="https://en.wiktionary.org/wiki/Pascal_case">Pascal case</a>
     */
    public static String toPascalCase(String string) {
        List<String> words = new ArrayList<>();
        for (var word : string.split("[_\s]")) {
            var normalizedWord = word;
            if (normalizedWord.matches("[A-Z]+")) {
                normalizedWord = word.toLowerCase();
            }
            normalizedWord = StringUtils.capitalize(normalizedWord);
            words.add(normalizedWord);
        }
        return String.join("", words);
    }
}
