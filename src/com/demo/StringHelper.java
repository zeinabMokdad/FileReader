package com.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StringHelper {
    private List<String> inputString;
    private HashMap<String, Integer> lastIndexByWord;

    public StringHelper(List<String> inputString) {

        this.inputString = inputString;
        lastIndexByWord = new HashMap<>();
    }

    public void setInputString(List<String> inputString) {
        this.inputString = inputString;
    }

    public List<String> Process(String str1) {
        HashMap<String, Integer> matchedIndexByWord = new HashMap<>();
        for (String input : inputString) {
            SetSubsequentString(str1, input, matchedIndexByWord);
        }
        lastIndexByWord = matchedIndexByWord;
        return new ArrayList<>(matchedIndexByWord.keySet());
    }

    private void SetSubsequentString(String sub, String initialString, HashMap<String, Integer> matchedIndexByWord) {

        if (sub.length() > initialString.length())
            return;

        IsSubSequent(sub, initialString, matchedIndexByWord);
    }

    private boolean IsSubSequent(String sub, String initialString, HashMap<String, Integer> matchedIndexByWord) {
        int sequenceCount = 0;
        int length = sub.length();
        int matchedStringLasIndex = lastIndexByWord.getOrDefault(initialString, 0);

        for (int i = 0; i < length; i++) {
            for (int j = matchedStringLasIndex - 1; j < initialString.length(); j++) {
                if (sub.charAt(i) == initialString.charAt(j)) {
                    matchedStringLasIndex = j;
                    sequenceCount++;
                    break; 
                }
            }
        }
        if (length == sequenceCount) {
            matchedIndexByWord.put(initialString, matchedStringLasIndex);
            return true;
        }
        return true;
    }
}
