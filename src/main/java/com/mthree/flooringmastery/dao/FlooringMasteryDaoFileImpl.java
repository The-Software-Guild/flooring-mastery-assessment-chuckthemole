/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.flooringmastery.dao;

import com.mthree.flooringmastery.model.Order;
import com.mthree.flooringmastery.model.OrderFile;
import com.mthree.flooringmastery.model.Product;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;

/**
 *
 * @author Chuck
 */
public class FlooringMasteryDaoFileImpl implements FlooringMasteryDao {
    private Map<Integer, Order> orders;
    private Map<String, Product> products;
    private static Map<String, OrderFile> files;
    private static final String PRODUCT_FILE_PATH = "Data/Products.txt";
    private static final String BACKUP_FILE_PATH = "Backup/DataExport.txt";
    private static final String PATH_TO_ORDERS = "Orders/";
    private static final String FILE_HEADER = 
            "OrderNumber::CustomerName::State::TaxRate::" + 
            "ProductType::Area::CostPerSquareFoot::" + 
            "LaborCostPerSquareFoot::MaterialCost:LaborCost::" + 
            "Tax::Total";
    
    public FlooringMasteryDaoFileImpl() {
        orders = new HashMap<>();
        products = new HashMap<>();
        files = new HashMap<>();
        
        List<Product> productsFromFile = loadProductsFromFile();
        
        if (productsFromFile != null) {
            productsFromFile.forEach(product -> {
                products.put(product.getProductType(), product);
            });
        } else {
            products = null;
        }
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
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }
    
    @Override
    public boolean addOrder(int orderNumber, Order order) {
        orders.put(orderNumber, order);
        writeOrderToFile(
                files.get(order.getOrderDate()).getFileName(), order);
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
        if (files.containsKey(date) || !OrderFile.isCorrectDateFormat(date)) {
            return false;
        }
        
        OrderFile file = new OrderFile(date, false);
        files.put(date, file);
        return file.createOrderFile();
    }

    @Override
    public boolean isCorrectDateFormat(String date) {
        return OrderFile.isCorrectDateFormat(date);
    }

    @Override
    public List<Product> loadProductsFromFile() {
        Scanner sc;
        List<Product> productsFromFile = new ArrayList<>();
        
        try {
            sc = new Scanner(new BufferedReader(new FileReader(PRODUCT_FILE_PATH)));
            sc.nextLine();
            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();
                String[] productData = currentLine.split(",");
                Product product = new Product(
                        productData[0], 
                        new BigDecimal(productData[1]), 
                        new BigDecimal(productData[2]));
                productsFromFile.add(product);
            }
            return productsFromFile;
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    @Override
    public boolean loadFiles() {        
        File folder = new File(PATH_TO_ORDERS);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                String date = parseDateFromFile(file.getName());
                if (date != null) {
                    System.out.println("Parsed name is " + date);
                    files.put(date, new OrderFile(file.getName(), true));
                    loadOrdersFromFile(file.getName());
                } else {
                    System.out.println("Could not load " + file.getName());
                }
            } 
        }
        
        return true;
    }
    
    @Override
    public boolean writeOrderToFile(String file, Order order) { 

        try {
            PrintWriter out = new PrintWriter(new FileWriter(file, true));
            out.println(order.getOrderNumber() + "::" + 
                    order.getCustomerName() + "::" + 
                    order.getState() + "::" +
                    //order.getTaxRate() + "::" +
                    order.getProductType().getProductType() + "::" +
                    order.getArea().toString() + "::" +
                    order.getProductType().getCostPerSquareFoot() + "::" +
                    order.getProductType().getLaborCostPerSquareFoot() + "::" //+
                    //order.getMaterialCost() + "::" +
                    //order.getLaborCost() + "::" +
                    //order.getTax() + "::" +
                    //order.getTotal()
                    );
            out.flush();
            out.close();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public boolean writeAllOrdersToBackupFile() {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(BACKUP_FILE_PATH));
                out.println(FILE_HEADER);
            
            int i = 0;
            while (true) {
                i++;
                if (!orders.containsKey(i)) {
                    System.out.println("Does not contain key " + i);
                    break;
                }
                Order order = orders.get(i);

                // PrintWriter out = new PrintWriter(new FileWriter(BACKUP_FILE_PATH, true));
                out.println(order.getOrderNumber() + "::" + 
                        order.getCustomerName() + "::" + 
                        order.getState() + "::" +
                        //order.getTaxRate() + "::" +
                        order.getProductType().getProductType() + "::" +
                        order.getArea().toString() + "::" +
                        order.getProductType().getCostPerSquareFoot() + "::" +
                        order.getProductType().getLaborCostPerSquareFoot() + "::" //+
                        //order.getMaterialCost() + "::" +
                        //order.getLaborCost() + "::" +
                        //order.getTax() + "::" +
                        //order.getTotal()
                        );           
            }
            out.flush();
            out.close(); 
            return true;
            
        } catch (IOException ex) {
                return false;
        }
    }
    
    private String parseDateFromFile(String file) {
        try {
        StringBuilder sb = new StringBuilder();
        String date = file.substring(file.indexOf("_") + 1, file.indexOf("."));
        return sb.append(date.charAt(0))
                .append(date.charAt(1))
                .append("/")
                .append(date.charAt(2))
                .append(date.charAt(3))
                .append("/")
                .append(date.charAt(4))
                .append(date.charAt(5))
                .append(date.charAt(6))
                .append(date.charAt(7))
                .toString();
        } catch (IndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    private void loadOrdersFromFile(String file) {
        Scanner sc;
        List<Product> ordersFromFile = new ArrayList<>();
        
        try {
            sc = new Scanner(new BufferedReader(new FileReader(file)));
            sc.nextLine();
            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();
                String[] orderData = currentLine.split("::");
                Order order = new Order(
                        parseDateFromFile(file), // orderDate
                        orderData[1], // customerName
                        orderData[2], // state
                        products.get(orderData[3]), // productType
                        new BigDecimal(orderData[4])); //area
                orders.put(Integer.parseInt(orderData[0]), order);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }    
    }
}