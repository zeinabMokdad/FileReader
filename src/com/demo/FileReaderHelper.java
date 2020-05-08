package com.demo;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileReaderHelper {

    public Map<String, Integer> readFile(String path) throws IOException {

        Map<String, Integer> words_kvp = new HashMap<>();
        List<String> allLines = Files.readAllLines(Paths.get(path));

        for (String line : allLines) {
            String[] words = line.split(" ");
            for (String word : words) {
                String lowerCaseWord = word.toLowerCase();
                Integer wordCount = words_kvp.get(lowerCaseWord);
                if (wordCount == null)
                    words_kvp.put(lowerCaseWord, 1);
                else
                    words_kvp.put(lowerCaseWord, wordCount + 1);
            }
        }
        return words_kvp;
    }
}
