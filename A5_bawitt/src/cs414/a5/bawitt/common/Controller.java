package cs414.a5.bawitt.common;

import org.joda.time.DateTime;

public interface Controller extends java.rmi.Remote{
	
	Garage getGarage() throws java.rmi.RemoteException;
	
	DateTime getTicketEnterDate(int tid) throws java.rmi.RemoteException;
	
	boolean isTicketValid(int tid) throws java.rmi.RemoteException;
	
	double getAmountDueByID(int tid) throws java.rmi.RemoteException;
	
	void exitWithoutPayment(String n, String a, String p, int tid) throws java.rmi.RemoteException;
	
	String makeCashPayment(int tid, double amount) throws java.rmi.RemoteException;
	
	String makeElectronicPayment(double amountDue, String ccNum, String expDate, int ticketNumber) throws java.rmi.RemoteException;
}