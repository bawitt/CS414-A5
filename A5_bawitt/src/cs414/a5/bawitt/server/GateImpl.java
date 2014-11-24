package cs414.a5.bawitt.server;

import cs414.a5.bawitt.common.Gate;


public class GateImpl extends java.rmi.server.UnicastRemoteObject implements Gate {
	private GateTypeImpl type;
	private boolean gateOpenStatus;
	
	public GateImpl(GateTypeImpl t) throws java.rmi.RemoteException{
		type = t;
		gateOpenStatus = false;
	}
	
	/* (non-Javadoc)
	 * @see a4.application.GateImpl#closeGate()
	 */
	@Override
	public void closeGate() throws java.rmi.RemoteException{
		gateOpenStatus = false;
		System.out.println(type + " gate closed.\n");
	}
	/* (non-Javadoc)
	 * @see a4.application.GateImpl#openGate()
	 */
	@Override
	public void openGate() throws java.rmi.RemoteException{
		gateOpenStatus = true;
		System.out.println(type + " gate open.");
	}	
	/* (non-Javadoc)
	 * @see a4.application.GateImpl#getType()
	 */
	@Override
	public GateTypeImpl getType() throws java.rmi.RemoteException{
		return type;
	}
	/* (non-Javadoc)
	 * @see a4.application.GateImpl#getGateOpenStatus()
	 */
	@Override
	public boolean getGateOpenStatus() throws java.rmi.RemoteException{
		return gateOpenStatus;
	}
	

}
