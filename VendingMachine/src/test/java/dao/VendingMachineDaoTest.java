/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Product;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author me
 */
public class VendingMachineDaoTest {
    
    private VendingMachineDao dao;
    
    public VendingMachineDaoTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws IOException {
        String fileName = "testVending";
        new FileWriter(fileName);
        dao = new VendingMachineDaoImpl(fileName);
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testDisplay() throws VendingMachinePersistenceException
    {
        Product testValue1 = new Product("Chocolate");
        testValue1.setPrice(BigDecimal.ONE);
        testValue1.setQty(10);
        
        dao.addProduct("Chocolate", testValue1);
        
        Product testValue2 = new Product("Candy");
        testValue2.setPrice(BigDecimal.ONE);
        testValue2.setQty(10);
        
        dao.addProduct("Candy", testValue2);
        
        
        List<Product> testReturnedV = dao.getInventory();
        
        assertTrue(testReturnedV.contains(testValue1));
        assertTrue(testReturnedV.contains(testValue2));
        assertTrue(testReturnedV.size() == 2);
        
        
    
    }
    /*
    @Test
    public void testAddProduct() throws VendingMachinePersistenceException{
        List<Product> DisplayAllProduct = dao.displayAllProducts();
        int initialCount = DisplayAllProduct.size();
        Product p = new Product("Apple", new BigDecimal(1), 10);
        dao.addProduct("Apple", p);
        int finalCount = DisplayAllProduct.size();
        assertTrue(finalCount == initialCount + 1);
    }
    
    @Test
    public void removeProduct() throws VendingMachinePersistenceException {
        List<Product> DisplayAllProduct = dao.displayAllProducts();
        int initialCount = DisplayAllProduct.size();
        Product p = new Product("Apple", new BigDecimal(1), 10);
        dao.addProduct("Apple", p);
        dao.addProduct("Pear", p);
        System.out.println(dao.displayAllProducts().size());
    }
    
    @Test
    public void testGetProduct() throws VendingMachinePersistenceException{
        dao.addProduct("Popcorn", new Product("Popcorn", new BigDecimal(11), 10));
        Product foundProduct = dao.getProduct("Popcorn");
        assertNotNull(foundProduct);
        
    }
    
    @Test
    public void testGetInventory() throws VendingMachinePersistenceException {
        Product popcorn = new Product("Popcorn", new BigDecimal(11), 10);
        dao.addProduct("Popcorn", popcorn);

        List<Product> inventory = dao.getInventory();
        assertTrue(inventory.contains(popcorn));
    }
*/
}
