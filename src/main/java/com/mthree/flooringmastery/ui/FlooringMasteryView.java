/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.flooringmastery.ui;

import com.mthree.flooringmastery.model.Order;
import com.mthree.flooringmastery.model.Product;
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
        io.print("");
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
    
    public void displayWriteToBackupSuccessfulBanner() {
        io.print("\n* * * * * * Write to backup file successful * * * * * *");
    }
    
    public void displayWriteToBackupUnsuccessfulBanner() {
        io.print("\n* * * * * * Write to backup file unsuccessful * * * * * *");
    }
    
    public void displayWriteToFilesSuccessfulBanner() {
        io.print("\n* * * * * * Write to files successful * * * * * *");
    }
    
    public void displayWriteToFilesUnsuccessfulBanner() {
        io.print("\n* * * * * * Write to files unsuccessful * * * * * *");
    }    
    public void displayLoadFilesSuccessfulBanner() {
        io.print("\n* * * * * * Load files successful * * * * * *");
    }
    
    public void displayLoadFilesUnsuccessfulBanner() {
        io.print("\n* * * * * * Load files unsuccessful * * * * * *");
    }   

    public void displayRemoveOrderSuccessfulBanner() {
        io.print("\n* * * * * * Remove Order Successful * * * * * *");
    }

    public void displayRemoveOrderUnsuccessfulBanner() {
        io.print("\n* * * * * * Remove Order Unsuccessful * * * * * *");
    }   
    
    public void displayEditOrderSuccessfulBanner() {
        io.print("\n* * * * * * Found Order to Edit * * * * * *");
    }  
    
    public void displayEditOrderUnsuccessfulBanner() {
        io.print("\n* * * * * * Can't Find Order to Edit * * * * * *");
    } 
    
    public void print(String s) {
        io.print(s);
    }
    
    public void displayOrderList(List<Order> orders) {
        orders.stream().map(order -> String.format(
                "* %s \n  Order Number: %s  Name: %s  State: %s  Product: %s  Area: %s  " +
                        "Material Cost: $%s  Labor Cost: $%s  Tax: $%s  Total: $%s",
                order.getOrderDate(),
                Integer.toString(order.getOrderNumber()),
                order.getCustomerName(),
                order.getState(),
                order.getProductType().getProductType(),
                order.getArea().toString(),
                order.getMaterialCost().toString(),
                order.getLaborCost().toString(),
                order.getTax().toString(),
                order.getTotal().toString()               
        )).forEachOrdered(orderInfo -> {
            io.print(orderInfo);
        });
        io.readString("Please hit enter to continue.");
    }
    
    public void displayOrderListByDate(List<Order> orders) {       
        orders.stream().map(order -> String.format(
                "* Order Number: %s  Name: %s  State: %s  Product: %s  Area: %s  " +
                        "Material Cost: $%s  Labor Cost: $%s  Tax: $%s  Total: $%s",
                Integer.toString(order.getOrderNumber()),
                order.getCustomerName(),
                order.getState(),
                order.getProductType(),
                order.getArea().toString(),
                order.getMaterialCost().toString(),
                order.getLaborCost().toString(),
                order.getTax().toString(),
                order.getTotal().toString()
        )).forEachOrdered(orderInfo -> {
            io.print(orderInfo);
        });
        io.readString("Please hit enter to continue.");
    }

    public Order getNewOrderInfo(List<Product> products) {
        String date = io.readString("Please enter the order date (MM/DD/YYYY)");
        String name = io.readString("Please enter customer name");
        String state = io.readString("Please enter state (Example: CA)");
        for (int i = 0; i < products.size(); i++) {
            io.print((i + 1) + ". " + 
                    "Type: " + 
                    products.get(i).getProductType() + 
                    "  Cost/sqr ft: $" + 
                    products.get(i).getCostPerSquareFoot() + 
                    "  Labor Cost/sqr ft: $" + 
                    products.get(i).getLaborCostPerSquareFoot());
        }
        int type = io.readInt("Please select a product", 1, products.size());
        String area = io.readString("Please enter the area (100 or greater)");
        try {
            Order order = new Order(
                    date, 
                    name.toUpperCase(), 
                    state.toUpperCase(), 
                    products.get(type - 1), 
                    new BigDecimal(area));
            return order;
        } catch (NumberFormatException e) {
            // io.print(e.getMessage());
            return null;
        }
    }
    
    public String getRemoveOrderInfo() {
        String date = io.readString("Please enter the order date (MM/DD/YYYY)");
        String number = io.readString("Please enter the order number");
        StringBuilder sb = new StringBuilder();
        return sb.append(date).append("::").append(number).toString();
    }
    
    public String getEditOrderDateOrderNumber() {
        String date = io.readString("Please enter the order date (MM/DD/YYYY)");
        String number = io.readString("Please enter the order number");
        StringBuilder sb = new StringBuilder();
        return sb.append(date).append("::").append(number).toString();
    }
    
    public Order getEditOrderInfo(Order oldOrder, List<Product> products) {
        String name = io.readString("Enter customer name (" + oldOrder.getCustomerName() + ")");
        if (name.equals("")) {
            name = oldOrder.getCustomerName();
        }
        
        String state = io.readString("Enter a state (" + oldOrder.getState() + ")");
        if (state.equals("")) {
            state = oldOrder.getState();
        }
        
        for (int i = 0; i < products.size(); i++) {
            io.print((i + 1) + ". " + 
                    "Type: " + 
                    products.get(i).getProductType() + 
                    "  Cost/sqr ft: $" + 
                    products.get(i).getCostPerSquareFoot() + 
                    "  Labor Cost/sqr ft: $" + 
                    products.get(i).getLaborCostPerSquareFoot());
        }
        int type = io.readInt("Please select a product(" + oldOrder.getProductType().getProductType() + ")", 1, products.size());
        
        String area = io.readString("Please enter the area (" + oldOrder.getArea().toString() + ")");
        if (area.equals("")) {
            area = oldOrder.getArea().toString();
        }
        
        try {
            Order.decrementOrderCount();
            Order order = new Order(
                    oldOrder.getOrderDate(), 
                    name.toUpperCase(), 
                    state.toUpperCase(), 
                    products.get(type - 1), 
                    new BigDecimal(area),
                    oldOrder.getOrderNumber());
            return order;
        } catch (NumberFormatException e) {
            // io.print(e.getMessage());
            return null;
        }
    }
}
