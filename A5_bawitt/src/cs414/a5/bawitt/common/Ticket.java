package cs414.a5.bawitt.common;

import org.joda.time.DateTime;

public interface Ticket extends java.rmi.Remote{

	//hours from entry until current time
	int getDurationHours() throws java.rmi.RemoteException;

	int getID() throws java.rmi.RemoteException;;

	DateTime getEnterDate() throws java.rmi.RemoteException;

	void setPaymentDate(DateTime dt) throws java.rmi.RemoteException;

	double getTicketStandardRate() throws java.rmi.RemoteException;

	double getAmountDue() throws java.rmi.RemoteException;
}