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
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Chuck
 */
public class FlooringMasteryDaoFileImpl implements FlooringMasteryDao {
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
    
    public FlooringMasteryDaoFileImpl() {
        orders = new HashMap<>();
        products = new HashMap<>();
        files = new HashMap<>();
        taxes = new HashMap<>();
        
        List<Product> productsFromFile = loadProductsFromFile();
        loadTaxes();
        
        if (productsFromFile != null) {
            productsFromFile.forEach(product -> {
                products.put(product.getProductType(), product);
            });
        } else {
            products = null;
        }
        
        loadFiles();      
    }
    
    public FlooringMasteryDaoFileImpl(boolean isTest) {
        orders = new HashMap<>();
        products = new HashMap<>();
        files = new HashMap<>();
        taxes = new HashMap<>();
        
        List<Product> productsFromFile = loadProductsFromFile();
        loadTaxes();

        if (productsFromFile != null) {
            productsFromFile.forEach(product -> {
                products.put(product.getProductType(), product);
            });
        } else {
            products = null;
        }

        if (!isTest) {
            loadFiles();      
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
    public Map<String, BigDecimal> getAllTaxes() {
        return taxes;
    }    
    
    @Override
    public boolean addOrder(int orderNumber, Order order) {
        
        order.setTaxRate(taxes.get(order.getState()));
        order.setMaterialCost(getMaterialCost(order));
        order.setLaborCost(getLaborCost(order));
        order.setTax(getTax(order));
        order.setTotal(getTotal(order));
        orders.put(orderNumber, order);

        return true;
    }
    
    private BigDecimal getMaterialCost(Order order) {
        return order
                .getProductType()
                .getCostPerSquareFoot()
                .multiply(order.getArea());
    }
    
    private BigDecimal getLaborCost(Order order) {
        return order
                .getProductType()
                .getLaborCostPerSquareFoot()
                .multiply(order.getArea());
    }
    
    private BigDecimal getTax(Order order) {
        return (order.getMaterialCost()
                .add(order.getLaborCost()))
                .multiply(order.getTaxRate()
                        .divide(BigDecimal.TEN)
                        .divide(BigDecimal.TEN));
    }
    
    private BigDecimal getTotal(Order order) {
        return order.getMaterialCost()
                .add(order.getLaborCost())
                .add(order.getTax());
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
                
                orders.get(oldOrderNumber).setTaxRate(taxes.get(orders.get(oldOrderNumber).getState()));
                orders.get(oldOrderNumber).setMaterialCost(getMaterialCost(orders.get(oldOrderNumber)));
                orders.get(oldOrderNumber).setLaborCost(getLaborCost(orders.get(oldOrderNumber)));
                orders.get(oldOrderNumber).setTax(getTax(orders.get(oldOrderNumber)));
                orders.get(oldOrderNumber).setTotal(getTotal(orders.get(oldOrderNumber)));
                
                return orders.get(oldOrderNumber);
            }
        }
        
        return null;
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
    
    private void loadOrdersFromFile(String file) {
        Scanner sc;
        
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
                        products.get(orderData[4]), // productType
                        new BigDecimal(orderData[5]), // area
                        Integer.parseInt(orderData[0])); // orderNumber
                order.setTaxRate(taxes.get(order.getState()));
                order.setMaterialCost(getMaterialCost(order));
                order.setLaborCost(getLaborCost(order));
                order.setTax(getTax(order));
                order.setTotal(getTotal(order));
                orders.put(Integer.parseInt(orderData[0]), order);
                
                //System.out.println("\nSuccess loading file: ");
                //System.out.println(file);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("\nFailure loading file: ");
            System.out.println(ex.getMessage());
            System.out.println();
        }    
    }
    
    public boolean loadFiles() {        
        File folder = new File(PATH_TO_ORDERS);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                String date = parseDateFromFile(file.getName());
                if (date != null) {
                    //System.out.println("Parsed name is " + date);
                    files.put(date, new OrderFile(file.getName(), true));
                    loadOrdersFromFile(PATH_TO_ORDERS + file.getName());           
                } else {
                    System.out.println("Could not load " + file.getName());
                }
            } 
        }
        
        for(Order order : orders.values()) {
            if (order.getOrderNumber() > Order.getOrderCount()) {
                Order.setOrderCount(order.getOrderNumber());
            }
        }
        //System.out.println("Order count is: " + Order.getOrderCount());
        
        return true;
    }
    
    public boolean loadTaxes() {
        File file = new File(PATH_TO_TAXES);
        Scanner sc;
        
        try {
            sc = new Scanner(new BufferedReader(new FileReader(file)));
            sc.nextLine();
            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();
                String[] taxData = currentLine.split(",");
                
                taxes.put(taxData[0], new BigDecimal(taxData[2]));
                
                //System.out.println("\nSuccess loading tax file: ");
                //System.out.println(file);
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println("\nFailure loading tax file: ");
            System.out.println(ex.getMessage());
            System.out.println();
        } 
        
        return true;
    }
    
    @Override
    public boolean writeOrderToFile(String file, Order order) { 

        try {
            PrintWriter out = new PrintWriter(new FileWriter(file, true));
            out.println(getOrderAsStringForFile(order));
            out.flush();
            out.close();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
    
    @Override
    public boolean writeAllOrdersToFiles() {
        deleteAllOrderFiles();
        files = new HashMap<>();
        for (Order order : orders.values()) {          
            createOrderFile(order.getOrderDate());
            writeOrderToFile(
                files.get(order.getOrderDate()).getFileName(), order);
        }
        return true;
    }

    @Override
    public boolean writeAllOrdersToBackupFile() {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(BACKUP_FILE_PATH));
            out.println(FILE_HEADER);
            
            List<String> orderList = new ArrayList<>();
            for (Order order : orders.values()) {
                orderList.add(getOrderAsStringForFile(order));
            }
            
            Collections.sort(orderList);
            
            for (String order : orderList) {
                out.println(order);
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
    
    private String getOrderAsStringForFile(Order order) {
        StringBuilder sb = new StringBuilder();
        
        sb.append(order.getOrderNumber())
                .append("::")
                .append(order.getCustomerName())
                .append("::")
                .append(order.getState())
                .append("::")
                .append(order.getTaxRate())
                .append("::")
                .append(order.getProductType().getProductType())
                .append("::")
                .append(order.getArea().toString())
                .append("::")
                .append(order.getProductType().getCostPerSquareFoot())
                .append("::")
                .append(order.getProductType().getLaborCostPerSquareFoot())
                .append("::")
                .append(order.getMaterialCost())
                .append("::")
                .append(order.getLaborCost())
                .append("::")
                .append(order.getTax())
                .append("::")
                .append(order.getTotal());
        
        return sb.toString();
    }
    
    private boolean deleteAllOrderFiles() {
        for(File file: (new File(PATH_TO_ORDERS)).listFiles()) {
            file.delete();
        }
        return true;
    }
}