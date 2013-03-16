package model.liarsDice.gameInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.liarsDice.gameLogic.Bid;
import model.liarsDice.gameLogic.Die;



/**
 * Holds the relevant information for the current state of the game, including the current bid, the game history so far, 
 * the dice of the player whose turn it is, and a PlayerInfo object for each other player in the game.
 */
public class GameInfo {
	private Bid currentBid;
	private GameHistory gameHistory;
	private List<Die> myDice;
	private int myPlayerID;
	private List<PlayerInfo> playersInfo;
	
	/**
	 * Default constructor.
	 */
	public GameInfo(){
		init(null, new GameHistory(), new ArrayList<Die>(), 0, new ArrayList<PlayerInfo>());
	}
	
	/**
	 * Constructor.
	 * @param currentBid The current bid.
	 * @param gameHistory The history of the game so far.
	 * @param myDice The dice of the player whose turn it is.
	 * @param players List of PlayerInfo objects (one for each player).
	 */
	public GameInfo(Bid currentBid, GameHistory gameHistory, List<Die> myDice, int myPlayerID, List<PlayerInfo> players) {
		init(currentBid, gameHistory, myDice, myPlayerID, players);
	}
	
	/**
	 * Copy constructor.  Creates a deep (unmodifiable) copy of everything in gi.
	 * @param gi The GameInfo object to be copied.
	 */
	public GameInfo(GameInfo gi){
		init(gi.getCurrentBid(), gi.getGameHistory(), gi.getMyDice(), gi.getMyPlayerID(), gi.getPlayersInfo());
	}

	/**
	 * Creates a deep (unmodifiable) copy of all parameters.
	 * @param currentBid The current bid.
	 * @param gameHistory The history of the game so far.
	 * @param myDice The dice of the player whose turn it is.
	 * @param myPlayerID The id of the player whose turn it is.
	 * @param playersInfo List of PlayerInfo objects (one for each player).
	 */
	public void init(Bid currentBid, GameHistory gameHistory, List<Die> myDice, int myPlayerID, List<PlayerInfo> playersInfo) {
		this.currentBid = currentBid;
		this.gameHistory = new GameHistory(gameHistory);
		this.myDice = Collections.unmodifiableList(myDice);
		this.myPlayerID = myPlayerID;
		this.playersInfo = Collections.unmodifiableList(playersInfo);
	}
	
	/**
	 * @return The current bid or null if there are no bids yet (first turn of each round).
	 */
	public Bid getCurrentBid() {
		return currentBid;
	}

	/**
	 * @return The game history.
	 */
	public GameHistory getGameHistory() {
		return gameHistory;
	}

	/**
	 * @return The dice of the player whose turn it is.
	 */
	public List<Die> getMyDice() {
		return myDice;
	}
	
	/**
	 * @return The ID of the player whose turn it is.
	 */
	public int getMyPlayerID() {
		return myPlayerID;
	}
	
	/**
	 * @return List of PlayerInfo objects (one for each player).
	 */
	public List<PlayerInfo> getPlayersInfo() {
		return playersInfo;
	}
	
	/**
	 * @return The ID of the winner of the game, or 0 if there is no winner (the game isn't over yet).
	 */
	public int getWinnerID()
	{
		int winner = 0;
		
		for(PlayerInfo p : playersInfo){
			if(p.getNumDice() > 0){
				if (winner != 0)
					return 0;
				winner = p.getID();
			}
		}
		
		return winner;
	}
	
	/**
	 * @return The total number of dice (between all players) remaining in the game.
	 */
	public int getTotalDice() {
		int totalDice = myDice.size();
		for(PlayerInfo p : playersInfo){
			totalDice += p.getNumDice();
		}
		return totalDice;
	}
	
	/**
	 * Checks to see if there is only one player with dice left.
	 * @return true if game is over, false otherwise.
	 */
	public boolean isGameOver() {
		return getWinnerID() != 0;
	}
}
