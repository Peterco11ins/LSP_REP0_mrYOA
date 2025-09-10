package org.howard.edu.lsp.assignment2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ETLPipeline {
    private static final String INPUT_PATH = "data/products.csv";
    private static final String OUTPUT_PATH = "data/transformed_products.csv";

    public static void main(String[] args) {
        new ETLPipeline().processETL();
    }

    public void processETL() {
        try {
            List<Product> products = extractData();
            List<Product> transformedProducts = transformData(products);
            loadData(transformedProducts);
            System.out.println("ETL pipeline completed. Output written to " + OUTPUT_PATH);
        } catch (IOException e) {
            System.err.println("Error processing ETL pipeline: " + e.getMessage());
        }
    }

    private List<Product> extractData() throws IOException {
        List<Product> products = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_PATH))) {
            String line = reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    products.add(new Product(
                        Integer.parseInt(parts[0]),
                        parts[1],
                        Double.parseDouble(parts[2]),
                        parts[3]
                    ));
                }
            }
        }
        return products;
    }

    private List<Product> transformData(List<Product> products) {
        for (Product product : products) {
            product.setName(product.getName().toUpperCase());

            if (product.getCategory().equals("Electronics")) {
                if (product.getPrice() > 500) {
                    product.setCategory("Premium Electronics");
                }
                double discountedPrice = roundToTwoDecimals(product.getPrice() * 0.9);
                product.setPrice(discountedPrice);
            }
        }
        return products;
    }

    private double roundToTwoDecimals(double value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private void loadData(List<Product> products) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_PATH))) {
            writer.write("ProductID,Name,Price,Category,PriceRange\n");
            for (Product product : products) {
                writer.write(String.format("%d,%s,%.2f,%s,%s\n",
                    product.getProductId(),
                    product.getName(),
                    product.getPrice(),
                    product.getCategory(),
                    product.getPriceRange()
                ));
            }
        }
    }
}

class Product {
    private int productId;
    private String name;
    private double price;
    private String category;
    private String priceRange;

    public Product(int productId, String name, double price, String category) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.priceRange = calculatePriceRange(price);
    }

    private String calculatePriceRange(double price) {
        if (price <= 10.0) return "Low";
        if (price <= 100.0) return "Medium";
        if (price <= 500.0) return "High";
        return "Premium";
    }

    public int getProductId() { return productId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getPriceRange() { return priceRange; }
}
