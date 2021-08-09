/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.flooringmastery.model;

import java.math.BigDecimal;
import java.util.Objects;

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
    
    @Override
    public boolean equals(Object o) {
        Product order = (Product) o;
        
        if (this.productType.equals(order.productType) &&
                this.costPerSquareFoot.equals(order.costPerSquareFoot) &&
                this.laborCostPerSquareFoot.equals(order.laborCostPerSquareFoot)) {
            return true;
        }
        
        return false;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.productType);
        hash = 89 * hash + Objects.hashCode(this.costPerSquareFoot);
        hash = 89 * hash + Objects.hashCode(this.laborCostPerSquareFoot);
        return hash;
    }
}
