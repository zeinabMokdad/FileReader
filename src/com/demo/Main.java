package com.demo;

import java.io.IOException;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        FileReaderHelper helper = new FileReaderHelper();
        Map<String, Integer> words_kvp= helper.readFile("words.txt");

        words_kvp.forEach((key,value) -> System.out.println(key + " = " + value));
    }
}
