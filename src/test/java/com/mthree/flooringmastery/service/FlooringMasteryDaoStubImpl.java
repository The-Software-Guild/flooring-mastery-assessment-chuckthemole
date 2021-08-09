/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.flooringmastery.service;

import com.mthree.flooringmastery.dao.FlooringMasteryDao;
import com.mthree.flooringmastery.model.Order;
import com.mthree.flooringmastery.model.OrderFile;
import com.mthree.flooringmastery.model.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Chuck
 */
public class FlooringMasteryDaoStubImpl implements FlooringMasteryDao {
    private Map<Integer, Order> orders;
    private Map<String, Product> products;
    private Map<String, BigDecimal> taxes;
    private static Map<String, OrderFile> files;
    private static final String PRODUCT_FILE_PATH = "Data/Products.txt";
    private static final String BACKUP_FILE_PATH = "Backup/DataExport.txt";
    private static final String PATH_TO_ORDERS = "Orders/";
    private static final String PATH_TO_TAXES = "Data/Taxes.txt";
    private static final String FILE_HEADER = 
            "OrderNumber::CustomerName::State::TaxRate::" + 
            "ProductType::Area::CostPerSquareFoot::" + 
            "LaborCostPerSquareFoot::MaterialCost:LaborCost::" + 
            "Tax::Total";
    
    public FlooringMasteryDaoStubImpl() {
        orders = new HashMap<>();
        products = new HashMap<>();
        files = new HashMap<>();
        taxes = new HashMap<>();
        
        List<Product> productsFromFile = new ArrayList<>();
        Product onlyProduct = new Product("Wood", new BigDecimal("2.25"), new BigDecimal("1.50"));
        productsFromFile.add(onlyProduct);

        taxes.put("CA", new BigDecimal("10"));
        
        if (productsFromFile != null) {
            productsFromFile.forEach(product -> {
                products.put(product.getProductType(), product);
            });
        } else {
            products = null;
        }
        
        String date = "11/11/2021";
        files.put(date, new OrderFile(date, false));

        Order order = new Order(date, "Chuck","CA", onlyProduct, new BigDecimal("100"), 1);
        Order.decrementOrderCount();
        orders.put(1, order);
    }
    
    @Override
    public List<Order> getAllOrders() {
        List<Order> orderList = new ArrayList<>();
        orderList.addAll(orders.values());
        return orderList;
    }

    @Override
    public List<Order> getAllOrders(String date) {
        if (date.equals("11/11/2021")) {
            List<Order> orderList = new ArrayList<>();
            orderList.addAll(orders.values());
            return orderList;
        }
        return null; 
    }

    @Override
    public Map<String, BigDecimal> getAllTaxes() {
        return taxes;
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
    public Order editOrder(Order newOrder, int oldOrderNumber) {
        
        if (orders.containsKey(oldOrderNumber)) {
            if (orders.get(oldOrderNumber).getOrderDate().equals(newOrder.getOrderDate())) { 

                orders.get(oldOrderNumber).setCustomerName(newOrder.getCustomerName());
                orders.get(oldOrderNumber).setState(newOrder.getState());
                orders.get(oldOrderNumber).setProductType(newOrder.getProductType());
                orders.get(oldOrderNumber).setArea(newOrder.getArea());
                
                orders.get(oldOrderNumber).setTaxRate(new BigDecimal("10"));
                orders.get(oldOrderNumber).setMaterialCost(new BigDecimal("10"));
                orders.get(oldOrderNumber).setLaborCost(new BigDecimal("10"));
                orders.get(oldOrderNumber).setTax(new BigDecimal("10"));
                orders.get(oldOrderNumber).setTotal(new BigDecimal("10"));
                
                return orders.get(oldOrderNumber);
            }
        }
        
        return null;        
    }

    @Override
    public boolean createOrderFile(String date) {
        return true;
    }

    @Override
    public boolean isCorrectDateFormat(String date) {
        return true;
    }

    @Override
    public boolean writeOrderToFile(String file, Order order) {
        return true;
    }

    @Override
    public boolean writeAllOrdersToFiles() {
        return true;
    }

    @Override
    public boolean writeAllOrdersToBackupFile() {
        return true;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        productList.addAll(products.values());
        return productList;
    }
    
}
