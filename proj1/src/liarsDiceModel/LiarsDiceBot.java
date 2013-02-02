package liarsDiceModel;
import programmerTournamentModel.Bot;

//done
public abstract class LiarsDiceBot implements Bot {
	
	public abstract Decision getDecision(GameInfo currentGameInfo);
	
	public static boolean checkValidDecision(Decision decision, Bid currentBid){
		return LiarsDiceGame.isValidDecision(decision, currentBid);
	}
}
