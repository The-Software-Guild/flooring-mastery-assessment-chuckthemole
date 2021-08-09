/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.flooringmastery.dao;

import com.mthree.flooringmastery.model.Order;
import com.mthree.flooringmastery.model.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
public class FlooringMasteryDaoFileImplTest {
    FlooringMasteryDao dao;
    
    public FlooringMasteryDaoFileImplTest() {
        dao = new FlooringMasteryDaoFileImpl(true);
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
     * Test of addOrder method, of class FlooringMasteryDaoFileImpl.
     */
    @Test
    public void testAddOrder() {
        System.out.println("addOrder");
        
        String date = "11/11/2021";
        Product onlyProduct = new Product("Wood", new BigDecimal("2.25"), new BigDecimal("1.50"));
        Order order = new Order(date, "Chuck","CA", onlyProduct, new BigDecimal("100"), 1);
        Order.decrementOrderCount();

        boolean expResult = true;
        boolean result = dao.addOrder(1, order);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllOrders method, of class FlooringMasteryDaoFileImpl.
     */
    @Test
    public void testGetAllOrders_0args() {
        System.out.println("getAllOrders");
        
        String date = "11/11/2021";
        Product onlyProduct = new Product("Wood", new BigDecimal("2.25"), new BigDecimal("1.50"));
        Order order = new Order(date, "Chuck","CA", onlyProduct, new BigDecimal("100"), 1);
        Order.decrementOrderCount();

        List<Order> expResult = new ArrayList<>();
        expResult.add(order);
        
        //dao.addOrder(1, order);
        System.out.println("size of dao.getAllOrders()" + dao.getAllOrders().size());
        List<Order> result = dao.getAllOrders();
        
        assertEquals(expResult.size(), result.size());
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllOrders method, of class FlooringMasteryDaoFileImpl.
     */
    @Test
    public void testGetAllOrders_String() {
        System.out.println("getAllOrders");
        
        String date = "11/11/2021";
        Product onlyProduct = new Product("Wood", new BigDecimal("2.25"), new BigDecimal("1.50"));
        Order order = new Order(date, "Chuck","CA", onlyProduct, new BigDecimal("100"), 1);
        Order.decrementOrderCount();

        List<Order> expResult = new ArrayList<>();
        expResult.add(order);
        
        //dao.addOrder(1, order);
        List<Order> result = dao.getAllOrders(date);
        
        assertEquals(expResult.size(), result.size());
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllProducts method, of class FlooringMasteryDaoFileImpl.
     */
    @Test
    public void testGetAllProducts() {
        System.out.println("getAllProducts");
        
        Product product1 = new Product("Carpet", new BigDecimal("2.25"), new BigDecimal("2.10"));
        Product product2 = new Product("Laminate", new BigDecimal("1.75"), new BigDecimal("2.10"));
        Product product3 = new Product("Tile", new BigDecimal("3.50"), new BigDecimal("4.15"));
        Product product4 = new Product("Wood", new BigDecimal("5.15"), new BigDecimal("4.75"));
        
        List<Product> expResult = new ArrayList<>();
        expResult.add(product1);
        expResult.add(product2);
        expResult.add(product3);
        expResult.add(product4);
    
        List<Product> result = dao.getAllProducts();
        
        for (Product product : result) {
            expResult.contains(product);
        }
        
        assertEquals(expResult.size(), result.size());
    }
    
    /**
     * Test of removeOrder method, of class FlooringMasteryDaoFileImpl.
     */
    @Test
    public void testRemoveOrder() {
        System.out.println("removeOrder");
        
        String date = "11/11/2021";
        Product onlyProduct = new Product("Wood", new BigDecimal("2.25"), new BigDecimal("1.50"));
        Order expResult = new Order(date, "Chuck","CA", onlyProduct, new BigDecimal("100"), 1);
        Order.decrementOrderCount();
        
        Order result = dao.removeOrder(date, 1);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllTaxes method, of class FlooringMasteryDaoFileImpl.
     */
    @Test
    public void testGetAllTaxes() {
        return;
    }

    /**
     * Test of editOrder method, of class FlooringMasteryDaoFileImpl.
     */
    @Test
    public void testEditOrder() {
        return;
    }

    /**
     * Test of createOrderFile method, of class FlooringMasteryDaoFileImpl.
     */
    @Test
    public void testCreateOrderFile() {
        return;
    }

    /**
     * Test of isCorrectDateFormat method, of class FlooringMasteryDaoFileImpl.
     */
    @Test
    public void testIsCorrectDateFormat() {
        return;
    }

    /**
     * Test of loadProductsFromFile method, of class FlooringMasteryDaoFileImpl.
     */
    @Test
    public void testLoadProductsFromFile() {
        return;
    }

    /**
     * Test of loadFiles method, of class FlooringMasteryDaoFileImpl.
     */
    @Test
    public void testLoadFiles() {
        return;
    }

    /**
     * Test of loadTaxes method, of class FlooringMasteryDaoFileImpl.
     */
    @Test
    public void testLoadTaxes() {
        return;
    }

    /**
     * Test of writeOrderToFile method, of class FlooringMasteryDaoFileImpl.
     */
    @Test
    public void testWriteOrderToFile() {
        return;
    }

    /**
     * Test of writeAllOrdersToFiles method, of class FlooringMasteryDaoFileImpl.
     */
    @Test
    public void testWriteAllOrdersToFiles() {
        return;
    }

    /**
     * Test of writeAllOrdersToBackupFile method, of class FlooringMasteryDaoFileImpl.
     */
    @Test
    public void testWriteAllOrdersToBackupFile() {
        return;
    }
    
}
