package liarsDiceModel;

/**
 * Very simple test bot which randomly throws exceptions or Challenges.
 */
public class TestBot5 extends LiarsDiceBot {

	public String getName() {
		return "Takes Forever";
	}

	public Decision getDecision(GameInfo currentGameInfo) {
		while(true);
	}

}
