package com.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("please provide filename in the command");
            return;
        }
        String fileName = args[0];
        if (!Files.exists(Paths.get(fileName))) {
            System.out.println("file not found: " + fileName);
            return;
        }

        FileHelper fileReaderHelper = new FileHelper(fileName);
        fileReaderHelper.processLargeFiles();

        //FileHelper fileHelper = new FileHelper("words.txt");
        // fileHelper.processLargeFiles();
        //ExecutorService executorService = Executors.newFixedThreadPool(3);
        //String[] files = {"C:\\Users\\husen\\IdeaProjects\\FileReader\\words.txt"};
        //for (String fileName : files) {
        //     FileReaderHelper helper = new FileReaderHelper(fileName);
        //  executorService.submit(helper);
        // }
        //  words_kvp.forEach((key,value) -> System.out.println(key + " = " + value));
    }
}
