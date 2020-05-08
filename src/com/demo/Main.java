package com.demo;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        String[] files = {"C:\\Users\\husen\\IdeaProjects\\FileReader\\words.txt"};
        for (String fileName : files) {
            FileReaderHelper helper = new FileReaderHelper(fileName);
            executorService.submit(helper);
        }
        //  words_kvp.forEach((key,value) -> System.out.println(key + " = " + value));
    }
}
