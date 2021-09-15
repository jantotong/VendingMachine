/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.ui;

import com.sg.dto.Product;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Noah McElroy
 */
@Component
public class VendingMachineView 
{
    
    UserIO io;
    
    public VendingMachineView(UserIO io)
            {
                this.io = io;
            }
    /*
    add
    remove
    edit 
    buy
    exit
    */
    
    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Add item to the vending machine");
        io.print("2. Remove item from the vending machine");
        io.print("3. Edit item from the vending machine");
        io.print("4. Buy a product");
        io.print("5. Get Inventory");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices.", 1, 6);
    }
    
    public Product getNewProductInfo() 
    {
        String itemName = io.readString("Please enter Item's name");
        BigDecimal price = io.readBigDecimal("Please enter Item's price");
        int qty = io.readInt("Please enter Item's quantity");
        Product item = new Product(itemName,price,qty);
        return item;
    }
    
    public void displayProduct(Product product) 
    {
        if (product != null) {

            io.print("\nDisplaying information about :");
            io.print("Item : " + product.getName());
            io.print("Price : "+ product.getPrice().toString());
            io.print("");
        } else {
            io.print("\nNo such student.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void addItemBannerDisplay()
    {
        io.print("====== ADD ITEM ======");
    }
    
    public void addItemSuccessDisplay()
    {
        io.print("ADD ITEM SUCCESSFUL");
    }
    public void removeItemBannerDisplay()
    {

        io.print("==== REMOVE ITEM ====");
    }
    
    public void removeItemSuccessDisplay()
    {
        io.print("REMOVE ITEM SUCCESSFUL");
    }
    
    
    public void editItemBannerDisplay()
    {
        io.print("===== EDIT ITEM =====");
    }
    
    public void editItemSuccessDisplay()
    {
        io.print("EDIT ITEM SUCCESSFUL");
    }
    
    public String getItemSelection()
    {
        return io.readString("Select an Item");
        
    }
    
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }


    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    

    public void getInventoryBannerDisplay()
    {
        io.print("============================");
        io.print("========= INVENTORY ========");
    }

    public String getItemName() {
        return io.readString("Enter a product name");
    }
    
    
    public void buyItemBannerDisplay()
    {
        io.print("====================");
        io.print("===== Buy Item =====");
    }
    
    public void buyItemSuccessDisplay()
    {
        io.print("YOU SUCCESSFULLY BOUGH AN ITEM");
    }

    public BigDecimal getAmount() {
        return io.readBigDecimal("HOW MUCH MONEY DO YOU HAVE?");
    }
    

    
}
    
    
