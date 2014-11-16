package Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import a5.application.CashPayment;
import a5.application.CashPaymentImpl;
import a5.application.PaymentType;

public class CashPaymentTest {
	double amountDue=20;
	double change;
	double paymentAmount=10;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCashPayment() {
		CashPayment cp = new CashPayment(amountDue, paymentAmount);
		assertTrue(cp.getPaymentType()==PaymentType.cash);
		assertTrue(cp.getChange()==0);
		assertTrue(cp.getAmountDue()==10);
	}
	@Test
	public void testMakeInitialCashPayment() {
		paymentAmount = 15;
		CashPaymentImpl cp = new CashPayment(amountDue, paymentAmount);
		assertTrue(cp.getChange()==0);
		assertTrue(cp.getAmountDue()==5);
	}
	@Test
	public void testMakePostCashPayment(){
		double secondPayment=5;
		double thirdPayment=10;
		CashPaymentImpl cp = new CashPayment(amountDue, paymentAmount);
		cp.makePostCashPayment(secondPayment);
		assertTrue(cp.getAmountDue()==5);
		assertTrue(cp.getChange()==0);
		cp.makePostCashPayment(thirdPayment);
		assertTrue(cp.getAmountDue()==-5);
		assertTrue(cp.getChange()==5);
	}

}
