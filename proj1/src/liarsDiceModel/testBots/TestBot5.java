package liarsDiceModel.testBots;

import liarsDiceModel.gameInfo.GameInfo;
import liarsDiceModel.gameLogic.Challenge;
import liarsDiceModel.gameLogic.Decision;
import liarsDiceModel.gameLogic.LiarsDiceBot;


public class TestBot5 extends LiarsDiceBot {

	public String getName() {
		return "Infinite Looper";
	}

	public Decision getDecision(GameInfo currentGameInfo) {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
		}
		return new Challenge();
	}
}