//done
public abstract class Bot {
	
	public abstract String getName();
	
	public abstract Decision getDecision(GameInfo currentGameInfo);
	
	public static boolean checkValidDecision(Decision decision, Bid currentBid){
		return LiarsDiceGame.isValidDecision(decision, currentBid);
	}
}
