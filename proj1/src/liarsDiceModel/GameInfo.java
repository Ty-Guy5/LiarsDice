package liarsDiceModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import programmerTournamentModel.GameHistory;

/**
 * Holds the relevant information for the current state of the game, including the current bid, the game history so far, 
 * the dice of the player whose turn it is, and a PlayerInfo object for each other player in the game.
 */
public class GameInfo {
	private Bid currentBid;
	private GameHistory gameHistory;
	private List<Die> myDice;
	private List<PlayerInfo> playersInfo;
	
	/**
	 * Default constructor.
	 */
	public GameInfo(){
		init(null, new GameHistory(), new ArrayList<Die>(), new ArrayList<PlayerInfo>());
	}
	
	/**
	 * Constructor.
	 * @param currentBid The current bid.
	 * @param gameHistory The history of the game so far.
	 * @param myDice The dice of the player whose turn it is.
	 * @param players List of PlayerInfo objects (one for each player).
	 */
	public GameInfo(Bid currentBid, GameHistory gameHistory, List<Die> myDice, List<PlayerInfo> players) {
		init(currentBid, gameHistory, myDice, players);
	}
	
	/**
	 * Copy constructor.  Creates a deep (unmodifiable) copy of everything in gi.
	 * @param gi The GameInfo object to be copied.
	 */
	public GameInfo(GameInfo gi){
		init(gi.getCurrentBid(), gi.getGameHistory(), gi.getMyDice(), gi.getPlayersInfo());
	}

	/**
	 * Creates a deep (unmodifiable) copy of all parameters.
	 * @param currentBid The current bid.
	 * @param gameHistory The history of the game so far.
	 * @param myDice The dice of the player whose turn it is.
	 * @param playersInfo List of PlayerInfo objects (one for each player).
	 */
	public void init(Bid currentBid, GameHistory gameHistory, List<Die> myDice, List<PlayerInfo> playersInfo) {
		this.currentBid = currentBid;
		this.gameHistory = new GameHistory(gameHistory);
		this.myDice = Collections.unmodifiableList(myDice);
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
}
