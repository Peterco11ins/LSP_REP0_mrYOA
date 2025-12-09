package org.howard.edu.lsp.finale.question1;

import java.util.Random;

/**
 * Basic password generation algorithm using java.util.Random.
 * Generates passwords containing digits only (0-9).
 * 
 * @author Peter Collins
 * @version 1.0
 * @since Final Exam
 */
public class BasicAlgorithm implements PasswordAlgorithm {
    private static final String DIGITS = "0123456789";
    private final Random random;

    /**
     * Constructs a BasicAlgorithm instance.
     */
    public BasicAlgorithm() {
        this.random = new Random();
    }

    /**
     * Generates a password of the specified length containing only digits.
     * 
     * @param length the desired length of the password
     * @return a password string containing only digits (0-9)
     */
    @Override
    public String generate(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Password length must be positive");
        }
        
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(DIGITS.length());
            password.append(DIGITS.charAt(index));
        }
        return password.toString();
    }
}


