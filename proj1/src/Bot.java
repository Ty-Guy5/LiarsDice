
public abstract class Bot {
	
	public String getName();
	
	public Decision getDecision(GameInfo currentGameInfo);
	
	public static boolean checkValidDecision(Decision decision, Bid currentBid){
		return LiarsDiceGame.isValidDecision(Decision decision, Bid currentBid);
	}
}
