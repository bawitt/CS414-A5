package cs414.a5.bawitt.common;

import java.util.Set;

import cs414.a5.bawitt.server.EmployeeImpl;
import cs414.a5.bawitt.server.ReceiptImpl;
import cs414.a5.bawitt.server.SignStatusImpl;
import cs414.a5.bawitt.server.TicketImpl;
import cs414.a5.bawitt.server.UnpaidTicketImpl;

public interface Garage extends java.rmi.Remote{

	void updateSignStatus() throws java.rmi.RemoteException;
	
	int getTotalSpaces() throws java.rmi.RemoteException;

	int getUsedSpaces() throws java.rmi.RemoteException;

	void updateGarageSpaces(int numSpaces) throws java.rmi.RemoteException;;

	void updateGarageStandardRate(double r) throws java.rmi.RemoteException;;

	void updateGarageFlatRate(double r) throws java.rmi.RemoteException;;

	int issueTicket() throws java.rmi.RemoteException;;

	TicketImpl getTicketFromList(int id) throws java.rmi.RemoteException;;

	Employee getEmployeeFromList(String un) throws java.rmi.RemoteException;;

	boolean authorizeUser(String un, String pn) throws java.rmi.RemoteException;;

	void exitGarage(int tid) throws java.rmi.RemoteException;;

	void exitGarage() throws java.rmi.RemoteException;;

	boolean createEmployee(String un, String pn) throws java.rmi.RemoteException;;

	Rate getGarageRate() throws java.rmi.RemoteException;;

	SignStatusImpl getSignStatus() throws java.rmi.RemoteException;;

	double getGarageFlatRate() throws java.rmi.RemoteException;;

	Set<EmployeeImpl> getEmployeeList() throws java.rmi.RemoteException;;

	Set<TicketImpl> getActiveTicketList() throws java.rmi.RemoteException;;

	String showUnpaidTickets() throws java.rmi.RemoteException;;

	Set<TicketImpl> getPaidTicketList() throws java.rmi.RemoteException;;

	int getGarageUsedSpaces() throws java.rmi.RemoteException;;

	void addReceiptToCollection(ReceiptImpl r) throws java.rmi.RemoteException;;

	void addUnpaidTicketToList(UnpaidTicketImpl ut) throws java.rmi.RemoteException;;

	void showsSpaceStatus() throws java.rmi.RemoteException;
	
	double getAmountOwedToGarage() throws java.rmi.RemoteException;
	
	double getAverageLengthOfStay() throws java.rmi.RemoteException;
	
	double getTotalRevenue() throws java.rmi.RemoteException;

}