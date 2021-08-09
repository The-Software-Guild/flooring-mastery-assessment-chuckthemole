/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.flooringmastery.service;

import com.mthree.flooringmastery.model.Order;
import com.mthree.flooringmastery.model.Product;
import java.util.List;

/**
 *
 * @author Chuck
 */
public interface FlooringMasteryServiceLayer {
    List<Order> getAllOrders();
    List<Product> getAllProducts();
    List<Order> getAllOrders(String date);
    String addOrder(Order order);
    Order removeOrder(String date, int orderNumber);
    String editOrder(Order newOrder, int oldOrderNumber);
    boolean writeAllOrdersToFiles();
    boolean writeAllOrdersToBackupFile();
}
