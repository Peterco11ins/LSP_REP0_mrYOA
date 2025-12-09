package org.howard.edu.lsp.finale.question1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit 5 test suite for PasswordGeneratorService.
 * Tests singleton behavior, algorithm selection, and password generation
 * for all supported algorithms.
 * 
 * @author Peter Collins
 * @version 1.0
 * @since Final Exam
 */
public class PasswordGeneratorServiceTest {

    private PasswordGeneratorService service;

    @BeforeEach
    public void setup() {
        service = PasswordGeneratorService.getInstance();
    }

    @Test
    public void checkInstanceNotNull() {
        // TODO: verify that 'service' is not null
        assertNotNull(service, "Service instance should not be null");
    }

    @Test
    public void checkSingleInstanceBehavior() {
        PasswordGeneratorService second = PasswordGeneratorService.getInstance();
        // TODO: Verify that both 'service' (created in @BeforeEach) 
        // and 'second' refer to the EXACT same object in memory. This 
        // test must confirm true singleton behavior — not just that the 
        // two objects are equal, but they are the *same 
        // instance* returned by getInstance().
        assertSame(service, second, "Both instances should refer to the exact same object (singleton behavior)");
    }

    @Test
    public void generateWithoutSettingAlgorithmThrowsException() {
        PasswordGeneratorService s = PasswordGeneratorService.getInstance();
        // TODO: verify correct exception behavior for each algorithm
        assertThrows(IllegalStateException.class, () -> {
            s.generatePassword(10);
        }, "Should throw IllegalStateException when generating password without setting algorithm");
    }

    @Test
    public void basicAlgorithmGeneratesCorrectLengthAndDigitsOnly() {
        service.setAlgorithm("basic");
        String p = service.generatePassword(10);
        // TODO: verify required behavior
        assertNotNull(p, "Password should not be null");
        assertEquals(10, p.length(), "Password should have correct length");
        assertTrue(p.matches("[0-9]+"), "Password should contain only digits (0-9)");
        
        // Test multiple generations to ensure consistency
        for (int i = 0; i < 5; i++) {
            String password = service.generatePassword(15);
            assertEquals(15, password.length(), "Password should have correct length");
            assertTrue(password.matches("[0-9]+"), "Password should contain only digits");
        }
    }

    @Test
    public void enhancedAlgorithmGeneratesCorrectCharactersAndLength() {
        service.setAlgorithm("enhanced");
        String p = service.generatePassword(12);
        // TODO: verify required behavior
        assertNotNull(p, "Password should not be null");
        assertEquals(12, p.length(), "Password should have correct length");
        assertTrue(p.matches("[A-Za-z0-9]+"), "Password should contain only A-Z, a-z, and 0-9");
        
        // Test multiple generations
        for (int i = 0; i < 5; i++) {
            String password = service.generatePassword(20);
            assertEquals(20, password.length(), "Password should have correct length");
            assertTrue(password.matches("[A-Za-z0-9]+"), "Password should contain only allowed characters");
        }
    }

    @Test
    public void lettersAlgorithmGeneratesLettersOnly() {
        service.setAlgorithm("letters");
        String p = service.generatePassword(8);
        // TODO: verify required behavior
        assertNotNull(p, "Password should not be null");
        assertEquals(8, p.length(), "Password should have correct length");
        assertTrue(p.matches("[A-Za-z]+"), "Password should contain only letters (A-Z, a-z)");
        
        // Test multiple generations
        for (int i = 0; i < 5; i++) {
            String password = service.generatePassword(10);
            assertEquals(10, password.length(), "Password should have correct length");
            assertTrue(password.matches("[A-Za-z]+"), "Password should contain only letters");
        }
    }

    @Test
    public void switchingAlgorithmsChangesBehavior() {
        service.setAlgorithm("basic");
        String p1 = service.generatePassword(10);

        service.setAlgorithm("letters");
        String p2 = service.generatePassword(10);

        service.setAlgorithm("enhanced");
        String p3 = service.generatePassword(10);

        // TODO: verify correct behavior characteristics of each algorithm
        // Verify basic algorithm output
        assertNotNull(p1, "Basic password should not be null");
        assertEquals(10, p1.length(), "Basic password should have correct length");
        assertTrue(p1.matches("[0-9]+"), "Basic password should contain only digits");
        
        // Verify letters algorithm output
        assertNotNull(p2, "Letters password should not be null");
        assertEquals(10, p2.length(), "Letters password should have correct length");
        assertTrue(p2.matches("[A-Za-z]+"), "Letters password should contain only letters");
        
        // Verify enhanced algorithm output
        assertNotNull(p3, "Enhanced password should not be null");
        assertEquals(10, p3.length(), "Enhanced password should have correct length");
        assertTrue(p3.matches("[A-Za-z0-9]+"), "Enhanced password should contain letters and digits");
        
        // Verify that passwords are different (very likely with different algorithms)
        // Note: There's a tiny chance they could be the same, but it's extremely unlikely
        // We can at least verify the character sets are different
        assertTrue(p1.matches("[0-9]+") && !p2.matches("[0-9]+"), 
                   "Basic and Letters passwords should have different character sets");
    }
}


