package cs414.a5.bawitt.common;

public interface Spaces extends java.rmi.Remote {

	void incEnter() throws java.rmi.RemoteException;;

	void decExit() throws java.rmi.RemoteException;;

	void setNumSpaces(int s) throws java.rmi.RemoteException;;

	int getNumSpaces() throws java.rmi.RemoteException;;

	void setUsedSpaces(int s) throws java.rmi.RemoteException;;

	int getUsedSpaces() throws java.rmi.RemoteException;;

}