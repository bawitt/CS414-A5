package cs414.a5.bawitt.server;

import cs414.a5.bawitt.common.Spaces;


public class SpacesImpl extends java.rmi.server.UnicastRemoteObject implements Spaces {

	private static final long serialVersionUID = 1L;
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

	@Override
	public void incEnter() throws java.rmi.RemoteException{
		usedSpaces = usedSpaces + 1;
	}

	@Override
	public void decExit() throws java.rmi.RemoteException{
		if(usedSpaces>0) usedSpaces = usedSpaces - 1;
	}

	@Override
	public void setNumSpaces(int s) throws java.rmi.RemoteException{
		numSpaces = s;
	}

	@Override
	public int getNumSpaces() throws java.rmi.RemoteException{
		return numSpaces;
	}

	@Override
	public void setUsedSpaces(int s) throws java.rmi.RemoteException{
		usedSpaces = s;
	}

	@Override
	public int getUsedSpaces() throws java.rmi.RemoteException{
		return usedSpaces;
	}
}
