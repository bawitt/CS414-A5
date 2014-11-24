package cs414.a5.bawitt.server;

import cs414.a5.bawitt.common.Sign;
import cs414.a5.bawitt.common.Spaces;


public class SignImpl extends java.rmi.server.UnicastRemoteObject implements Sign {
	private SignStatusImpl status;
	
	public SignImpl() throws java.rmi.RemoteException{
		status = SignStatusImpl.vacancy;
	}
	/* (non-Javadoc)
	 * @see a4.application.SignImpl#refreshSign(a4.application.Spaces)
	 */
	@Override
	public void refreshSign(Spaces s) throws java.rmi.RemoteException{
		if(s.getUsedSpaces()<s.getNumSpaces()){
			status = SignStatusImpl.vacancy;
		}
		else status = SignStatusImpl.full;
	}
	/* (non-Javadoc)
	 * @see a4.application.SignImpl#getSignStatus()
	 */
	@Override
	public SignStatusImpl getSignStatus() throws java.rmi.RemoteException{
		return status;
	}
	
	/* (non-Javadoc)
	 * @see a4.application.SignImpl#setSignStatus(a4.application.SignStatus)
	 */
	@Override
	public void setSignStatus(SignStatusImpl s) throws java.rmi.RemoteException{
		status = s;
	}
}
