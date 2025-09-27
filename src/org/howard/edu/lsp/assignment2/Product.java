package org.howard.edu.lsp.assignment2;

public class Product {
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
