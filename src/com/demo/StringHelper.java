package com.demo;

import java.util.ArrayList;
import java.util.List;

public class StringHelper {
    private final List<String> inputString;

    public StringHelper(List<String> inputString) {
        this.inputString = inputString;
    }

    public List<String> Process(String str1) {
        List<String> subsequents = new ArrayList<>();
        for (String input : inputString) {
            String subSequent = GetSubsequentString(str1, input);
            if (subSequent != null)
                subsequents.add(subSequent);
        }
        return subsequents;
    }

    private String GetSubsequentString(String sub, String initialString) {

        if (sub.length() > initialString.length())
            return null;

        if (IsSubSequent(sub, initialString))
            return initialString;

        return null;
    }

    private boolean IsSubSequent(String sub, String initialString) {
        int sequenceCount = 0;
        int length = sub.length();
        int index = 0;
        for (int i = 0; i < length; i++) {
            for (int j = index; j < initialString.length(); j++) {
                if (sub.charAt(i) == initialString.charAt(j)) {
                    index = j;
                    sequenceCount++;
                    break;
                }
            }
        }
        return length == sequenceCount;
    }
}
