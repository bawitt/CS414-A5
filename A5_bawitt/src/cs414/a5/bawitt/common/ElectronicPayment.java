package cs414.a5.bawitt.common;

public interface ElectronicPayment extends java.rmi.Remote{

	boolean isAccountValid() throws java.rmi.RemoteException;;

	boolean isDateValid() throws java.rmi.RemoteException;;

	boolean isActNumValid() throws java.rmi.RemoteException;;
	
	boolean isExpDateInFuture() throws java.rmi.RemoteException;

}