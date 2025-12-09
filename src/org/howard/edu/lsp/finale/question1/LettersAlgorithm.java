package org.howard.edu.lsp.finale.question1;

import java.util.Random;

/**
 * Letters-only password generation algorithm.
 * Generates passwords containing letters only (A-Z, a-z).
 * 
 * @author Peter Collins
 * @version 1.0
 * @since Final Exam
 */
public class LettersAlgorithm implements PasswordAlgorithm {
    private static final String LETTERS = 
        "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
        "abcdefghijklmnopqrstuvwxyz";
    private final Random random;

    /**
     * Constructs a LettersAlgorithm instance.
     */
    public LettersAlgorithm() {
        this.random = new Random();
    }

    /**
     * Generates a password of the specified length containing only letters.
     * 
     * @param length the desired length of the password
     * @return a password string containing only letters (A-Z, a-z)
     */
    @Override
    public String generate(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Password length must be positive");
        }
        
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(LETTERS.length());
            password.append(LETTERS.charAt(index));
        }
        return password.toString();
    }
}


