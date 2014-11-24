package cs414.a5.bawitt.server;

import cs414.a5.bawitt.common.CashPaymentInt;

public class CashPaymentImpl extends PaymentImpl implements CashPaymentInt{
	private double amountDue;
	private double change;
	private double paymentAmount;
	
	//public CashPaymentImpl(double ad, double pa) throws java.rmi.RemoteException{
	//	super(ad, PaymentTypeImpl.cash);
	//	amountDue = ad;
	//	paymentAmount = pa;
	//	change = 0;
	//	makeInitialCashPayment();	
//	}
	
	public CashPaymentImpl(int t, double ad) throws java.rmi.RemoteException{
		super(ad, PaymentTypeImpl.cash, t);
		amountDue = ad;
	}
	/* (non-Javadoc)
	 * @see a4.application.CashPaymentImpl#makeInitialCashPayment()
	 */

	@Override
	public void makeInitialCashPayment() throws java.rmi.RemoteException{
		amountDue = amountDue - paymentAmount;
		if(amountDue<0) change = amountDue *-1;
	}


	@Override

	public void makePostCashPayment(double paymentAmount) throws java.rmi.RemoteException{
		amountDue = amountDue - paymentAmount;
		if(amountDue<0) change = amountDue *-1;
	}
	/* (non-Javadoc)
	 * @see a4.application.CashPaymentImpl#getAmountDue()
	 */

	@Override
	public double getAmountDue() throws java.rmi.RemoteException{
		return amountDue;
	}
	/* (non-Javadoc)
	 * @see a4.application.CashPaymentImpl#getChange()
	 */

	@Override
	public double getChange() throws java.rmi.RemoteException{
		return change;
	}
}
