/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.flooringmastery.service;

import com.mthree.flooringmastery.dao.FlooringMasteryAuditDao;
import com.mthree.flooringmastery.dao.FlooringMasteryDao;
import com.mthree.flooringmastery.model.Order;
import com.mthree.flooringmastery.model.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Chuck
 */
public class FlooringMasteryServiceLayerImplTest {
    private FlooringMasteryServiceLayerImpl service;
    
    public FlooringMasteryServiceLayerImplTest() {
        FlooringMasteryDao dao = new FlooringMasteryDaoStubImpl();
        FlooringMasteryAuditDao auditDao = new FlooringMasteryAuditDaoStubImpl();

        service = new FlooringMasteryServiceLayerImpl(dao, auditDao);
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getAllOrders method, of class FlooringMasteryServiceLayerImpl.
     */
    @Test
    public void testGetAllOrders() {
        System.out.println("getAllOrders");
        
        String date = "11/11/2021";
        Product onlyProduct = new Product("Wood", new BigDecimal("2.25"), new BigDecimal("1.50"));
        Order order = new Order(date, "Chuck","CA", onlyProduct, new BigDecimal("100"), 1);
        Order.decrementOrderCount();

        List<Order> expResult = new ArrayList<>();
        expResult.add(order);
        
        List<Order> result = service.getAllOrders();
        
        assertEquals(expResult.size(), result.size());
        assertEquals(expResult, result);

    }

    /**
     * Test of getOrdersFromDate method, of class FlooringMasteryServiceLayerImpl.
     */
    @Test
    public void testGetOrdersFromDate() {
        System.out.println("getOrdersFromDate");
        
        String date = "11/11/2021";
        Product onlyProduct = new Product("Wood", new BigDecimal("2.25"), new BigDecimal("1.50"));
        Order order = new Order(date, "Chuck","CA", onlyProduct, new BigDecimal("100"), 1);
        Order.decrementOrderCount();

        List<Order> expResult = new ArrayList<>();
        expResult.add(order);
        
        List<Order> result = service.getOrdersFromDate(date);
        
        assertEquals(expResult.size(), result.size());
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllProducts method, of class FlooringMasteryServiceLayerImpl.
     */
    @Test
    public void testGetAllProducts() {
        System.out.println("getAllProducts");
        
        List<Product> expResult = new ArrayList<>();
        Product onlyProduct = new Product("Wood", new BigDecimal("2.25"), new BigDecimal("1.50"));
        expResult.add(onlyProduct);
        
        List<Product> result = service.getAllProducts();
        
        assertEquals(expResult.size(), result.size());
        assertEquals(expResult, result);
    }

    /**
     * Test of addOrder method, of class FlooringMasteryServiceLayerImpl.
     */
    @Test
    public void testAddOrder() {
        System.out.println("addOrder");
        
        
        String date = "11/11/2021";
        Product onlyProduct = new Product("Wood", new BigDecimal("2.25"), new BigDecimal("1.50"));
        Order order = new Order(date, "Chuck","CA", onlyProduct, new BigDecimal("100"), 1);
        Order.decrementOrderCount();
        
        String expResult = "\nOrder added!";
        String result = service.addOrder(order);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of removeOrder method, of class FlooringMasteryServiceLayerImpl.
     */
    @Test
    public void testRemoveOrder() {
        System.out.println("removeOrder");
        
        String date = "11/11/2021";
        Product onlyProduct = new Product("Wood", new BigDecimal("2.25"), new BigDecimal("1.50"));
        Order order = new Order(date, "Chuck","CA", onlyProduct, new BigDecimal("100"), 1);
        Order.decrementOrderCount();
        
        Order expResult = order;
        Order result = service.removeOrder(date, 1);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of editOrder method, of class FlooringMasteryServiceLayerImpl.
     */
    @Test
    public void testEditOrder() {
        System.out.println("editOrder");
        return;
    }

    /**
     * Test of writeAllOrdersToBackupFile method, of class FlooringMasteryServiceLayerImpl.
     */
    @Test
    public void testWriteAllOrdersToBackupFile() {
        System.out.println("writeAllOrdersToBackupFile");
        return;
    }

    /**
     * Test of writeAllOrdersToFiles method, of class FlooringMasteryServiceLayerImpl.
     */
    @Test
    public void testWriteAllOrdersToFiles() {
        System.out.println("writeAllOrdersToFiles");
        return;
    }
    
}
