package com.demo;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileHelper {
    String fileURI;

    public FileHelper(String fileURI) {
        this.fileURI = fileURI;
    }

    public void processLargeFiles() throws IOException {
        Map<String, Integer> words_kvp = new HashMap<>();
        File outputFile = new File(String.valueOf(Paths.get("Output.txt")));
        Files.lines(Paths.get(fileURI), StandardCharsets.UTF_8).parallel().forEach(
                line -> processLine(line, words_kvp)
        );
        Write(words_kvp, outputFile);
    }

    public void process() throws IOException {
        Map<String, Integer> words_kvp = new HashMap<>();
        File outputFile = new File(String.valueOf(Paths.get("Output.txt")));

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Files.lines(Paths.get(fileURI), StandardCharsets.UTF_8).forEach(
                line -> executorService.submit(() -> processLine(line, words_kvp))
        );
        executorService.shutdown();
        Write(words_kvp, outputFile);
    }

    private void processLine(String line, Map<String, Integer> wordsCount) {
        String[] words = line.split(" ");
        for (String word : words) {

            if(word.isEmpty() || word.isBlank())
                continue;

            String lowerCaseWord = word.toLowerCase();
            wordsCount.merge(lowerCaseWord, 1, Integer::sum);
        }
    }
    public void Write(Map<String, Integer> wordsCount, File outputFile) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))) {
            for (Map.Entry<String, Integer> entry : wordsCount.entrySet()) {
                bufferedWriter.write(entry.getKey() + ":" + entry.getValue());
                bufferedWriter.newLine();
            }

        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
    }
}
