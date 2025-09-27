package org.howard.edu.lsp.assignment2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ETLProcessor {
    private static final String INPUT_PATH = "data/products.csv";
    private static final String OUTPUT_PATH = "data/transformed_products.csv";

    public void processETL() {
        try {
            List<Product> products = extractData();
            List<Product> transformedProducts = transformData(products);
            loadData(transformedProducts);
        } catch (IOException e) {
            System.err.println("Error processing ETL pipeline: " + e.getMessage());
        }
    }

    private List<Product> extractData() throws IOException {
        List<Product> products = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_PATH))) {
            String line = reader.readLine();
            
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
