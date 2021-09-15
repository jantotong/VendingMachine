/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.VendingMachinePersistenceException;
import dto.Product;
import service.VendingMachineDuplicateNameException;
import service.VendingMachineServiceLayer;
import ui.UserIO;
import ui.UserIOConsoleImpl;
import ui.VendingMachineView;

/**
 *
 * @author Noah McElroy
 */
public class VendingMachineController {

    private VendingMachineView view;
    private UserIO io = new UserIOConsoleImpl();
    private VendingMachineServiceLayer service;

    public VendingMachineController(VendingMachineView view, VendingMachineServiceLayer slayer) {
        this.view = view;
        this.service = slayer;
    }

    public void run() {

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
            } catch (VendingMachineDuplicateNameException  | VendingMachineDataValidationException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
    }

    private void getInventory() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void buyItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void editItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void removeItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
