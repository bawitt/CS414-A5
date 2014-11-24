package cs414.a5.bawitt.common;

public interface Rate extends java.rmi.Remote{

	double getStandardRate() throws java.rmi.RemoteException;;

	void setStandardRate(double r) throws java.rmi.RemoteException;;

	double getFlatRate() throws java.rmi.RemoteException;;

	void setFlatRate(double r) throws java.rmi.RemoteException;;

}