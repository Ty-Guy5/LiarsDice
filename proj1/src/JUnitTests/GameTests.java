package JUnitTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import liarsDiceModel.*;

public class GameTests {
	
	public void setupObjects() {
	}

	@Test
	public void testLiarsDiceGame() {
		fail("Not yet implemented");
	}

	@Test
	public void testRunGame() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsValidDecision() 
	{
		Decision c = new Challenge();
		GameInfo gi = new GameInfo();
		assertFalse(LiarsDiceGame.isValidDecision(c, gi));
	}

	@Test
	public void testSetTimeout() {
		fail("Not yet implemented");
	}
}
