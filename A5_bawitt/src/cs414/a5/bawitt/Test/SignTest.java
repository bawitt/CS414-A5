package cs414.a5.bawitt.Test;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a5.bawitt.common.Sign;
import cs414.a5.bawitt.common.Spaces;
import cs414.a5.bawitt.server.SignImpl;
import cs414.a5.bawitt.server.SignStatusImpl;
import cs414.a5.bawitt.server.SpacesImpl;

public class SignTest {
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSign()  throws RemoteException {
		//Spaces sp = new SpacesImpl(4,2);
		Sign sn = new SignImpl();
		assertTrue(sn.getSignStatus()==SignStatusImpl.vacancy);
	}
	@Test
	public void testRefreshSign() throws RemoteException {
		Spaces sp = new SpacesImpl(4,2);
		Sign sn = new SignImpl();
		sn.refreshSign(sp);
		assertTrue(sn.getSignStatus()==SignStatusImpl.vacancy);
		sp.setNumSpaces(1);
		sn.refreshSign(sp);
		assertTrue(sn.getSignStatus()==SignStatusImpl.full);
	}
}
