package org.howard.edu.lsp.assignment3;

import java.io.IOException;
import java.util.List;

/**
 * Orchestrates the entire ETL (Extract, Transform, Load) pipeline.
 * This class coordinates the data extraction, transformation, and loading
 * processes using specialized component classes.
 * 
 * @author Peter Collins
 * @version 1.0
 * @since Assignment 3
 */
public class ETLOrchestrator {
    private final DataReader dataReader;
    private final ProductTransformer productTransformer;
    private final DataWriter dataWriter;

    /**
     * Constructs an ETLOrchestrator with the specified file paths.
     * 
     * @param inputPath the path to the input CSV file
     * @param outputPath the path to the output CSV file
     */
    public ETLOrchestrator(String inputPath, String outputPath) {
        this.dataReader = new DataReader(inputPath);
        this.productTransformer = new ProductTransformer();
        this.dataWriter = new DataWriter(outputPath);
    }

    /**
     * Constructs an ETLOrchestrator with default file paths.
     * Uses the same paths as Assignment 2 for compatibility.
     */
    public ETLOrchestrator() {
        this("data/products.csv", "data/transformed_products.csv");
    }

    /**
     * Executes the complete ETL pipeline.
     * This method coordinates the extraction, transformation, and loading
     * of product data according to the business requirements.
     * 
     * @throws IOException if there is an error during file operations
     */
    public void processETL() throws IOException {
        System.out.println("Starting ETL pipeline...");
        
        // Extract data from input file
        List<Product> products = extractData();
        System.out.println("Extracted " + products.size() + " products from input file");
        
        // Transform the data according to business rules
        List<Product> transformedProducts = transformData(products);
        System.out.println("Transformed " + transformedProducts.size() + " products");
        
        // Load transformed data to output file
        loadData(transformedProducts);
        System.out.println("Loaded transformed data to output file");
        
        System.out.println("ETL pipeline completed successfully");
    }

    /**
     * Extracts product data from the input file.
     * 
     * @return a list of Product objects extracted from the input file
     * @throws IOException if there is an error reading the input file
     */
    private List<Product> extractData() throws IOException {
        return dataReader.readProducts();
    }

    /**
     * Transforms the product data according to business rules.
     * 
     * @param products the list of products to transform
     * @return the transformed list of products
     */
    private List<Product> transformData(List<Product> products) {
        return productTransformer.transformProducts(products);
    }

    /**
     * Loads the transformed product data to the output file.
     * 
     * @param products the list of transformed products to write
     * @throws IOException if there is an error writing to the output file
     */
    private void loadData(List<Product> products) throws IOException {
        dataWriter.writeProducts(products);
    }

    /**
     * Gets the data reader component.
     * 
     * @return the DataReader instance
     */
    public DataReader getDataReader() {
        return dataReader;
    }

    /**
     * Gets the product transformer component.
     * 
     * @return the ProductTransformer instance
     */
    public ProductTransformer getProductTransformer() {
        return productTransformer;
    }

    /**
     * Gets the data writer component.
     * 
     * @return the DataWriter instance
     */
    public DataWriter getDataWriter() {
        return dataWriter;
    }
}
