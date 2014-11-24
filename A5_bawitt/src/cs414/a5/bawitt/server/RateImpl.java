package cs414.a5.bawitt.server;

import cs414.a5.bawitt.common.Rate;

public class RateImpl extends java.rmi.server.UnicastRemoteObject implements Rate {

	private static final long serialVersionUID = 1L;
	private double rate;
	private double flatRate;
	
	public RateImpl(double r, double fr) throws java.rmi.RemoteException{
		rate = r;
		flatRate = fr;
	}
	public RateImpl() throws java.rmi.RemoteException{
		rate = 0;
		flatRate = 0;
	}
	
	@Override
	public double getStandardRate() throws java.rmi.RemoteException{
		return rate;
	}

	@Override
	public void setStandardRate(double r) throws java.rmi.RemoteException{
		rate = r;
	}
	
	@Override
	public double getFlatRate() throws java.rmi.RemoteException{
		return flatRate;
	}

	@Override
	public void setFlatRate(double r) throws java.rmi.RemoteException{
		flatRate = r;
	}
}
