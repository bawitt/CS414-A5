package cs414.a5.bawitt.server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import cs414.a5.bawitt.common.*;

public class GarageServer {
		
	  public GarageServer(int port) {
		    try {
		      Registry registry = LocateRegistry.createRegistry(port);
		      Controller c = new ControllerImpl();
		      registry.rebind("Garage", UnicastRemoteObject.exportObject(c, port));

		      System.out.println("Garage server running...");
		    } catch (Exception e) {
		      System.out.println("Trouble: " + e);
		    }
		  }
	  public static void main(String args[]) {
		    new GarageServer(Integer.parseInt(args[1]));
	}
}
	/*
	private String url;
	
	public GarageServer(String url) {
		this.url = url;
		try {
			Controller g = new ControllerImpl();
			System.out.println("G created");
			Naming.rebind(url, g);
			System.out.println("name bound");
			System.out.println("GargeServer server running...");
		} catch (Exception e) {
			System.out.println("Trouble: " + e);
		}
	}

	public static void main(String args[]) {
		String url = new String("rmi://" + args[0] + ":" + args[1] + "/BankService");
		new GarageServer(url);
	}
	*/
