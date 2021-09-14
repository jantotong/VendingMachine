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
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer{
    @Override
    public void addProduct() throws InsufficientFundsException, NoItemInventoryException, VendingMachineDuplicateNameException, VendingMachinePersistenceException {
        throw new UnsupportedOperationException("");
    }

    @Override
    public void DisplayAllProduct() throws NoItemInventoryException, VendingMachinePersistenceException {

    }

    @Override
    public void getProduct() throws NoItemInventoryException, InsufficientFundsException, VendingMachinePersistenceException {

    }

    @Override
    public void removeProduct() throws NoItemInventoryException, VendingMachinePersistenceException {

    }

    @Override
    public void editProduct() throws NoItemInventoryException, VendingMachinePersistenceException {

    }
}
