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
public class Tax {
    private final String stateAbbreviation;
    private final String state;
    private final BigDecimal taxRate;
    
    public Tax(String stateAbbreviation, String state, BigDecimal taxRate) {
        this.stateAbbreviation = stateAbbreviation;
        this.state = state;
        this.taxRate = taxRate;
    }
    
    public String getStateAbbreviation() {
        return stateAbbreviation;
    }
    
    public String getState() {
        return state;
    }
    
    public BigDecimal getTaxRate() {
        return taxRate;
    }
}
