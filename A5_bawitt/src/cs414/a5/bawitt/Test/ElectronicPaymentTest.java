package cs414.a5.bawitt.Test;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a5.bawitt.common.ElectronicPayment;
import cs414.a5.bawitt.server.ElectronicPaymentImpl;
import cs414.a5.bawitt.server.PaymentTypeImpl;

public class ElectronicPaymentTest {
	String accountNum;
	String expDate;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testElectronicPayment() throws RemoteException {
		accountNum = "1122334455667788";
		expDate = "12/14";
		ElectronicPaymentImpl ep = new ElectronicPaymentImpl(50, accountNum, expDate,0);
		assertTrue(ep.getAmountDue()==50);
		assertTrue(ep.getPaymentType()==PaymentTypeImpl.electronic);
	}
	@Test
	public void testIsDateValid() throws RemoteException{
		accountNum = "1122334455667788";
		expDate = "1214";
		ElectronicPayment ep1 = new ElectronicPaymentImpl(50, accountNum, expDate,0);
		assertTrue(!ep1.isDateValid());
		expDate = "12/14";
		ElectronicPayment ep2 = new ElectronicPaymentImpl(50, accountNum, expDate,0);
		assertTrue(ep2.isDateValid());
	}
	@Test
	public void testIsActNum() throws RemoteException{
		accountNum = "11223344556677";
		expDate = "12/14";
		ElectronicPayment ep1 = new ElectronicPaymentImpl(50, accountNum, expDate,0);
		assertTrue(!ep1.isActNumValid());
		accountNum = "11223344s5667788";
		ElectronicPayment ep2 = new ElectronicPaymentImpl(50, accountNum, expDate,0);
		assertTrue(!ep2.isActNumValid());
		accountNum = "1122334455667788";
		ElectronicPayment ep3 = new ElectronicPaymentImpl(50, accountNum, expDate,0);
		assertTrue(ep3.isActNumValid());
	}
}
