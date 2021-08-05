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
public class Order {
    private static int orderCount = 0;
    private final int orderNumber;
    private final String orderDate;
    private String customerName;
    private String state;
    private String productType;
    private BigDecimal area;
    
    public Order(
            String orderDate, 
            String customerName,
            String state, 
            String productType,
            BigDecimal area) {
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.state = state;
        this.productType = productType;
        this.area = area;
        Order.orderCount++;
        this.orderNumber = orderCount;
    }
    
    public String getOrderDate() {
        return orderDate;
    }
    
    public int getOrderNumber() {
        return orderNumber;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public String getProductType() {
        return productType;
    }
    
    public void setProductType(String productType) {
        this.productType = productType;
    }
    
    public BigDecimal getArea() {
        return area;
    }
    
    public void setArea(BigDecimal area) {
        this.area = area;
    }
}
