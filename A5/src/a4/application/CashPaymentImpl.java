package a4.application;

public interface CashPaymentImpl {

	void makeInitialCashPayment();

	void makePostCashPayment(double paymentAmount);

	double getAmountDue();

	double getChange();

}