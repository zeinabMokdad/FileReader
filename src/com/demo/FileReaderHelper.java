package com.demo;

import java.io.*;
import java.nio.file.*;

public class FileReaderHelper implements Runnable {
    private String FilePath;

    public FileReaderHelper(String filePath) {
        this.FilePath = filePath;
    }

    public void ReadFile() throws IOException {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(FilePath))) {
            String line = null;
            while ((line = br.readLine()) != null)
                System.out.println(line);
        } catch (IOException exc) {
            System.out.println("exc.getMessage() = " + exc.getMessage());
        }
    }

    public void process() throws IOException {
        File file = new File(FilePath);
        try (InputStream inputStream = new FileInputStream(file)) {
            writeStream(inputStream);
        }
    }

    public void writeStream(InputStream inputStream) throws IOException {
        File outputFile = new File(String.valueOf(Paths.get("output.txt")));
        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
            int fileReturnedValue;
            while ((fileReturnedValue = inputStream.read()) != -1) {
                fos.write(fileReturnedValue);
            }
        }
    }

    @Override
    public void run() {
        try {
            process();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
    }
}

//    public Map<String, Integer> readFile(String path) throws IOException {
//
//        Map<String, Integer> words_kvp = new HashMap<>();
//        List<String> allLines = Files.readAllLines(Paths.get(path));
//
//        for (String line : allLines) {
//            String[] words = line.split(" ");
//            for (String word : words) {
//                String lowerCaseWord = word.toLowerCase();
//                Integer wordCount = words_kvp.get(lowerCaseWord);
//                if (wordCount == null)
//                    words_kvp.put(lowerCaseWord, 1);
//                else
//                    words_kvp.put(lowerCaseWord, wordCount + 1);
//            }
//        }
//        return words_kvp;
//    }

