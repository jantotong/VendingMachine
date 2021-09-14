/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Noah McElroy
 */
public class VendingMachineDaoImpl implements VendingMachineDao {
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
    public List<Product> DisplayAllProduct() throws VendingMachinePersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product addProduct(String name, Product product) throws VendingMachinePersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product getProduct(String name) throws VendingMachinePersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product removeProduct(String name) throws VendingMachinePersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product editProduct(String name) throws VendingMachinePersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
}
