package cs414.a5.bawitt.Test;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a5.bawitt.common.Gate;
import cs414.a5.bawitt.server.GateImpl;
import cs414.a5.bawitt.server.GateTypeImpl;

public class GateTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGate() throws RemoteException  {
		Gate g = new GateImpl(GateTypeImpl.exit);
		assertTrue(g.getGateOpenStatus()==false);
		assertTrue(g.getType()==GateTypeImpl.exit);
	}
	@Test
	public void testCloseGate() throws RemoteException {
		Gate g = new GateImpl(GateTypeImpl.exit);
		g.closeGate();
		assertTrue(g.getGateOpenStatus()==false);	
	}
	@Test
	public void testOpenGate() throws RemoteException {
		Gate g = new GateImpl(GateTypeImpl.exit);
		g.openGate();
		assertTrue(g.getGateOpenStatus()==true);	
	}
}
