/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.flooringmastery.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDate;

/**
 *
 * @author Chuck
 */
public class OrderFile {
    private final String fileName;
    private static final String FILE_HEADER = 
            "OrderNumber::CustomerName::State::TaxRate::" + 
            "ProductType::Area::CostPerSquareFoot::" + 
            "LaborCostPerSquareFoot::MaterialCost:LaborCost::" + 
            "Tax::Total";
    private static final String DATE_FORMAT = "^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$";
    private static final Pattern PATTERN = Pattern.compile(DATE_FORMAT);
    
    public OrderFile(String date, boolean isComplete) {
        StringBuilder sb = new StringBuilder();
        if (!isComplete) {
            String[] dateElements = date.split("/");

            for (int i = 0; i < dateElements.length; i++) {
                if (dateElements[i].length() == 1) {
                    dateElements[i] = "0" + dateElements[i];
                }
            }

            sb.append("Orders/Orders_")
                    .append(dateElements[0])
                    .append(dateElements[1])
                    .append(dateElements[2])
                    .append(".txt");
            this.fileName = sb.toString();
        } else {
            this.fileName = sb.append("Orders/").append(date).toString();
        }
    } 
    
    public boolean createOrderFile() {
        try {
            File file = new File(fileName);
            file.createNewFile();
            
            PrintWriter out = new PrintWriter(new FileWriter(fileName));
            out.println(FILE_HEADER);
            out.flush();
            out.close();
            return true;
        } catch (IOException e) {
            // System.out.print("Could not create file! " + fileName + " " + e.getMessage());
            return false;
        }
    }
    
    public static boolean isCorrectDateFormat(String date) {
        Matcher matcher = PATTERN.matcher(date);
        
        if (matcher.matches()) {
            String[] monthDayYear = date.split("/");
            int[] monthDayYearAsInt = new int[3];
            
            for (int i = 0; i < monthDayYear.length; i ++) {
                monthDayYearAsInt[i] = Integer.parseInt(monthDayYear[i]);
            }
            
            LocalDate timeNow = LocalDate.now();
            LocalDate time = LocalDate.of(
                    monthDayYearAsInt[2], 
                    monthDayYearAsInt[0], 
                    monthDayYearAsInt[1]);
            
            if (timeNow.isBefore(time)) {             
                return true;
            }
        }
        
        return false;
    }
    
    public String getFileName() {
        return fileName;
    }
}
