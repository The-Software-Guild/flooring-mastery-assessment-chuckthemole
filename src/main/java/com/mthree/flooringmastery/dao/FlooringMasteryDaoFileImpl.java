/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.flooringmastery.dao;

import com.mthree.flooringmastery.model.Order;
import com.mthree.flooringmastery.model.OrderFile;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Chuck
 */
public class FlooringMasteryDaoFileImpl implements FlooringMasteryDao {
    private Map<Integer, Order> orders;
    
    public FlooringMasteryDaoFileImpl() {
        orders = new HashMap<>();
    }

    @Override
    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }
    
    @Override
    public List<Order> getAllOrders(String date) {
        return new ArrayList<>(orders.values());
    }
    @Override
    public boolean addOrder(int orderNumber, Order order) {
        orders.put(orderNumber, order);
        return true;
    }

    @Override
    public Order removeOrder(String date, int orderNumber) {
        return orders.remove(orderNumber);
    }

    @Override
    public Order editOrder(String date, int orderNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean createOrderFile(String date) {
        if (OrderFile.doesFileExistWithDate(date)) {
            return false;
        }
        return (new OrderFile(date)).createOrderFile();
    }

    @Override
    public boolean isCorrectDateFormat(String date) {
        return OrderFile.isCorrectDateFormat(date);
    }
}
