/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.flooringmastery.model;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Chuck
 */
public class OrderFile {
    private final String fileName;
    private static Set<String> files = new HashSet<>();
    private static final String DATE_FORMAT = "^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$";
    private static final Pattern PATTERN = Pattern.compile(DATE_FORMAT);
    
    public OrderFile(String date) {
        StringBuilder sb = new StringBuilder();
        sb.append("Orders_").append(date).append(".txt");
        this.fileName = sb.toString();
        OrderFile.files.add(date);
    } 
    
    public boolean createOrderFile() {
        try {
            File file = new File(fileName);
            file.createNewFile();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    
    public static boolean doesFileExistWithDate(String date) {
        return files.contains(date);
    }
    
    public static boolean isCorrectDateFormat(String date) {
        Matcher matcher = PATTERN.matcher(date);
        return matcher.matches();
    }
}
