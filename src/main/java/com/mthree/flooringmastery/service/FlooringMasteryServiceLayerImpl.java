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
            return "\nError: Enter proper values!";
        }
        
        if (!dao.isCorrectDateFormat(order.getOrderDate())) {
            return "\nError: Enter the date in correct format (MM/DD/YYYY) and as a future date!";
        }
        
        if (!Order.isCorrectDateFormat(order.getCustomerName())) {
            return "\nError: Enter a valid customer name!";
        }
        
        if (order.getState().length() != 2) {
            return "\nError: Enter state in correct format (Examples: CA, NJ, ME)";
        }

        if (!Order.getStates().contains(order.getState())) {
            return "\nError: Enter a valid US state!!";
        }
        
        StringBuilder sb = new StringBuilder();

        if (dao.createOrderFile(order.getOrderDate())) {
            sb.append("File created for ").append(order.getOrderDate());
        } 

        if (dao.addOrder(order.getOrderNumber(), order)) {
            sb.append("\nOrder added!");
        }

        return sb.toString();
    }

    @Override
    public Order removeOrder(String date, int orderNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order editOrder(String date, int orderNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public boolean loadFiles() {
        if(dao.loadFiles()) {
            return true;
        } else {
            return false;
        }
    }
}
