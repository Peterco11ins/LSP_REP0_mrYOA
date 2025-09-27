package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Handles data transformation logic for products.
 * This class encapsulates all transformation rules and applies them
 * to a list of products according to the business requirements.
 * 
 * @author Peter Collins
 * @version 1.0
 * @since Assignment 3
 */
public class ProductTransformer {
    private static final double ELECTRONICS_DISCOUNT_RATE = 0.9;
    private static final double PREMIUM_ELECTRONICS_THRESHOLD = 500.0;
    private static final String ELECTRONICS_CATEGORY = "Electronics";
    private static final String PREMIUM_ELECTRONICS_CATEGORY = "Premium Electronics";

    /**
     * Transforms a list of products according to the business rules.
     * Applies name capitalization and electronics-specific transformations.
     * 
     * @param products the list of products to transform
     * @return the transformed list of products (same objects, modified in place)
     */
    public List<Product> transformProducts(List<Product> products) {
        for (Product product : products) {
            transformProduct(product);
        }
        return products;
    }

    /**
     * Transforms a single product according to the business rules.
     * 
     * @param product the product to transform
     */
    private void transformProduct(Product product) {
        // Rule 1: Convert name to uppercase
        product.setName(product.getName().toUpperCase());

        // Rule 2: Apply electronics-specific transformations
        if (isElectronicsProduct(product)) {
            applyElectronicsTransformations(product);
        }
    }

    /**
     * Checks if a product belongs to the Electronics category.
     * 
     * @param product the product to check
     * @return true if the product is in Electronics category
     */
    private boolean isElectronicsProduct(Product product) {
        return ELECTRONICS_CATEGORY.equals(product.getCategory());
    }

    /**
     * Applies electronics-specific transformation rules.
     * 
     * @param product the electronics product to transform
     */
    private void applyElectronicsTransformations(Product product) {
        // Rule 2a: Upgrade to Premium Electronics if price > $500
        if (product.getPrice() > PREMIUM_ELECTRONICS_THRESHOLD) {
            product.setCategory(PREMIUM_ELECTRONICS_CATEGORY);
        }

        // Rule 2b: Apply 10% discount
        double discountedPrice = product.getPrice() * ELECTRONICS_DISCOUNT_RATE;
        double roundedPrice = roundToTwoDecimals(discountedPrice);
        product.setPrice(roundedPrice);
    }

    /**
     * Rounds a double value to two decimal places using HALF_UP rounding mode.
     * 
     * @param value the value to round
     * @return the rounded value with exactly 2 decimal places
     */
    private double roundToTwoDecimals(double value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * Gets the electronics discount rate.
     * 
     * @return the discount rate for electronics products
     */
    public double getElectronicsDiscountRate() {
        return ELECTRONICS_DISCOUNT_RATE;
    }

    /**
     * Gets the premium electronics price threshold.
     * 
     * @return the price threshold for premium electronics classification
     */
    public double getPremiumElectronicsThreshold() {
        return PREMIUM_ELECTRONICS_THRESHOLD;
    }
}
