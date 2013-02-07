package liarsDiceModel;


public class TestBot1 extends LiarsDiceBot {

	@Override
	public String getName() {
		return "Challenger";
	}

	@Override
	public Decision getDecision(GameInfo currentGameInfo) {
		//TODO: extend bot decision-making process
		return new Challenge();
	}

}
