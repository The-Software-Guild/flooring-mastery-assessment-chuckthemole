/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.flooringmastery.dao;

import com.mthree.flooringmastery.model.Order;
import com.mthree.flooringmastery.model.Product;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Chuck
 */
public interface FlooringMasteryDao {
    List<Order> getAllOrders();
    List<Order> getAllOrders(String date);
    Map<String, BigDecimal> getAllTaxes();
    boolean addOrder(int orderNumber, Order order);
    Order removeOrder(String date, int orderNumber);
    Order editOrder(Order newOrder, int oldOrderNumber);
    boolean createOrderFile(String date);
    boolean isCorrectDateFormat(String date);
    public boolean writeOrderToFile(String file, Order order);
    public boolean writeAllOrdersToFiles();
    public boolean writeAllOrdersToBackupFile();
    List<Product> getAllProducts();
}
