package liarsDiceModel.testBots;

import liarsDiceModel.gameInfo.GameInfo;
import liarsDiceModel.gameLogic.Challenge;
import liarsDiceModel.gameLogic.Decision;
import liarsDiceModel.gameLogic.LiarsDiceBot;


/**
 * Very simple test bot which randomly throws exceptions or Challenges.
 */
public class TestBot0 extends LiarsDiceBot {

	public String getName() {
		return "Problematic";
	}

	public Decision getDecision(GameInfo currentGameInfo) {
		if(Math.random() > 0){
			int[] array = new int[1];
			array[2] = 3; //throw Exception
		}
		return new Challenge();
	}

}
