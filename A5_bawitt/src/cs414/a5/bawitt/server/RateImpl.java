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
	
	/* (non-Javadoc)
	 * @see a4.application.RateImpl#getStandardRate()
	 */
	@Override
	public double getStandardRate() throws java.rmi.RemoteException{
		return rate;
	}
	/* (non-Javadoc)
	 * @see a4.application.RateImpl#setStandardRate(double)
	 */
	@Override
	public void setStandardRate(double r) throws java.rmi.RemoteException{
		rate = r;
	}
	
	/* (non-Javadoc)
	 * @see a4.application.RateImpl#getFlatRate()
	 */
	@Override
	public double getFlatRate() throws java.rmi.RemoteException{
		return flatRate;
	}
	/* (non-Javadoc)
	 * @see a4.application.RateImpl#setFlatRate(double)
	 */
	@Override
	public void setFlatRate(double r) throws java.rmi.RemoteException{
		flatRate = r;
	}
}
