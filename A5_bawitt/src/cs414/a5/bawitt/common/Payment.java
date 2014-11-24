package cs414.a5.bawitt.common;

import java.util.Date;

import cs414.a5.bawitt.server.PaymentTypeImpl;

public interface Payment extends java.rmi.Remote{

	double getAmountDue() throws java.rmi.RemoteException;;

	Date getPaymentDate() throws java.rmi.RemoteException;;

	PaymentTypeImpl getPaymentType() throws java.rmi.RemoteException;;

	double getOriginalAmountDue() throws java.rmi.RemoteException;
	
	String getTicketID() throws java.rmi.RemoteException;

}