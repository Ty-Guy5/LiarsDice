package liarsDiceModel;


public class TestBot2 extends LiarsDiceBot {

	@Override
	public String getName() {
		return "TestBot2";
	}

	@Override
	public Decision getDecision(GameInfo currentGameInfo) {
		return new Bid(5,5);
	}

}
