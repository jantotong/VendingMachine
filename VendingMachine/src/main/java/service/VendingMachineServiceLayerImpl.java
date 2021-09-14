/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dto.Product;

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
    
    
     private void validateProductData(Product item) throws VendingMachineDataValidationException 
    {

            if (item.getName() == null
                    || item.getName().trim().length() == 0
                    || item.getPrice() == null
                    || item.getQty() <= 0) {

                throw new VendingMachineDataValidationException(
                        "ERROR: All fields [name,price,qty] are required.");
        }
    }
    
    
}
