package cs414.a5.bawitt.common;

public interface UnpaidTicket extends java.rmi.Remote{
	
	String unpaidToString() throws java.rmi.RemoteException;
	
	Ticket getUnpaidTicket() throws java.rmi.RemoteException;
}