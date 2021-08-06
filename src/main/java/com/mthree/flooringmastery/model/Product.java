/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.flooringmastery.model;

import java.math.BigDecimal;

/**
 *
 * @author Chuck
 */
public class Product {
    private final String productType;
    private final BigDecimal costPerSquareFoot;
    private final BigDecimal laborCostPerSquareFoot;
    
    public Product(
            String productType, 
            BigDecimal costPerSquareFoot, 
            BigDecimal laborCostPerSquareFoot) {
        this.productType = productType;
        this.costPerSquareFoot = costPerSquareFoot;
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }
    
    public Product(Product product) {
        this.productType = product.productType;
        this.costPerSquareFoot = product.costPerSquareFoot;
        this.laborCostPerSquareFoot = product.laborCostPerSquareFoot;
    }
    
    public Product(String product) {
        String[] productElements = product.split(",");
        this.productType = productElements[0];
        this.costPerSquareFoot = new BigDecimal(productElements[1]);
        this.laborCostPerSquareFoot = new BigDecimal(productElements[2]);
    }
    
    public String getProductType() {
        return productType;
    }
    
    public BigDecimal getCostPerSquareFoot() {
        return costPerSquareFoot;
    }
    
    public BigDecimal getLaborCostPerSquareFoot() {
        return laborCostPerSquareFoot;
    }
}
