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

        if (IsEqual(sub, initialString))
            return initialString;

        return null;
    }

    private boolean IsEqual(String sub, String initialString) {
        int index = 0;
        int length = sub.length();
        for (int i = 0; i < initialString.length(); i++) {
            if (sub.charAt(index) == initialString.charAt(i))
                index++;
        }
        if (index == length)
            return true;
        return false;
    }
}
