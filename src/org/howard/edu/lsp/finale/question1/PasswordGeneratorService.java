package org.howard.edu.lsp.finale.question1;

import java.util.HashMap;
import java.util.Map;

/**
 * PasswordGeneratorService provides a centralized service for generating passwords
 * using various algorithms. This service implements the Singleton pattern to ensure
 * only one instance exists, and uses the Strategy pattern to allow runtime selection
 * and swapping of password generation algorithms.
 * 
 * DESIGN PATTERNS USED:
 * ====================
 * 
 * 1. SINGLETON PATTERN:
 *    - The service uses a private static instance and a private constructor to ensure
 *      only one instance of PasswordGeneratorService can exist in the application.
 *    - The getInstance() method provides the single shared access point to the service.
 *    - This pattern is appropriate because the requirements explicitly state "only one
 *      instance of the service may exist" and "provide a single shared access point."
 *    - Benefits: Centralized control, resource efficiency, and consistent state management.
 * 
 * 2. STRATEGY PATTERN:
 *    - The service uses the Strategy pattern to encapsulate different password generation
 *      algorithms (basic, enhanced, letters) as interchangeable strategies.
 *    - Each algorithm implements the PasswordAlgorithm interface, allowing them to be
 *      swapped at runtime without modifying client code.
 *    - The setAlgorithm() method allows the caller to select and change the algorithm
 *      dynamically, and generatePassword() delegates to the currently selected strategy.
 *    - This pattern is appropriate because the requirements state: "Support multiple
 *      approaches to password generation," "Allow the caller to select the approach at
 *      run time," "Support future expansion of password-generation approaches," and
 *      "Make password-generation behavior swappable."
 *    - Benefits: Open/Closed Principle (open for extension, closed for modification),
 *      runtime algorithm selection, and easy addition of new algorithms without changing
 *      existing code.
 * 
 * COMBINATION OF PATTERNS:
 *    - The Singleton pattern ensures a single service instance, while the Strategy pattern
 *      provides the flexibility to change algorithms. This combination allows the service
 *      to maintain state (the selected algorithm) while providing a consistent interface
 *      for password generation across the application.
 * 
 * @author Peter Collins
 * @version 1.0
 * @since Final Exam
 */
public class PasswordGeneratorService {
    private static PasswordGeneratorService instance;
    private PasswordAlgorithm currentAlgorithm;
    private Map<String, PasswordAlgorithm> algorithms;

    /**
     * Private constructor to enforce Singleton pattern.
     * Initializes the available password generation algorithms.
     */
    private PasswordGeneratorService() {
        algorithms = new HashMap<>();
        algorithms.put("basic", new BasicAlgorithm());
        algorithms.put("enhanced", new EnhancedAlgorithm());
        algorithms.put("letters", new LettersAlgorithm());
    }

    /**
     * Returns the singleton instance of PasswordGeneratorService.
     * If no instance exists, creates one. Subsequent calls return the same instance.
     * 
     * @return the singleton instance of PasswordGeneratorService
     */
    public static PasswordGeneratorService getInstance() {
        if (instance == null) {
            synchronized (PasswordGeneratorService.class) {
                if (instance == null) {
                    instance = new PasswordGeneratorService();
                }
            }
        }
        return instance;
    }

    /**
     * Sets the password generation algorithm to use.
     * Supported algorithms: "basic", "enhanced", "letters"
     * 
     * @param name the name of the algorithm to use
     * @throws IllegalArgumentException if the algorithm name is not supported
     */
    public void setAlgorithm(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Algorithm name cannot be null or empty");
        }
        
        PasswordAlgorithm algorithm = algorithms.get(name.toLowerCase());
        if (algorithm == null) {
            throw new IllegalArgumentException("Unsupported algorithm: " + name);
        }
        
        this.currentAlgorithm = algorithm;
    }

    /**
     * Generates a password of the specified length using the currently selected algorithm.
     * 
     * @param length the desired length of the password
     * @return a password string generated according to the selected algorithm's rules
     * @throws IllegalStateException if no algorithm has been set before calling this method
     * @throws IllegalArgumentException if length is not positive
     */
    public String generatePassword(int length) {
        if (currentAlgorithm == null) {
            throw new IllegalStateException("Algorithm must be set before generating password");
        }
        
        return currentAlgorithm.generate(length);
    }
}


