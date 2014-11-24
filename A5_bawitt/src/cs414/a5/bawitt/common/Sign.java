package cs414.a5.bawitt.common;

import cs414.a5.bawitt.server.SignStatusImpl;


public interface Sign extends java.rmi.Remote{

	void refreshSign(Spaces s) throws java.rmi.RemoteException;;

	SignStatusImpl getSignStatus() throws java.rmi.RemoteException;;

	void setSignStatus(SignStatusImpl s) throws java.rmi.RemoteException;;

}