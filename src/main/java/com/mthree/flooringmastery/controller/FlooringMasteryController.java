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
        loadFiles();
        
        while (keepGoing) {
            int menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1 -> displayOrders();
                case 2 -> createOrder();
                case 3 -> editOrder();
                case 4 -> removeOrder();
                case 5 -> exportAllData();
                case 6 -> keepGoing = false;
                default -> unknownCommand();
            }
        }
        
        writeAllOrdersToBackupFile();
        exitMessage();
    }
    
    private void displayOrders() {
        List<Order> orders = service.getAllOrders();
        view.displayOrderListBanner();
        view.displayOrderList(orders);
    }
    
    private void displayOrders(String date) {
        List<Order> orders = service.getAllOrders(date);
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
        
    }
    
    private void removeOrder() {
        
    }
    
    private void exportAllData() {
        
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
    
    private void loadFiles() {
        if (service.loadFiles()) {
            view.displayLoadFilesSuccessfulBanner();
        } else {
            view.displayLoadFilesUnsuccessfulBanner();
        }
    }
}
