/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dto.Product;

import java.util.List;

/**
 *
 * @author Noah McElroy
 */
public interface VendingMachineServiceLayer {

    void addProduct(String name, Product product) throws VendingMachineDataValidationException, NoItemInventoryException, VendingMachineDuplicateNameException, VendingMachinePersistenceException;

    List<Product> DisplayAllProduct() throws NoItemInventoryException, VendingMachinePersistenceException;

    Product getProduct(String name) throws NoItemInventoryException, InsufficientFundsException, VendingMachinePersistenceException;

    Product removeProduct (String name) throws NoItemInventoryException, VendingMachinePersistenceException;

    Product editProduct (String name) throws NoItemInventoryException, VendingMachinePersistenceException;
}
