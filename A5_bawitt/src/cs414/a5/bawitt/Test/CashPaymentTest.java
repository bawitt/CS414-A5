package cs414.a5.bawitt.Test;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a5.bawitt.common.CashPaymentInt;
import cs414.a5.bawitt.server.CashPaymentImpl;
import cs414.a5.bawitt.server.PaymentTypeImpl;

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
	public void testCashPayment() throws RemoteException {
		CashPaymentImpl cp = new CashPaymentImpl(0, amountDue);
		assertTrue(cp.getPaymentType()==PaymentTypeImpl.cash);
		assertTrue(cp.getChange()==0);
		assertTrue(cp.getAmountDue()==10);
	}
	@Test
	public void testMakeInitialCashPayment() throws RemoteException {
		paymentAmount = 15;
		CashPaymentImpl cp = new CashPaymentImpl(0, amountDue);
		assertTrue(cp.getChange()==0);
		assertTrue(cp.getAmountDue()==5);
	}
	@Test
	public void testMakePostCashPayment() throws RemoteException{
		double secondPayment=5;
		double thirdPayment=10;
		CashPaymentImpl cp = new CashPaymentImpl(0, amountDue);
		cp.makePostCashPayment(secondPayment);
		assertTrue(cp.getAmountDue()==5);
		assertTrue(cp.getChange()==0);
		cp.makePostCashPayment(thirdPayment);
		assertTrue(cp.getAmountDue()==-5);
		assertTrue(cp.getChange()==5);
	}

}
