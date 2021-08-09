/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.flooringmastery.controller;

import com.mthree.flooringmastery.model.Order;
import com.mthree.flooringmastery.model.Product;
import com.mthree.flooringmastery.service.FlooringMasteryServiceLayer;
import com.mthree.flooringmastery.ui.FlooringMasteryView;
import java.util.List;

/**
 *
 * @author Chuck
 */
public class FlooringMasteryController {
    private FlooringMasteryView view;
    private FlooringMasteryServiceLayer service;


    public FlooringMasteryController(FlooringMasteryServiceLayer service, FlooringMasteryView view) {
        this.service = service;
        this.view = view;
    }
    
    public void run() {
        
        boolean keepGoing = true;
        
        while (keepGoing) {
            int menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1 -> displayOrdersFromDate();
                case 2 -> createOrder();
                case 3 -> editOrder();
                case 4 -> removeOrder();
                case 5 -> exportAllData();
                case 6 -> keepGoing = false;
                default -> unknownCommand();
            }
        }
        
        // writeAllOrdersToBackupFile();
        writeAllOrdersToFiles();
        exitMessage();
    }
    
    private void displayOrders() {
        List<Order> orders = service.getAllOrders();
        view.displayOrderListBanner();
        view.displayOrderList(orders);
    }
    
    private void displayOrdersFromDate() {
        view.displayOrderListBanner();
        String date = view.getOrderDate();
        
        List<Order> orders = service.getOrdersFromDate(date);
        if (orders != null) {
            view.displayOrderList(orders);
        } else {
            view.displayDisplayListUnsuccessfulBanner();
        }
    }
    
    private void displayOrders(String date) {
        List<Order> orders = service.getOrdersFromDate(date);
        view.displayOrderListByDateBanner(date);
        view.displayOrderListByDate(orders);
    }
    
    private void createOrder() {
        view.displayCreateOrderBanner();
        List<Product> products = service.getAllProducts();
        if (products != null) {
            Order newOrder = view.getNewOrderInfo(service.getAllProducts());
            view.print(service.addOrder(newOrder));
        } else {
            view.displayCreateUnsuccessfulBanner();
        }
    }
    
    private void editOrder() {
        view.displayEditOrderBanner();
        String dateAndOrderNumber = view.getEditOrderDateOrderNumber();
        String[] dateAndOrderNumberSplit = dateAndOrderNumber.split("::");
        
        List<Order> orders = service.getOrdersFromDate(dateAndOrderNumberSplit[0]);
        Order foundOrder = null;
        for(Order order : orders) {
            if (order.getOrderNumber() == Integer.parseInt(dateAndOrderNumberSplit[1])) {
                foundOrder = order;
                break;
            }
        }
        
        if (foundOrder != null) {
            view.displayEditOrderSuccessfulBanner();
            List<Product> products = service.getAllProducts();
            Order order = view.getEditOrderInfo(foundOrder, products);
            view.print(service.editOrder(order, order.getOrderNumber()));
        } else {
            view.displayEditOrderUnsuccessfulBanner();
        }
    }
    
    private void removeOrder() {
        view.displayRemoveOrderBanner();
        String info = view.getRemoveOrderInfo();
        String[] splitInfo = info.split("::");
        if (service.removeOrder(splitInfo[0], Integer.parseInt(splitInfo[1])) != null) {
            view.displayRemoveOrderSuccessfulBanner();
        } else {
            view.displayRemoveOrderUnsuccessfulBanner();
        } 
    }
    
    private void exportAllData() {
        if(service.writeAllOrdersToBackupFile()) {
            view.displayWriteToBackupSuccessfulBanner();
        } else {
            view.displayWriteToBackupUnsuccessfulBanner();
        }
    }
    
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
    private void exitMessage() {
        view.displayExitBanner();
    }
    
    private void writeAllOrdersToBackupFile() {
        if (service.writeAllOrdersToBackupFile()) {
            view.displayWriteToBackupSuccessfulBanner();
        } else {
            view.displayWriteToBackupUnsuccessfulBanner();
        }
    }
    
    private void writeAllOrdersToFiles() {
        if (service.writeAllOrdersToFiles()) {
            view.displayWriteToFilesSuccessfulBanner();
        } else {
            view.displayWriteToFilesUnsuccessfulBanner();
        }
    }
}
