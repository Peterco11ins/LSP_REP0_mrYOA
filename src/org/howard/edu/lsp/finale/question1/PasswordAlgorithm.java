package org.howard.edu.lsp.finale.question1;

/**
 * Strategy interface for password generation algorithms.
 * This interface allows different password generation strategies to be
 * implemented and swapped at runtime without modifying client code.
 * 
 * @author Peter Collins
 * @version 1.0
 * @since Final Exam
 */
public interface PasswordAlgorithm {
    /**
     * Generates a password of the specified length using the algorithm's strategy.
     * 
     * @param length the desired length of the password
     * @return a password string generated according to the algorithm's rules
     */
    String generate(int length);
}


