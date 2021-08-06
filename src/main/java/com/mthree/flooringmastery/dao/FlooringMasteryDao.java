/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.flooringmastery.dao;

import com.mthree.flooringmastery.model.Order;
import com.mthree.flooringmastery.model.Product;
import java.util.List;

/**
 *
 * @author Chuck
 */
public interface FlooringMasteryDao {
    List<Order> getAllOrders();
    List<Order> getAllOrders(String date);
    boolean addOrder(int orderNumber, Order order);
    Order removeOrder(String date, int orderNumber);
    Order editOrder(String date, int orderNumber);
    boolean createOrderFile(String date);
    boolean isCorrectDateFormat(String date);
    List<Product> loadProductsFromFile();
    boolean loadFiles();
    public boolean writeOrderToFile(String file, Order order);
    public boolean writeAllOrdersToBackupFile();
    List<Product> getAllProducts();
}
