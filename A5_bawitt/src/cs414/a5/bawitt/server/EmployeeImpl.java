package cs414.a5.bawitt.server;

import cs414.a5.bawitt.common.Employee;


public class EmployeeImpl extends java.rmi.server.UnicastRemoteObject implements Employee {
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
	/* (non-Javadoc)
	 * @see a4.application.EmployeeImpl#isApproved(java.lang.String)
	 */
	@Override
	public boolean isApproved(String pn) throws java.rmi.RemoteException{
		if(isActive && (passNum.equals(pn))){
			return true;
		}
		else return false;
	}
	/* (non-Javadoc)
	 * @see a4.application.EmployeeImpl#deactivateUser()
	 */
	@Override
	public void deactivateUser() throws java.rmi.RemoteException{
		isActive = false;
	}
	/* (non-Javadoc)
	 * @see a4.application.EmployeeImpl#getUsername()
	 */
	@Override
	public String getUsername() throws java.rmi.RemoteException{
		return username;
	}
	/* (non-Javadoc)
	 * @see a4.application.EmployeeImpl#getIsActive()
	 */
	@Override
	public boolean getIsActive() throws java.rmi.RemoteException{
		return isActive;
	}
	
}
