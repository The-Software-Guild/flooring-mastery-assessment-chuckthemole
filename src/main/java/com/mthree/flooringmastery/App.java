/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.flooringmastery;

import com.mthree.flooringmastery.controller.FlooringMasteryController;
import com.mthree.flooringmastery.dao.FlooringMasteryAuditDao;
import com.mthree.flooringmastery.dao.FlooringMasteryAuditDaoFileImpl;
import com.mthree.flooringmastery.dao.FlooringMasteryDao;
import com.mthree.flooringmastery.dao.FlooringMasteryDaoFileImpl;
import com.mthree.flooringmastery.model.Product;
import com.mthree.flooringmastery.service.FlooringMasteryServiceLayer;
import com.mthree.flooringmastery.service.FlooringMasteryServiceLayerImpl;
import com.mthree.flooringmastery.ui.FlooringMasteryView;
import com.mthree.flooringmastery.ui.UserIO;
import com.mthree.flooringmastery.ui.UserIOConsoleImpl;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Chuck
 */
public class App {
    public static void main(String[] args) {
        /*
        UserIO myIo = new UserIOConsoleImpl();
        FlooringMasteryView myView = new FlooringMasteryView(myIo);
        FlooringMasteryDao myDao = new FlooringMasteryDaoFileImpl();
        FlooringMasteryAuditDao myAuditDao = new FlooringMasteryAuditDaoFileImpl();
        FlooringMasteryServiceLayer myService = new FlooringMasteryServiceLayerImpl(myDao, myAuditDao);
        // myDao.loadSnacksFromFile();
        FlooringMasteryController controller = new FlooringMasteryController(myService, myView);
        controller.run();
        // myDao.writeSnacksToFile();    
        */
       
        
        ApplicationContext ctx = 
                new ClassPathXmlApplicationContext("applicationContext.xml");
        FlooringMasteryController controller = 
                ctx.getBean("controller", FlooringMasteryController.class);
        controller.run();
        
        /*
        Scanner sc;
        List<Product> productsFromFile = new ArrayList<>();
        
        try {
            sc = new Scanner(new BufferedReader(new FileReader("SampleFileData/Data/Products.txt")));
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
            System.out.print("Success");
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            System.out.print("Nope!");
        }*/
        
    } 
}
