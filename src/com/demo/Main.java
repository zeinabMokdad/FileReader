package com.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        ArrayList<String> inputs = new ArrayList<>();
        inputs.add("zeinab");
        inputs.add("abc");
        inputs.add("mokdad");
        inputs.add("bca");
        inputs.add("ahhhbiiic");
        inputs.add("cjjjjbjjjja");

        StringHelper stringHelper = new StringHelper(inputs);

        String inputString="";
        do {

            System.out.print("Enter a string : ");
            Scanner scanner = new Scanner(System.in);
            inputString = inputString + scanner.nextLine();
            List<String> subSequents = stringHelper.Process(inputString);
            for (String str : subSequents) {
                System.out.println(str);
            }
            stringHelper.setInputString(subSequents);
        }
        while (!inputString.contains("!END!"));


//        if (args.length == 0) {
//            System.out.println("please provide filename in the command");
//            return;
//        }
//        String fileName = args[0];
//        if (!Files.exists(Paths.get(fileName))) {
//            System.out.println("file not found: " + fileName);
//            return;
//        }
//
//        FileHelper fileReaderHelper = new FileHelper(fileName);
//        fileReaderHelper.processLargeFiles();

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
