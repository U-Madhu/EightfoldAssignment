package com.eightfold.util;

public class EmailUtil {

    public static String normalize(String email) {

        if (email == null) {
            return null;
        }

        return email.trim().toLowerCase();
    }

}