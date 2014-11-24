package cs414.a5.bawitt.server;

import cs414.a5.bawitt.common.Sign;
import cs414.a5.bawitt.common.Spaces;


public class SignImpl extends java.rmi.server.UnicastRemoteObject implements Sign {

	private static final long serialVersionUID = 1L;
	private SignStatusImpl status;
	
	public SignImpl() throws java.rmi.RemoteException{
		status = SignStatusImpl.vacancy;
	}

	@Override
	public void refreshSign(Spaces s) throws java.rmi.RemoteException{
		if(s.getUsedSpaces()<s.getNumSpaces()){
			status = SignStatusImpl.vacancy;
		}
		else status = SignStatusImpl.full;
	}

	@Override
	public SignStatusImpl getSignStatus() throws java.rmi.RemoteException{
		return status;
	}
	
	@Override
	public void setSignStatus(SignStatusImpl s) throws java.rmi.RemoteException{
		status = s;
	}
}
