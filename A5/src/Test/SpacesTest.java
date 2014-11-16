package Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import a5.application.Spaces;
import a5.application.SpacesImpl;

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
	public void testSpaces() {
		SpacesImpl s = new Spaces(numSpaces,usedSpaces);
		assertTrue(s.getUsedSpaces()==2);
		assertTrue(s.getNumSpaces()==4);
	}
	@Test
	public void testIncEnter(){
		SpacesImpl s = new Spaces(numSpaces,usedSpaces);
		s.incEnter();
		assertTrue(s.getUsedSpaces()==3);
	}
	@Test
	public void testDecExit(){
		SpacesImpl s = new Spaces(numSpaces,usedSpaces);
		s.decExit();
		assertTrue(s.getUsedSpaces()==1);
	}
}
