/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.festival.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHashUtil {
    
    // Method to hash a password using SHA-256
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256"); // Create SHA-256 message digest instance
            byte[] hash = md.digest(password.getBytes()); // Convert password into hash bytes
            StringBuilder hexString = new StringBuilder(); // Convert hash bytes to hexadecimal format
            
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    // Method to verify password by comparing hashes
    public static boolean verifyPassword(String password, String hashedPassword) {
        String hashedInput = hashPassword(password); // Hash the input password
        return hashedInput.equals(hashedPassword);  // Compare hashed input with stored hashed password
    }
}
