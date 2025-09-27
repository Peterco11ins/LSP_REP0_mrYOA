package org.howard.edu.lsp.assignment3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles data extraction from CSV files.
 * This class is responsible for reading product data from CSV files
 * and converting them into Product objects.
 * 
 * @author Peter Collins
 * @version 1.0
 * @since Assignment 3
 */
public class DataReader {
    private final String inputPath;

    /**
     * Constructs a DataReader with the specified input file path.
     * 
     * @param inputPath the path to the input CSV file
     */
    public DataReader(String inputPath) {
        this.inputPath = inputPath;
    }

    /**
     * Reads product data from the CSV file and returns a list of Product objects.
     * Skips the header row and processes each data row.
     * 
     * @return a list of Product objects extracted from the CSV file
     * @throws IOException if there is an error reading the file
     */
    public List<Product> readProducts() throws IOException {
        List<Product> products = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath))) {
            // Skip header row
            String line = reader.readLine();
            
            // Process data rows
            while ((line = reader.readLine()) != null) {
                Product product = parseProductLine(line);
                if (product != null) {
                    products.add(product);
                }
            }
        }
        
        return products;
    }

    /**
     * Parses a single line from the CSV file and creates a Product object.
     * 
     * @param line the CSV line to parse
     * @return a Product object if parsing is successful, null otherwise
     */
    private Product parseProductLine(String line) {
        try {
            String[] parts = line.split(",");
            if (parts.length == 4) {
                int productId = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                double price = Double.parseDouble(parts[2].trim());
                String category = parts[3].trim();
                
                return new Product(productId, name, price, category);
            }
        } catch (NumberFormatException e) {
            System.err.println("Error parsing line: " + line + " - " + e.getMessage());
        }
        
        return null;
    }

    /**
     * Gets the input file path.
     * 
     * @return the input file path
     */
    public String getInputPath() {
        return inputPath;
    }
}
