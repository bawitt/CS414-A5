package cs414.a5.bawitt.common;

import cs414.a5.bawitt.server.GateTypeImpl;


public interface Gate extends java.rmi.Remote{

	void closeGate() throws java.rmi.RemoteException;;

	void openGate() throws java.rmi.RemoteException;;

	GateTypeImpl getType() throws java.rmi.RemoteException;;

	boolean getGateOpenStatus() throws java.rmi.RemoteException;;

}