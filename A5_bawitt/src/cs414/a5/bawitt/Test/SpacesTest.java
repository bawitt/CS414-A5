package cs414.a5.bawitt.Test;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a5.bawitt.common.Spaces;
import cs414.a5.bawitt.server.SpacesImpl;

public class SpacesTest {
	int numSpaces = 4;
	int usedSpaces = 2;

	@Before
	public void setUp() throws Exception {
		numSpaces = 4;
		usedSpaces = 2;
	}
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSpaces() throws RemoteException {
		Spaces s = new SpacesImpl(numSpaces,usedSpaces);
		assertTrue(s.getUsedSpaces()==2);
		assertTrue(s.getNumSpaces()==4);
	}
	@Test
	public void testIncEnter() throws RemoteException{
		Spaces s = new SpacesImpl(numSpaces,usedSpaces);
		s.incEnter();
		assertTrue(s.getUsedSpaces()==3);
	}
	@Test
	public void testDecExit() throws RemoteException{
		Spaces s = new SpacesImpl(numSpaces,usedSpaces);
		s.decExit();
		assertTrue(s.getUsedSpaces()==1);
	}
}
