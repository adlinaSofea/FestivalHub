/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.festival.util;

/**
 * Utility class for authentication operations.
 * Provides methods for password authentication and encryption.
 * Acts as a facade to PasswordHashUtil.
 */
public class AuthUtil {
    // * Verifies if input password matches stored hashed password.Used during user login authentication.
    public static boolean authenticate(String inputPassword, String storedPassword) { 
        return PasswordHashUtil.verifyPassword(inputPassword, storedPassword);
    }
    
    //Hashes a plain text password for secure storage. Used during user registration.
    public static String encryptPassword(String password) {
        return PasswordHashUtil.hashPassword(password);
    }
}
