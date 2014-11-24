package cs414.a5.bawitt.common;

public interface Employee extends java.rmi.Remote{

	boolean isApproved(String pn) throws java.rmi.RemoteException;;

	void deactivateUser() throws java.rmi.RemoteException;;

	String getUsername() throws java.rmi.RemoteException;;

	boolean getIsActive() throws java.rmi.RemoteException;;

}