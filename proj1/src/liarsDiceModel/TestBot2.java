package liarsDiceModel;

/**
 * Very simple test bot which always returns the same bid. (Will probably be invalid decision a lot.)
 */
public class TestBot2 extends LiarsDiceBot {

	@Override
	public String getName() {
		return "Consistent";
	}

	@Override
	public Decision getDecision(GameInfo currentGameInfo) {
		return new Bid(20,5);
	}

}
