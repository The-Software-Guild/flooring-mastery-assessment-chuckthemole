/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.flooringmastery.ui;

import com.mthree.flooringmastery.model.Order;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Chuck
 */
public class FlooringMasteryView {
    private UserIO io;
    
    public FlooringMasteryView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        io.print("* <<Flooring Program>>");
        io.print("* 1. Display Orders");
        io.print("* 2. Add an Order");
        io.print("* 3. Edit an Order");
        io.print("* 4. Remove an Order");
        io.print("* 5. Export All Data");
        io.print("* 6. Quit");
        io.print("*");
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        io.print(""); 

        return io.readInt("Please select from the above choices.", 1, 6);
    }
    
    public void displayOrderListBanner() {
        io.print("* * * * * * All Orders * * * * * *");
    }
    
    public void displayOrderListByDateBanner(String date) {
        io.print("* * * * * * " + date + " * * * * * *");
    }
    
    public void displayCreateOrderBanner() {
        io.print("\n* * * * * * Create Order * * * * * *");
    }
    
    public void displayEditOrderBanner() {
        io.print("\n* * * * * * Edit Order * * * * * *");
    }
    
    public void displayRemoveOrderBanner() {
        io.print("\n* * * * * * Remove Order * * * * * *");
    }
    
    public void displayExportAllDataBanner() {
        io.print("\n* * * * * * Export All Data * * * * * *");
    }
    
    public void displayOrderList(List<Order> orders) {
        orders.stream().map(order -> String.format(
                "* %s \n  Order Number: %s  Name: %s  State: %s  Product: %s  Area: %s",
                order.getOrderDate(),
                Integer.toString(order.getOrderNumber()),
                order.getCustomerName(),
                order.getState(),
                order.getProductType(),
                order.getArea().toString()
        )).forEachOrdered(orderInfo -> {
            io.print(orderInfo);
        });
        io.readString("Please hit enter to continue.");
    }
    
    public void displayOrderListByDate(List<Order> orders) {       
        orders.stream().map(order -> String.format(
                "* Order Number: %s  Name: %s  State: %s  Product: %s  Area: %s",
                Integer.toString(order.getOrderNumber()),
                order.getCustomerName(),
                order.getState(),
                order.getProductType(),
                order.getArea().toString()
        )).forEachOrdered(orderInfo -> {
            io.print(orderInfo);
        });
        io.readString("Please hit enter to continue.");
    }

    public Order getNewOrderInfo() {
        String date = io.readString("Please enter the order date (MM/DD/YYYY)");
        String name = io.readString("Please enter customer name (FIRST LAST)");
        String state = io.readString("Please enter state (Example: CA)");
        String type = io.readString("Please enter the product type");
        String area = io.readString("Please enter the area");
        try {
            Order order = new Order(date, name, state, type, new BigDecimal(area));
            return order;
        } catch (NumberFormatException e) {
            // io.print(e.getMessage());
            return null;
        }
    }
    
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    
    public void displayCreateSuccessBanner() {
        io.print("\n* * * * * * Create Order Successful * * * * * *");
    }
    
    public void displayCreateUnsuccessfulBanner() {
        io.print("\n* * * * * * Create Order Unsuccessful * * * * * *");
    }
    
    public void print(String s) {
        io.print(s);
    }
}
