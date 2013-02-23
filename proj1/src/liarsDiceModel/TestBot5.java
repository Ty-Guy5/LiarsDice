package liarsDiceModel;

/**
 * Test bot which loops infinitely.
 */
public class TestBot5 extends LiarsDiceBot {

	public String getName() {
		return "Takes Forever";
	}

	public Decision getDecision(GameInfo currentGameInfo) {
		while(true);
	}

}
