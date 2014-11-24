package cs414.a5.bawitt.Test;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.joda.time.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a5.bawitt.common.Rate;
import cs414.a5.bawitt.common.Ticket;
import cs414.a5.bawitt.server.RateImpl;
import cs414.a5.bawitt.server.TicketImpl;

public class TicketTest {
	int id=5;


	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTicket()  throws RemoteException{
		Rate r = new RateImpl(4,100);
		//Rate r = new Rate(4,100);
		Ticket tn = new TicketImpl(id, r);
		assertTrue(tn.getID() == id);
		assertTrue(tn.getTicketStandardRate()==r.getStandardRate());
	}
	@Test
	public void testTicketNull()  throws RemoteException{
		Rate r = new RateImpl(4,100);
		Ticket tn = new TicketImpl(r);
		assertTrue(tn.getID()==-1);
	}
	@Test
	public void testGetDurationHours()  throws RemoteException{
		Rate r = new RateImpl(4,100);
		//Rate r = new Rate(4,100);
		Ticket tn = new TicketImpl(id, r);
		DateTime paymentDate = new DateTime();
		tn.setPaymentDate(paymentDate);
		assertTrue(tn.getDurationHours()==1);
	}
	@Test
	public void testGetAmountDue() throws RemoteException{
		Rate r = new RateImpl(4,100);
		Ticket tn = new TicketImpl(id, r);
		DateTime paymentDate = new DateTime();
		tn.setPaymentDate(paymentDate);
		assertTrue(tn.getAmountDue()==(r.getStandardRate()*tn.getDurationHours()));
	}
}
