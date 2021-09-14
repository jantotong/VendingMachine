/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Product;
import java.util.List;

/**
 *
 * @author Noah McElroy
 */
public interface VendingMachineDao {
    List<Product> DisplayAllProduct()throws VendingMachinePersistenceException;
    
    Product addProduct(String name, Product product )throws VendingMachinePersistenceException;
    
    Product getProduct(String name)throws VendingMachinePersistenceException;
    
    Product removeProduct(String name)throws VendingMachinePersistenceException;
            
    Product editProduct(String name)throws VendingMachinePersistenceException;   
}

