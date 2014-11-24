package cs414.a5.bawitt.server;

import cs414.a5.bawitt.common.Employee;


public class EmployeeImpl extends java.rmi.server.UnicastRemoteObject implements Employee {

	private static final long serialVersionUID = 1L;
	String username;
	String passNum;
	boolean isAdmin;
	boolean isActive;
	
	public EmployeeImpl(String un, String pn) throws java.rmi.RemoteException{
		username = un;
		passNum = pn;
		isActive = true;
	}
	
	public EmployeeImpl() throws java.rmi.RemoteException {
		username = null;
	}

	@Override
	public boolean isApproved(String pn) throws java.rmi.RemoteException{
		if(isActive && (passNum.equals(pn))){
			return true;
		}
		else return false;
	}

	@Override
	public void deactivateUser() throws java.rmi.RemoteException{
		isActive = false;
	}

	@Override
	public String getUsername() throws java.rmi.RemoteException{
		return username;
	}

	@Override
	public boolean getIsActive() throws java.rmi.RemoteException{
		return isActive;
	}
	
}
