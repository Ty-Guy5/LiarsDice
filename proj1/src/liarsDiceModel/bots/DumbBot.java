package liarsDiceModel.bots;

import java.util.List;

import liarsDiceModel.gameInfo.GameInfo;
import liarsDiceModel.gameInfo.PlayerInfo;
import liarsDiceModel.gameLogic.Bid;
import liarsDiceModel.gameLogic.Challenge;
import liarsDiceModel.gameLogic.Decision;
import liarsDiceModel.gameLogic.LiarsDiceBot;

/**
 * Test bot which simply adds one to the frequency of the current bid, 
 * or Challenges if the current bid's frequency is more than the total number of dice.
 */
public class DumbBot extends LiarsDiceBot {

	@Override
	public String getName() {
		return "Dumb Bot";
	}

	@Override
	public Decision getDecision(GameInfo currentGameInfo) {
		Bid b = currentGameInfo.getCurrentBid();
		if(b == null){
			return new Bid(2, 2);
		}
		/* This prevents a game from never ending */
		else if(b.getFrequency() > getTotalDice(currentGameInfo)){
			return new Challenge();
		}
		/**/
		else{
			return new Bid(b.getFrequency() + 1, b.getFaceValue());
		}
	}
	
	private int getTotalDice(GameInfo currentGameInfo) {
		int totalDice = currentGameInfo.getMyDice().size();
		List<PlayerInfo> players = currentGameInfo.getPlayersInfo();
		for(PlayerInfo p : players){
			totalDice += p.getNumDice();
		}
		return totalDice;
	}

}
