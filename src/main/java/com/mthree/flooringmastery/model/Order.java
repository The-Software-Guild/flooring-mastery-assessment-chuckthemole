/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.flooringmastery.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private Product productType;
    private BigDecimal area;
    
    private static final String NAME_FORMAT = "([a-zA-Z]|[0-9]|,|\\.| )+";
    private static final Pattern PATTERN = Pattern.compile(NAME_FORMAT);
    
    public enum States {
        AL, AK, AZ, AR, CA, CO, CT, DE, DC, FL,
        GA, HI, ID, IL, IN, IA, KS, KY, LA, ME,
        MD, MA, MI, MN, MS, MO, MT, NB, NV, NH, 
        NJ, NM, NY, NC, ND, OH, OK, OR, PA, RI,
        SC, SD, TN, TX, UT, VT, VA, WA, WV, WI,
        WY
    }
    
    public Order(
            String orderDate, 
            String customerName,
            String state, 
            Product productType,
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
    
    public Product getProductType() {
        return productType;
    }
    
    public void setProductType(Product productType) {
        this.productType = productType;
    }
    
    public BigDecimal getArea() {
        return area;
    }
    
    public void setArea(BigDecimal area) {
        this.area = area;
    }
    
    public static boolean isCorrectDateFormat(String name) {
        Matcher matcher = PATTERN.matcher(name);
        return matcher.matches();
    }
    
    public static Set<String> getStates() {
        States[] states = States.values();
        Set<String> statesAsSet = new HashSet<>();
        
        for (States state : states) {
            statesAsSet.add(state.toString());
        }
        
        return statesAsSet;
    }
}
