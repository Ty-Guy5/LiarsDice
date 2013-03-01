package liarsDiceModel.testBots;

import liarsDiceModel.gameInfo.GameInfo;
import liarsDiceModel.gameLogic.Bid;
import liarsDiceModel.gameLogic.Decision;
import liarsDiceModel.gameLogic.LiarsDiceBot;


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
