/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.VendingMachineDao;
import dto.Product;

import java.util.List;

/**
 * @author Noah McElroy
 */

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    VendingMachineDao dao;

    public VendingMachineServiceLayerImpl(VendingMachineDao dao) {
        this.dao = dao;
    }

    @Override
    public Product addProduct(String name, Product product) throws InsufficientFundsException, NoItemInventoryException, VendingMachineDuplicateNameException, VendingMachinePersistenceException {
        //if(dao.getProduct(name)){}
        throw new UnsupportedOperationException("No Support Yet");
    }

    @Override
    public List<Product> DisplayAllProduct() throws NoItemInventoryException, VendingMachinePersistenceException {
        throw new UnsupportedOperationException("No Support Yet");
    }

    @Override
    public Product getProduct(String name) throws NoItemInventoryException, InsufficientFundsException, VendingMachinePersistenceException {
        throw new UnsupportedOperationException("No Support Yet");
    }

    @Override
    public Product removeProduct(String name) throws NoItemInventoryException, VendingMachinePersistenceException {
        throw new UnsupportedOperationException("No Support Yet");
    }

    @Override
    public Product editProduct(String name) throws NoItemInventoryException, VendingMachinePersistenceException {
        throw new UnsupportedOperationException("No Support Yet");
    }

    private void validateProductData(Product item) throws VendingMachineDataValidationException {

        if (item.getName() == null
                || item.getName().trim().length() == 0
                || item.getPrice() == null
                || item.getQty() <= 0) {

            throw new VendingMachineDataValidationException(
                    "ERROR: All fields [name,price,qty] are required.");
        }
    }

}
