package cs414.a5.bawitt.server;

import cs414.a5.bawitt.common.CashPayment;

public class CashPaymentImpl extends PaymentImpl implements CashPayment{
	
	private static final long serialVersionUID = 1L;
	private double amountDue;
	private double change;
	//private double paymentAmount;
	
	public CashPaymentImpl(int t, double ad) throws java.rmi.RemoteException{
		super(ad, PaymentTypeImpl.cash, t);
		amountDue = ad;
	}

	@Override
	public double getAmountDue() throws java.rmi.RemoteException{
		return amountDue;
	}

	@Override
	public double getChange() throws java.rmi.RemoteException{
		return change;
	}
}
