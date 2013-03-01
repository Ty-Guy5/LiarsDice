package liarsDiceModel.testBots;

import liarsDiceModel.gameInfo.GameInfo;
import liarsDiceModel.gameLogic.Challenge;
import liarsDiceModel.gameLogic.Decision;
import liarsDiceModel.gameLogic.LiarsDiceBot;


/**
 * Very simple test bot which always Challenges.
 */
public class TestBot1 extends LiarsDiceBot {

	@Override
	public String getName() {
		return "Challenger";
	}

	@Override
	public Decision getDecision(GameInfo currentGameInfo) {
		return new Challenge();
	}

}
