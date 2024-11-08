package com.example.authentication.utils;

import java.security.MessageDigest;

public class HashHelper {
    public static String generateHash(String password) throws Exception{
        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte hash[] = algorithm.digest(password.getBytes("UTF-8"));

        StringBuilder text = new StringBuilder();
        for (byte b : hash) {
            text.append(String.format("%02X", 0xFF & b));
        }

        return text.toString();
    }
}
