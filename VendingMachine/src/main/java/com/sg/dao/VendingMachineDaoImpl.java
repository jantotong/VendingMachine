/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dao;

import com.sg.dto.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.sg.ui.UserIO;
import com.sg.ui.UserIOConsoleImpl;
import org.springframework.stereotype.Component;

/**
 *
 * @author Noah McElroy
 */
@Component
public class VendingMachineDaoImpl implements VendingMachineDao {
    private UserIO io = new UserIOConsoleImpl();
    private final String VENDING_FILE;
    public static final String DELIMITER = "::";
    
    private Map<String, Product> products = new HashMap<>();
    
    public VendingMachineDaoImpl(){
        VENDING_FILE = "vending.txt";
    }

    public VendingMachineDaoImpl(String vendingTextFile){
        VENDING_FILE = vendingTextFile;
    }

    @Override
    public List<Product> displayAllProducts() throws VendingMachinePersistenceException {//not sure about this one
        loadVending();
        return new ArrayList(products.values());
    }

    @Override
    public Product addProduct(String name, Product product) throws VendingMachinePersistenceException {
            loadVending();
            Product newProduct = products.put(name, product);
            writeVending();
            return newProduct;
    }

    @Override
    public Product getProduct(String name) throws VendingMachinePersistenceException {
        loadVending();
        return products.get(name);
    }

    @Override
    public Product removeProduct(String name) throws VendingMachinePersistenceException {
        loadVending();
        Product removedProduct = products.remove(name);
        writeVending();
        return removedProduct;
    }

    @Override
    public Product editProduct(String name) throws VendingMachinePersistenceException {
        loadVending(); 
        while(!products.containsKey(name)){
          name = io.readString("previous item name is not in the vending machine please enter a available product");
        }
        io.print("What would you like to change");
        io.print("1. Edit product name");
        io.print("2. Edit product price");
        io.print("3. Edit product quanitity");
        Product EditedProduct = products.get(name);
        int choice =io.readInt("Please select from the above choices.", 1, 3);
        switch(choice){
            case 1:
               //EditedDVD.setTitle(io.readString("Enter the edited title"));
               //dvds.remove(title);
               //in order to prevent duplication need to delete old copy and add the new version
               String newProductName =io.readString("Enter the edited product name");
               Product ProductFromFile = new Product(newProductName,EditedProduct.getPrice(),EditedProduct.getQty());
               products.remove(name);
               products.put(newProductName, ProductFromFile);
               break;
            case 2:
                String price = io.readString("Please enter the new price");
                EditedProduct.setPrice(new BigDecimal(price));
                //EditedDVD.setReleaseDate(LocalDate.MAX);
                break;
            case 3:
                EditedProduct.setQty(io.readInt("Please Enter the new quanitity amount."));
                break;
            default:
                break;
        }
        writeVending();
        return EditedProduct;
    }
    private Product unmarshallProduct(String productAsText){

        String[] productTokens = productAsText.split(DELIMITER);

        // Given the pattern above, the student Id is in index 0 of the array.
        String productName = productTokens[0];

        // Which we can then use to create a new Student object to satisfy
        // the requirements of the Student constructor.
        Product productFromFile = new Product(productName);

        productFromFile.setPrice(new BigDecimal(productTokens[1]));
        productFromFile.setQty(Integer.parseInt(productTokens[2]));
        


        return productFromFile;
    }
    private void loadVending() throws VendingMachinePersistenceException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(VENDING_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "-_- Could not load vending machine data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentStudent holds the most recent student unmarshalled
        Product currentProduct;
        // Go through ROSTER_FILE line by line, decoding each line into a 
        // Student object by calling the unmarshallStudent method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a Student
            currentProduct = unmarshallProduct(currentLine);

            // We are going to use the student id as the map key for our student object.
            // Put currentStudent into the map using student id as the key
            products.put(currentProduct.getName(), currentProduct);
        }
        // close scanner
        scanner.close();
    }
    
     private String marshallProduct(Product aProduct){
        // We need to turn a Student object into a line of text for our file.
        // For example, we need an in memory object to end up like this:
        // 4321::Charles::Babbage::Java-September1842

        // It's not a complicated process. Just get out each property,
        // and concatenate with our DELIMITER as a kind of spacer. 

        // Start with the student id, since that's supposed to be first.
        String productAsText = aProduct.getName() + DELIMITER;

        // add the rest of the properties in the correct order:

        // FirstName
        productAsText += aProduct.getPrice()+ DELIMITER;

        // LastName
        productAsText += aProduct.getQty();



        // We have now turned a student to text! Return it!
        return productAsText;
    } 

       /**
     * Writes all students in the roster out to a ROSTER_FILE.  See loadRoster
     * for file format.
     * 
     * @throws ClassRosterDaoException if an error occurs writing to the file
     */
    private void writeVending() throws VendingMachinePersistenceException {
        // NOTE FOR APPRENTICES: We are not handling the IOException - but
        // we are translating it to an application specific exception and 
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to 
        // handle any errors that occur.
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(VENDING_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException(
                    "Could not save vending machine data.", e);
        }

        String productAsText;
        List<Product> productList = this.displayAllProducts();
        for (Product currentProduct : productList) {
            // turn a Student into a String
            productAsText = marshallProduct(currentProduct);
            // write the Student object to the file
            out.println(productAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

    @Override
    public List<Product> getInventory() throws VendingMachinePersistenceException 
    {
        loadVending();
        List<Product> returnValue = new ArrayList<>(products.values());
        return returnValue;
    }

    @Override
    public void reduceStockByOne(String name) {
        
        Product temp = products.get(name);
        temp.setQty(temp.getQty() -1 );
        
        products.replace(name, temp);
        
    }
}
