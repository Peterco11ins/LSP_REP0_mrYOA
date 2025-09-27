package org.howard.edu.lsp.assignment3;

/**
 * Main entry point for Assignment 3 ETL Pipeline.
 * This class demonstrates the object-oriented redesign of the ETL pipeline
 * with improved separation of concerns and better encapsulation.
 * 
 * @author Your Name
 * @version 1.0
 * @since Assignment 3
 */
public class Main {
    /**
     * Main method that executes the ETL pipeline.
     * Creates an ETLOrchestrator and processes the data transformation.
     * 
     * @param args command line arguments (not used in this implementation)
     */
    public static void main(String[] args) {
        try {
            ETLOrchestrator orchestrator = new ETLOrchestrator();
            orchestrator.processETL();
        } catch (Exception e) {
            System.err.println("Error processing ETL pipeline: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
