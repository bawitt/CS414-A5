package Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import a5.application.Sign;
import a5.application.SignImpl;
import a5.application.SignStatus;
import a5.application.Spaces;
import a5.application.SpacesImpl;

public class SignTest {
	SpacesImpl sp = new Spaces(4,2);
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSign() {
		SignImpl sn = new Sign();
		assertTrue(sn.getSignStatus()==SignStatus.vacancy);
	}
	@Test
	public void testRefreshSign(){
		SignImpl sn = new Sign();
		sn.refreshSign(sp);
		assertTrue(sn.getSignStatus()==SignStatus.vacancy);
		sp.setNumSpaces(1);
		sn.refreshSign(sp);
		assertTrue(sn.getSignStatus()==SignStatus.full);
	}
}
