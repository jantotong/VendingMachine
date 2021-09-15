/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.controller;

import com.sg.dao.VendingMachinePersistenceException;
import com.sg.dto.Change;
import com.sg.dto.Product;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.sg.service.InsufficientFundsException;
import com.sg.service.NoItemInventoryException;
import com.sg.service.VendingMachineDataValidationException;
import com.sg.service.VendingMachineDuplicateNameException;
import com.sg.service.VendingMachineServiceLayer;
import com.sg.ui.UserIO;
import com.sg.ui.UserIOConsoleImpl;
import com.sg.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Noah McElroy
 */

@Component
public class VendingMachineController {

    private VendingMachineView view;
    private UserIO io = new UserIOConsoleImpl();
    private VendingMachineServiceLayer service;
    
    private BigDecimal cash = new BigDecimal("0.00");

    @Autowired
    public VendingMachineController(VendingMachineView view, VendingMachineServiceLayer slayer) {
        this.view = view;
        this.service = slayer;
    }

    public void run() throws NoItemInventoryException {

        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {
                menuSelection = getMenuSelection();
                
                switch(menuSelection){
                
                    case 1:
                        addItem();
                        break;
                    case 2:
                        removeItem();
                        break;
                    case 3:
                        editItem();
                        break;
                    case 4:
                        buyItem();
                        break;
                    case 5:
                        getInventory();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        System.out.println("Unknown command");
                        break;
                }
            }
            view.displayExitBanner();
            
        } catch (VendingMachinePersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void addItem() throws VendingMachinePersistenceException {
        view.addItemBannerDisplay();
        boolean hasErrors = false;
        do {            
            Product item = view.getNewProductInfo();
            try {
                service.addProduct(item.getName(), item);
                view.addItemSuccessDisplay();
                hasErrors = false;
            } catch (VendingMachineDuplicateNameException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            } catch (VendingMachineDataValidationException ex) {
                hasErrors = true;
                view.displayErrorMessage(ex.getMessage());
            } catch (NoItemInventoryException ex) {
                hasErrors = true;
                view.displayErrorMessage(ex.getMessage());
            }
        } while (hasErrors);
    }

    private void getInventory() throws VendingMachinePersistenceException, NoItemInventoryException {
        view.getInventoryBannerDisplay();
        List<Product> allProducts = service.DisplayAllProduct();
        for (Product allProduct : allProducts) {
            view.displayProduct(allProduct);
        }
    }

    private void buyItem() 
    {
        // to do
        // implement logic
        view.buyItemBannerDisplay();
        
        cash = view.getAmount();
        
        String name = view.getItemName();
        try
        {
            if (service.getProduct(name) != null && service.getProduct(name).getQty()>0 && cash.doubleValue() >= service.getProduct(name).getPrice().doubleValue())
            {
                cash = new BigDecimal( cash.doubleValue() - service.getProduct(name).getPrice().doubleValue());
                service.reduceStockByOne(name);
            }
        }catch (Exception e)
        {
            System.out.println("FATAL ERROR");
        }
        
        view.buyItemSuccessDisplay();
        
        
        /*
        Map<changesType, BigDecimal> exchangeTotal()
        */
        Change change = new Change(cash);
        
        Map<Change.changesType,BigDecimal> changemap = change.exchangeTotal();
        
        
        view.displayChange(changemap);
        
        // VARIABLE CASH NOW HOLDS THE MONEY
        
        // TO DO
        // DISPLAY CHANGE
        // HERE
        
        // THEN RESET CASH VARIABLE
        
        cash = new BigDecimal("0.00");
        
    }

    private void editItem() {
        view.editItemBannerDisplay();
        String name = view.getItemName();
        try {
            if(service.getProduct(name) != null){
                service.editProduct(name);
            }
        } catch (NoItemInventoryException ex) {
            Logger.getLogger(VendingMachineController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InsufficientFundsException ex) {
            Logger.getLogger(VendingMachineController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (VendingMachinePersistenceException ex) {
            Logger.getLogger(VendingMachineController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void removeItem() {
        view.removeItemBannerDisplay();
        String name = view.getItemName();
        try {
            service.removeProduct(name);
            view.removeItemSuccessDisplay();
        } catch (NoItemInventoryException ex) {
            Logger.getLogger(VendingMachineController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (VendingMachinePersistenceException ex) {
            Logger.getLogger(VendingMachineController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private enum changesType {
        QUARTERS, DIMES, NICKELS, PENNIES
    }
    
    
}
