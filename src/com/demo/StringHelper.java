package com.demo;

import java.util.ArrayList;

public class StringHelper {
    ArrayList<String> inputString;

    public StringHelper(ArrayList<String> inputString) {
        this.inputString = inputString;
    }

    public ArrayList<String> GetSubsequentString(String str1) {
        ArrayList<String> subsequents = new ArrayList<>();
        for (String input : inputString) {
            String subSequent = isSubSequent(str1, input);
            if (subSequent != null)
                subsequents.add(subSequent);
        }
        return subsequents;
    }


    private String isSubSequent(String sub, String initialString) {

        if (sub == null || initialString == null || sub.isBlank() || sub.isEmpty() || initialString.isEmpty() || initialString.isBlank())
            return null;

        if (sub.length() > initialString.length())
            return null;

        if (checkString(sub, initialString))
            return initialString;

        return null;
    }

    private boolean checkString(String sub, String initialString) {
        int index = 0;
        for (int i = 0; i < initialString.length(); i++) {
            if (sub.charAt(index) == initialString.charAt(i))
                index++;
        }
        if (index == sub.length())
            return true;

        return false;
    }
}
