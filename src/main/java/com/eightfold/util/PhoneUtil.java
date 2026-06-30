package com.eightfold.util;

public class PhoneUtil {

    public static String normalize(String phone) {

        if (phone == null) {
            return null;
        }

        // Keep only digits
        phone = phone.replaceAll("[^0-9]", "");

        // If it's a 10-digit Indian number, prepend +91
        if (phone.length() == 10) {
            phone = "91" + phone;
        }

        return "+" + phone;
    }
}