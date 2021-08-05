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
import com.mthree.flooringmastery.service.FlooringMasteryServiceLayer;
import com.mthree.flooringmastery.service.FlooringMasteryServiceLayerImpl;
import com.mthree.flooringmastery.ui.FlooringMasteryView;
import com.mthree.flooringmastery.ui.UserIO;
import com.mthree.flooringmastery.ui.UserIOConsoleImpl;
import java.io.File;
import java.io.IOException;
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
        
    } 
}
