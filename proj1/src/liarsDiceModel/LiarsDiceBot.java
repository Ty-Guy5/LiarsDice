package liarsDiceModel;
import programmerTournamentModel.Bot;
import programmerTournamentModel.GameHistory;

//done
/**
 * Parent class for all contest entries.  Each team will create a class which extends LiarsDiceBot.
 */
public abstract class LiarsDiceBot implements Bot {
	
	/**
	 * This is the method that a game will call when it is this bot's turn. (This method must be implemented.)
	 * 
	 * If this method throws an exception or returns an invalid decision, this bot automatically loses the round (and subsequently loses a die).
	 * A checkValidDecision method is provided for your convenience to ensure a decision is valid before returning it.
	 * It is strongly recommended that you check the validity of each decision before returning it.
	 * @param currentGameInfo All relevant information for the current state of the game.
	 * @return A decision: either a Bid or a Challenge.
	 */
	public abstract Decision getDecision(GameInfo currentGameInfo);
	
	/**
	 * At the end of each game, the game history is given to each bot.  Feel free to ignore this method if you're not into machine learning.  :)
	 * @param gameHistory The history of the game.
	 */
	public void reportGameResults(GameHistory gameHistory){
		//don't have to implement this method. At the end of a game, we will report the results
	}
	
	/**
	 * Checks the validity of the given decision against the current bid.
	 * 
	 * Validity rules:
	 * 1. A Challenge when the current bid is null (first turn of the round) is invalid.
	 * 2. A Bid with frequency greater than the total number of dice remaining in the game (among all players) is invalid.
	 * 3. To be valid, a new bid must increase (in relation to the current bid) either the frequency, or the face value, or both.
	 * 4. As long as it is not the first turn of a round (currentBid == null), a Challenge is always valid.
	 * @param decision The decision to be checked for validity.
	 * @param currentBid The current bid. (The current bid will be null on the first turn of the round).
	 * @return true if the given decision is valid, false otherwise
	 */
	public static boolean checkValidDecision(Decision decision, Bid currentBid){
		return LiarsDiceGame.isValidDecision(decision, currentBid);
	}
}
