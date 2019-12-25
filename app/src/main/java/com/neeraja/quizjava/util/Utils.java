package com.neeraja.quizjava.util;

public class Utils {
    public static boolean isValidString (String text) {
        if (text != null && !text.isEmpty())
            return true;
        return false;
    }
}
