package cs414.a5.bawitt.server;

import cs414.a5.bawitt.common.Spaces;


public class SpacesImpl extends java.rmi.server.UnicastRemoteObject implements Spaces {
	private int numSpaces;
	private int usedSpaces;
	
	public SpacesImpl(int s, int u) throws java.rmi.RemoteException{
		numSpaces = s;
		usedSpaces = u;
	}
	public SpacesImpl() throws java.rmi.RemoteException{
		numSpaces = 0;
		usedSpaces = 0;
	}
	/* (non-Javadoc)
	 * @see a4.application.SpacesImpl#incEnter()
	 */
	@Override
	public void incEnter() throws java.rmi.RemoteException{
		usedSpaces = usedSpaces + 1;
	}
	/* (non-Javadoc)
	 * @see a4.application.SpacesImpl#decExit()
	 */
	@Override
	public void decExit() throws java.rmi.RemoteException{
		usedSpaces = usedSpaces - 1;
	}
	/* (non-Javadoc)
	 * @see a4.application.SpacesImpl#setNumSpaces(int)
	 */
	@Override
	public void setNumSpaces(int s) throws java.rmi.RemoteException{
		numSpaces = s;
	}
	/* (non-Javadoc)
	 * @see a4.application.SpacesImpl#getNumSpaces()
	 */
	@Override
	public int getNumSpaces() throws java.rmi.RemoteException{
		return numSpaces;
	}
	/* (non-Javadoc)
	 * @see a4.application.SpacesImpl#setUsedSpaces(int)
	 */
	@Override
	public void setUsedSpaces(int s) throws java.rmi.RemoteException{
		usedSpaces = s;
	}
	/* (non-Javadoc)
	 * @see a4.application.SpacesImpl#getUsedSpaces()
	 */
	@Override
	public int getUsedSpaces() throws java.rmi.RemoteException{
		return usedSpaces;
	}
}
