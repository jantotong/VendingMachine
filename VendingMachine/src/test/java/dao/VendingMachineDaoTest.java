/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Product;
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
    static VendingMachineDao dao;
    public VendingMachineDaoTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        dao = new VendingMachineDaoImpl();
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        dao = new VendingMachineDaoImpl();
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testDisplay() throws VendingMachinePersistenceException{
        List<Product> DisplayAllProduct = dao.displayAllProducts();
        int initialCount = DisplayAllProduct.size();
        assertTrue(initialCount >= 0);//The count should never be negative
    
    }
    
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
}
