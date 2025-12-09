package org.howard.edu.lsp.finale.question1;

import java.security.SecureRandom;

/**
 * Enhanced password generation algorithm using java.security.SecureRandom.
 * Generates passwords containing uppercase letters, lowercase letters, and digits (A-Z, a-z, 0-9).
 * 
 * @author Peter Collins
 * @version 1.0
 * @since Final Exam
 */
public class EnhancedAlgorithm implements PasswordAlgorithm {
    private static final String ALLOWED = 
        "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
        "abcdefghijklmnopqrstuvwxyz" +
        "0123456789";
    private final SecureRandom secureRandom;

    /**
     * Constructs an EnhancedAlgorithm instance.
     */
    public EnhancedAlgorithm() {
        this.secureRandom = new SecureRandom();
    }

    /**
     * Generates a password of the specified length containing letters and digits.
     * 
     * @param length the desired length of the password
     * @return a password string containing uppercase letters, lowercase letters, and digits
     */
    @Override
    public String generate(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Password length must be positive");
        }
        
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(ALLOWED.length());
            password.append(ALLOWED.charAt(index));
        }
        return password.toString();
    }
}


