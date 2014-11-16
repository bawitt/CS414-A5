package Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import a5.application.ElectronicPayment;
import a5.application.ElectronicPaymentImpl;
import a5.application.PaymentType;

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
	public void testElectronicPayment() {
		accountNum = "1122334455667788";
		expDate = "12/14";
		ElectronicPayment ep = new ElectronicPayment(50, accountNum, expDate);
		assertTrue(ep.getAmountDue()==50);
		assertTrue(ep.getPaymentType()==PaymentType.electronic);
	}
	@Test
	public void testIsDateValid(){
		accountNum = "1122334455667788";
		expDate = "1214";
		ElectronicPaymentImpl ep1 = new ElectronicPayment(50, accountNum, expDate);
		assertTrue(!ep1.isDateValid());
		expDate = "12/14";
		ElectronicPaymentImpl ep2 = new ElectronicPayment(50, accountNum, expDate);
		assertTrue(ep2.isDateValid());
	}
	@Test
	public void testIsActNum(){
		accountNum = "11223344556677";
		expDate = "12/14";
		ElectronicPaymentImpl ep1 = new ElectronicPayment(50, accountNum, expDate);
		assertTrue(!ep1.isActNumValid());
		accountNum = "11223344s5667788";
		ElectronicPaymentImpl ep2 = new ElectronicPayment(50, accountNum, expDate);
		assertTrue(!ep2.isActNumValid());
		accountNum = "1122334455667788";
		ElectronicPaymentImpl ep3 = new ElectronicPayment(50, accountNum, expDate);
		assertTrue(ep3.isActNumValid());
	}
}
