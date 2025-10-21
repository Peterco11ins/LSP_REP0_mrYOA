package org.howard.edu.lsp.midterm.question2;

/**
 * AreaCalculator class with overloaded methods for calculating areas of different shapes.
 * Demonstrates method overloading with different parameter signatures.
 * 
 * @author Peter Collins
 * @version 1.0
 * @since Midterm
 */
public class AreaCalculator {
    
    /**
     * Calculates the area of a circle.
     * 
     * @param radius the radius of the circle
     * @return the area of the circle
     * @throws IllegalArgumentException if radius is less than or equal to 0
     */
    public static double area(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive");
        }
        return Math.PI * radius * radius;
    }
    
    /**
     * Calculates the area of a rectangle.
     * 
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     * @return the area of the rectangle
     * @throws IllegalArgumentException if width or height is less than or equal to 0
     */
    public static double area(double width, double height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and height must be positive");
        }
        return width * height;
    }
    
    /**
     * Calculates the area of a triangle.
     * 
     * @param base the base of the triangle
     * @param height the height of the triangle
     * @return the area of the triangle
     * @throws IllegalArgumentException if base or height is less than or equal to 0
     */
    public static double area(int base, int height) {
        if (base <= 0 || height <= 0) {
            throw new IllegalArgumentException("Base and height must be positive");
        }
        return 0.5 * base * height;
    }
    
    /**
     * Calculates the area of a square.
     * 
     * @param side the length of one side of the square
     * @return the area of the square
     * @throws IllegalArgumentException if side is less than or equal to 0
     */
    public static double area(int side) {
        if (side <= 0) {
            throw new IllegalArgumentException("Side must be positive");
        }
        return side * side;
    }
}

/*
 * References:
 * 
 * 1. Oracle Corporation. (2021). Java Platform, Standard Edition 11 API Specification.
 *    Retrieved from https://docs.oracle.com/en/java/javase/11/docs/api/
 * 
 * 2. Bloch, J. (2018). Effective Java (3rd ed.). Addison-Wesley Professional.
 * 
 * 3. Gamma, E., Helm, R., Johnson, R., & Vlissides, J. (1994). Design Patterns: 
 *    Elements of Reusable Object-Oriented Software. Addison-Wesley.
 * 
 * 4. Oracle Corporation. (2021). The Java Tutorials - Exceptions.
 *    Retrieved from https://docs.oracle.com/javase/tutorial/essential/exceptions/
 */
