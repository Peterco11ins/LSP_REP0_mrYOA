package org.howard.edu.lsp.assignment3;

/**
 * Represents a product in the ETL pipeline with enhanced encapsulation.
 * This class models a product with its essential attributes and provides
 * methods for data transformation and price range calculation.
 * 
 * @author Peter Collins
 * @version 1.0
 * @since Assignment 3
 */
public class Product {
    private final int productId;
    private String name;
    private double price;
    private String category;
    private String priceRange;

    /**
     * Constructs a new Product with the specified attributes.
     * The price range is automatically calculated based on the price.
     * 
     * @param productId the unique identifier for the product
     * @param name the name of the product
     * @param price the price of the product
     * @param category the category the product belongs to
     */
    public Product(int productId, String name, double price, String category) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.priceRange = calculatePriceRange(price);
    }

    /**
     * Calculates the price range category based on the product's price.
     * 
     * @param price the price to categorize
     * @return the price range category: "Low" (≤$10), "Medium" (≤$100), 
     *         "High" (≤$500), or "Premium" (>$500)
     */
    private String calculatePriceRange(double price) {
        if (price <= 10.0) return "Low";
        if (price <= 100.0) return "Medium";
        if (price <= 500.0) return "High";
        return "Premium";
    }

    /**
     * Updates the price and recalculates the price range.
     * 
     * @param newPrice the new price for the product
     */
    public void updatePrice(double newPrice) {
        this.price = newPrice;
        this.priceRange = calculatePriceRange(newPrice);
    }

    /**
     * Gets the product ID.
     * 
     * @return the product ID
     */
    public int getProductId() { 
        return productId; 
    }

    /**
     * Gets the product name.
     * 
     * @return the product name
     */
    public String getName() { 
        return name; 
    }

    /**
     * Sets the product name.
     * 
     * @param name the new product name
     */
    public void setName(String name) { 
        this.name = name; 
    }

    /**
     * Gets the product price.
     * 
     * @return the product price
     */
    public double getPrice() { 
        return price; 
    }

    /**
     * Sets the product price and recalculates price range.
     * 
     * @param price the new product price
     */
    public void setPrice(double price) { 
        updatePrice(price);
    }

    /**
     * Gets the product category.
     * 
     * @return the product category
     */
    public String getCategory() { 
        return category; 
    }

    /**
     * Sets the product category.
     * 
     * @param category the new product category
     */
    public void setCategory(String category) { 
        this.category = category; 
    }

    /**
     * Gets the calculated price range.
     * 
     * @return the price range category
     */
    public String getPriceRange() { 
        return priceRange; 
    }

    /**
     * Returns a string representation of the product.
     * 
     * @return a formatted string containing all product attributes
     */
    @Override
    public String toString() {
        return String.format("Product{id=%d, name='%s', price=%.2f, category='%s', priceRange='%s'}",
                productId, name, price, category, priceRange);
    }
}
