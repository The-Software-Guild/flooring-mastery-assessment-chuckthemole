/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.flooringmastery.service;

import com.mthree.flooringmastery.dao.FlooringMasteryAuditDao;
import com.mthree.flooringmastery.dao.FlooringMasteryDao;
import com.mthree.flooringmastery.model.Order;
import com.mthree.flooringmastery.model.Product;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Chuck
 */
public class FlooringMasteryServiceLayerImpl implements 
        FlooringMasteryServiceLayer {
    FlooringMasteryDao dao;
    FlooringMasteryAuditDao auditDao;

    public FlooringMasteryServiceLayerImpl(
            FlooringMasteryDao dao, 
            FlooringMasteryAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public List<Order> getAllOrders() {
        return dao.getAllOrders();
    }
    
    @Override
    public List<Order> getAllOrders(String date) {
        return dao.getAllOrders(date);
    }
    
    @Override
    public List<Product> getAllProducts() {
        List<Product> products = dao.getAllProducts();
        if (products != null) {
            return products;
        } else {
            return null;
        }
    }

    @Override
    public String addOrder(Order order) {
        
        if (order == null) {
            Order.decrementOrderCount();
            return "\nError: Enter proper values!";
        }
        
        if (!dao.isCorrectDateFormat(order.getOrderDate())) {
            Order.decrementOrderCount();
            return "\nError: Enter the date in correct format (MM/DD/YYYY) and as a future date!";
        }
        
        if (!Order.isCorrectDateFormat(order.getCustomerName())) {
            Order.decrementOrderCount();
            return "\nError: Enter a valid customer name!";
        }
        
        if (order.getState().length() != 2) {
            Order.decrementOrderCount();
            return "\nError: Enter state in correct format (Examples: CA, NJ, ME)";
        }

        if (!Order.getStates().contains(order.getState())) {
            Order.decrementOrderCount();
            return "\nError: Enter a valid US state!!";
        }
        
        if (order.getArea().compareTo(BigDecimal.TEN.multiply(BigDecimal.TEN)) < 0) {
            return "\nError: Enter an area 100 or greater!!";
        }
        
        // Success adding order
        if (dao.addOrder(order.getOrderNumber(), order)) {
            return "\nOrder added!";
        }

        return "\nError adding order!";
    }

    @Override
    public Order removeOrder(String date, int orderNumber) {
        List<Order> orders = dao.getAllOrders();
        
        for (Order order : orders) {
            if (order.getOrderDate().equals(date) && order.getOrderNumber() == orderNumber) {
                return dao.removeOrder(date, orderNumber);
            }
        }
        
        return null;
    }

    @Override
    public String editOrder(Order newOrder, int oldOrderNumber) {
        
        if (newOrder == null) {
            return "\nError: Enter proper values!";
        }
        
        if (!dao.isCorrectDateFormat(newOrder.getOrderDate())) {
            return "\nError: Enter the date in correct format (MM/DD/YYYY) and as a future date!";
        }
        
        if (!Order.isCorrectDateFormat(newOrder.getCustomerName())) {
            return "\nError: Enter a valid customer name!";
        }
        
        if (newOrder.getState().length() != 2) {
            return "\nError: Enter state in correct format (Examples: CA, NJ, ME)";
        }

        if (!Order.getStates().contains(newOrder.getState())) {
            return "\nError: Enter a valid US state!!";
        }
        
        if (newOrder.getArea().compareTo(BigDecimal.TEN.multiply(BigDecimal.TEN)) < 0) {
            return "\nError: Enter an area 100 or greater!!";
        }
        
        Order order = dao.editOrder(newOrder, oldOrderNumber);

        if (order != null) {
            return "\nEdit order successful!";
        }
        return "\nError editting order!";
    }

    @Override
    public boolean writeAllOrdersToBackupFile() {
        if (dao.writeAllOrdersToBackupFile()) {
            return true;
        }
        // Throw exception
        return false;
    }
    
    @Override
    public boolean writeAllOrdersToFiles() {
        if (dao.writeAllOrdersToFiles()) {
            return true;
        }
        // Throw exception
        return false;
        
    }
}
