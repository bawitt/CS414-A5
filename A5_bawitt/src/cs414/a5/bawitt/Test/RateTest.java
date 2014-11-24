package cs414.a5.bawitt.Test;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a5.bawitt.common.Rate;
import cs414.a5.bawitt.server.RateImpl;

public class RateTest {
	double flatRate = 50;
	double standardRate = 4;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRate() throws RemoteException  {
		Rate r = new RateImpl(standardRate, flatRate);
		assertTrue(r.getStandardRate()==4);
		assertTrue(r.getFlatRate()==50);
	}
	@Test
	public void testRateNull() throws RemoteException {
		Rate r = new RateImpl();
		assertTrue(r.getStandardRate()==0);
		assertTrue(r.getFlatRate()==0);
	}
	@Test
	public void testSetStandardRate() throws RemoteException {
		Rate r = new RateImpl();
		r.setStandardRate(10);
		assertTrue(r.getStandardRate()==10);
	}
	@Test
	public void testSetFlatRate() throws RemoteException {
		Rate r = new RateImpl();
		r.setFlatRate(5);
		assertTrue(r.getFlatRate()==5);
	}

}
