package liarsDiceModel;
import programmerTournamentModel.Bot;

//done
public abstract class LiarsDiceBot implements Bot {
	
	public abstract Decision getDecision(GameInfo currentGameInfo);
	
	public void reportGameResults(GameInfo gameInfo){
		//don't have to implement this method. At the end of a game, we will report the results
	}
	
	public static boolean checkValidDecision(Decision decision, Bid currentBid){
		return LiarsDiceGame.isValidDecision(decision, currentBid);
	}
}
