package org.howard.edu.lsp.midterm.question2;

/**
 * Main class to demonstrate the AreaCalculator functionality.
 * Invokes all overloaded area methods and demonstrates exception handling.
 * 
 * @author Peter Collins
 * @version 1.0
 * @since Midterm
 */
public class Main {
    /**
     * Main method that demonstrates all area calculations and exception handling.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // Demonstrate all area calculations
        System.out.println("Circle radius 3.0 -> area = " + AreaCalculator.area(3.0));
        System.out.println("Rectangle 5.0 x 2.0 -> area = " + AreaCalculator.area(5.0, 2.0));
        System.out.println("Triangle base 10, height 6 -> area = " + AreaCalculator.area(10, 6));
        System.out.println("Square side 4 -> area = " + AreaCalculator.area(4));
        
        // Demonstrate exception handling
        try {
            AreaCalculator.area(-1);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        /*
         * Overloading vs Different Method Names:
         * 
         * Method overloading is the better design choice here because all methods
         * perform the same conceptual operation (calculating area) but with different
         * parameters. This provides a cleaner, more intuitive API where users can
         * call area() with different parameters and get the appropriate calculation.
         * Using different names like rectangleArea(), circleArea() would be less
         * elegant and more verbose, making the API harder to use and remember.
         */
    }
}

/*
 * References:
 * 
 * 1. Oracle Corporation. (2021). Java Platform, Standard Edition 11 API Specification.
 *    Retrieved from https://docs.oracle.com/en/java/javase/11/docs/api/
 * 
 * 2. Oracle Corporation. (2021). The Java Tutorials - Method Overloading.
 *    Retrieved from https://docs.oracle.com/javase/tutorial/java/javaOO/methods.html
 * 
 * 3. Bloch, J. (2018). Effective Java (3rd ed.). Addison-Wesley Professional.
 * 
 * 4. Oracle Corporation. (2021). The Java Tutorials - Exceptions.
 *    Retrieved from https://docs.oracle.com/javase/tutorial/essential/exceptions/
 */
