package cs414.a5.bawitt.common;

public interface CashPaymentInt {

	/* (non-Javadoc)
	 * @see a4.application.CashPaymentImpl#makeInitialCashPayment()
	 */
	void makeInitialCashPayment() throws java.rmi.RemoteException;

	/* (non-Javadoc)
	 * @see a4.application.CashPaymentImpl#makePostCashPayment(double)
	 */
	void makePostCashPayment(double paymentAmount)
			throws java.rmi.RemoteException;

	/* (non-Javadoc)
	 * @see a4.application.CashPaymentImpl#getAmountDue()
	 */
	double getAmountDue() throws java.rmi.RemoteException;

	/* (non-Javadoc)
	 * @see a4.application.CashPaymentImpl#getChange()
	 */
	double getChange() throws java.rmi.RemoteException;

}