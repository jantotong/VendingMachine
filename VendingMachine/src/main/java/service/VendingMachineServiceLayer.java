/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author Noah McElroy
 */
public interface VendingMachineServiceLayer {

    void addProduct() throws
            InsufficientFundsException
            ,NoItemInventoryException,
            VendingMachineDuplicateNameException,
            VendingMachinePersistenceException;

    void DisplayAllProduct() throws NoItemInventoryException, VendingMachinePersistenceException;

    void getProduct() throws NoItemInventoryException, InsufficientFundsException, VendingMachinePersistenceException;

    void removeProduct () throws NoItemInventoryException, VendingMachinePersistenceException;

    void editProduct () throws NoItemInventoryException, VendingMachinePersistenceException;
}
