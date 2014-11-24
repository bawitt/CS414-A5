package cs414.a5.bawitt.Test;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a5.bawitt.common.Payment;
import cs414.a5.bawitt.server.PaymentImpl;
import cs414.a5.bawitt.server.PaymentTypeImpl;

public class PaymentTest {
	PaymentTypeImpl paymentType= PaymentTypeImpl.cash;
	double amountDue=45;
	double originalAmountDue;
	Date paymentDate;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPayment()  throws RemoteException {
		Payment p = new PaymentImpl(amountDue, paymentType,0);
		assertTrue(p.getAmountDue()==45);
		assertTrue(p.getOriginalAmountDue()==45);
	}
}
