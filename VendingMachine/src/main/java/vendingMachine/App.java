/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingMachine;

import com.sg.controller.VendingMachineController;
import com.sg.dao.VendingMachineAuditDao;
import com.sg.dao.VendingMachineAuditDaoFileImpl;
import com.sg.dao.VendingMachineDao;
import com.sg.dao.VendingMachineDaoImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.sg.service.VendingMachineServiceLayer;
import com.sg.service.VendingMachineServiceLayerImpl;
import com.sg.ui.UserIO;
import com.sg.ui.UserIOConsoleImpl;
import com.sg.ui.VendingMachineView;

/**
 *
 * @author Noah McElroy
 */
public class App {
        
    
    
    public static void main(String[] args)
    {
    AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("com.sg");
        appContext.refresh();

        VendingMachineController controller = appContext.getBean("vendingMachineController", VendingMachineController.class);
        
        try{
            controller.run();
        }
        catch(Exception e)
        {
            System.out.println("FATAL ERRROR");
        }
    }
    
    /*public static void main(String[] args){
        // Instantiate the UserIO implementation
        UserIO myIo = new UserIOConsoleImpl();
        // Instantiate the View and wire the UserIO implementation into it
        VendingMachineView myView = new VendingMachineView(myIo);
        // Instantiate the DAO
        VendingMachineDao myDao = new VendingMachineDaoImpl();
        // Instantiate the Audit DAO
        VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoFileImpl();
        // Instantiate the Service Layer and wire the DAO and Audit DAO into it
        VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDao, myAuditDao);
        // Instantiate the Controller and wire the Service Layer into it
        VendingMachineController controller = new VendingMachineController(myView,myService);
        // Kick off the Controller
        controller.run();
    }
*/
    
}
