package cs414.a5.bawitt.server;

import cs414.a5.bawitt.common.Gate;

public class GateImpl extends java.rmi.server.UnicastRemoteObject implements Gate {

	private static final long serialVersionUID = 1L;
	private GateTypeImpl type;
	private boolean gateOpenStatus;
	
	public GateImpl(GateTypeImpl t) throws java.rmi.RemoteException{
		type = t;
		gateOpenStatus = false;
	}
	
	@Override
	public void closeGate() throws java.rmi.RemoteException{
		gateOpenStatus = false;
	}

	@Override
	public void openGate() throws java.rmi.RemoteException{
		gateOpenStatus = true;
	}	

	@Override
	public GateTypeImpl getType() throws java.rmi.RemoteException{
		return type;
	}

	@Override
	public boolean getGateOpenStatus() throws java.rmi.RemoteException{
		return gateOpenStatus;
	}
}
