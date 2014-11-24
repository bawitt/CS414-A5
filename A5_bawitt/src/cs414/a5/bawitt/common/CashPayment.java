package cs414.a5.bawitt.common;

public interface CashPayment {

	double getAmountDue() throws java.rmi.RemoteException;

	double getChange() throws java.rmi.RemoteException;
}