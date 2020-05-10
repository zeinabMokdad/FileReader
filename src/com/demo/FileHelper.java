package com.demo;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileHelper {
    String fileURI;

    public FileHelper(String fileURI) {
        this.fileURI = fileURI;
    }

    public void processLargeFiles() throws IOException {
        ConcurrentHashMap<String, Integer> words_kvp = new ConcurrentHashMap<>();

        Path path = Paths.get("Output.txt");
        File outputFile = path.toFile();

        Files.lines(Paths.get(fileURI), StandardCharsets.UTF_8).parallel().forEach(
                line -> processLine(line, words_kvp));

        Write(words_kvp, outputFile);
    }

    public void process() throws IOException {
        ConcurrentHashMap<String, Integer> words_kvp = new ConcurrentHashMap<>();

        Path path = Paths.get("Output.txt");
        File outputFile = path.toFile();

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Files.lines(Paths.get(fileURI), StandardCharsets.UTF_8).forEach(
                line -> executorService.submit(() -> processLine(line, words_kvp))
        );
        executorService.shutdown();
        Write(words_kvp, outputFile);
    }

    private synchronized void processLine(String line, ConcurrentHashMap<String, Integer> wordsCount) {
        String[] words = line.split(" ");

        for (String word : words) {
            if (word.isEmpty() || word.isBlank())
                continue;

            String lowerCaseWord = word.toLowerCase();
            Integer wordCount = wordsCount.get(lowerCaseWord);
            if (wordCount == null)
                wordsCount.put(lowerCaseWord, 1);
            else
                wordsCount.put(lowerCaseWord, wordCount + 1);

//            wordsCount.compute(word.toLowerCase(), (key, val) ->
//                    val == null ? 1 : val++);
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

    public void Write(InputStream inputStream, File outputFile) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile)) {
            int read;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1)
                fileOutputStream.write(bytes, 0, read);
        }
    }

    private void doWrite(Map<String, Integer> wordsCount, File outputFile) throws IOException {

        BufferedWriter bw = Files.newBufferedWriter(Paths.get("output.txt"));

        for (Map.Entry<String, Integer> entry : wordsCount.entrySet()) {
            try (Formatter f = new Formatter(bw)) {
                f.format("%s : %d", entry.getKey(), entry.getValue());
            }
        }
    }
}


