package cs414.a5.bawitt.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.SwingUtilities;

import cs414.a5.bawitt.common.Controller;

public class GarageClient {

	public static void main(String[] args) throws RemoteException {
        try {
    		Controller garageController = (Controller)
    		Naming.lookup("rmi://" + args[0] + ":" + args[1]  + "/Garage");
    		MainUI mainUI = new MainUI(garageController);
    		SwingUtilities.invokeLater(new Runnable(){
    			public void run(){
    				mainUI.paintUI();
    			}
    		});
       }

       catch (MalformedURLException murle) {
              System.out.println("MalformedURLException");
              System.out.println(murle);
          } catch (RemoteException re) {
              System.out.println("RemoteException"); 
              System.out.println(re);
          } catch (NotBoundException nbe) {
              System.out.println("NotBoundException");
              System.out.println(nbe);
          } catch (java.lang.ArithmeticException ae) {
               System.out.println("java.lang.ArithmeticException");
               System.out.println(ae);
          }         
	}	
}
