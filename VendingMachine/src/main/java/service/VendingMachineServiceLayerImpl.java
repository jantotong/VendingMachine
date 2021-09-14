/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.VendingMachineAuditDao;
import dao.VendingMachineDao;
import dto.Product;

import java.util.List;

/**
 * @author Noah McElroy
 */

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    private VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;
    

    public VendingMachineServiceLayerImpl(VendingMachineDao dao) {
        this.dao = dao;
    }
    
    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public void addProduct(String name, Product product) throws VendingMachineDataValidationException, NoItemInventoryException, VendingMachineDuplicateNameException, VendingMachinePersistenceException 
    {
        // this method creates red error marker
        // because it says that we should be 'aware' of :
        
        // VendingMachinePersistenceException
        
        // and also beause 
        
        // VendingMachineAuditDao AND VendingMachineAuditDaoImpl are empty for now.
        
        // red here
        if (dao.getProduct(name) != null) {
            throw new VendingMachineDuplicateNameException(
                    "ERROR: Could not create item named "
                    + product.getName()
                    + " already exists");
        }


        validateProductData(product);

        // red here also
        dao.addProduct(name, product);

        // The item was successfully created, now write to the audit log
        auditDao.writeAuditEntry(
                "Product " + product.getName() + " CREATED.");


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
