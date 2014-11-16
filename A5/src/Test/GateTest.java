package Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import a5.application.Gate;
import a5.application.GateImpl;
import a5.application.GateType;

public class GateTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGate() {
		GateImpl g = new Gate(GateType.exit);
		assertTrue(g.getGateOpenStatus()==false);
		assertTrue(g.getType()==GateType.exit);
	}
	@Test
	public void testCloseGate(){
		GateImpl g = new Gate(GateType.exit);
		g.closeGate();
		assertTrue(g.getGateOpenStatus()==false);	
	}
	@Test
	public void testOpenGate(){
		GateImpl g = new Gate(GateType.exit);
		g.openGate();
		assertTrue(g.getGateOpenStatus()==true);	
	}
}
