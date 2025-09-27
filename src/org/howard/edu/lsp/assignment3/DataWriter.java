package org.howard.edu.lsp.assignment3;

import java.io.*;
import java.util.List;

/**
 * Handles data loading to CSV files.
 * This class is responsible for writing transformed product data
 * to output CSV files in the required format.
 * 
 * @author Peter Collins
 * @version 1.0
 * @since Assignment 3
 */
public class DataWriter {
    private final String outputPath;

    /**
     * Constructs a DataWriter with the specified output file path.
     * 
     * @param outputPath the path to the output CSV file
     */
    public DataWriter(String outputPath) {
        this.outputPath = outputPath;
    }

    /**
     * Writes a list of products to the output CSV file.
     * Includes a header row and formats each product as a CSV line.
     * 
     * @param products the list of products to write
     * @throws IOException if there is an error writing to the file
     */
    public void writeProducts(List<Product> products) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            writeHeader(writer);
            writeProductData(writer, products);
        }
    }

    /**
     * Writes the CSV header row to the output file.
     * 
     * @param writer the BufferedWriter to write to
     * @throws IOException if there is an error writing
     */
    private void writeHeader(BufferedWriter writer) throws IOException {
        writer.write("ProductID,Name,Price,Category,PriceRange\n");
    }

    /**
     * Writes all product data to the output file.
     * 
     * @param writer the BufferedWriter to write to
     * @param products the list of products to write
     * @throws IOException if there is an error writing
     */
    private void writeProductData(BufferedWriter writer, List<Product> products) throws IOException {
        for (Product product : products) {
            writer.write(formatProductAsCSV(product));
            writer.newLine();
        }
    }

    /**
     * Formats a single product as a CSV line.
     * 
     * @param product the product to format
     * @return a formatted CSV string for the product
     */
    private String formatProductAsCSV(Product product) {
        return String.format("%d,%s,%.2f,%s,%s",
                product.getProductId(),
                product.getName(),
                product.getPrice(),
                product.getCategory(),
                product.getPriceRange());
    }

    /**
     * Gets the output file path.
     * 
     * @return the output file path
     */
    public String getOutputPath() {
        return outputPath;
    }
}
