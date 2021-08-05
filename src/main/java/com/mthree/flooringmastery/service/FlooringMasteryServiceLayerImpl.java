/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.flooringmastery.service;

import com.mthree.flooringmastery.dao.FlooringMasteryAuditDao;
import com.mthree.flooringmastery.dao.FlooringMasteryDao;
import com.mthree.flooringmastery.model.Order;
import com.mthree.flooringmastery.model.States;
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
    public String addOrder(Order order) {
        StringBuilder sb = new StringBuilder();
        
        if (order == null) {
            return sb.append("\nEnter a proper value for area").toString();
        }
        
        if (!dao.isCorrectDateFormat(order.getOrderDate())) {
            return sb.append("\nEnter the date in correct format (MM/DD/YYYY)").toString();
        }
        
        if (order.getCustomerName().equals("")) {
            return sb.append("\nCustomer name required!!").toString();
        }
        
        if (order.getState().length() != 2) {
            return sb.append("\nError: Enter state in correct format (Examples: CA, NJ, ME)").toString();
        }

        if (!States.getStates().contains(order.getState())) {
            return sb.append("\nError: Enter a valid US state!!").toString();
        }

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
    
}
