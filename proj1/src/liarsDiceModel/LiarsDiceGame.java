package liarsDiceModel;

import java.util.ArrayList;
import java.util.List;

import programmerTournamentModel.Bot;
import programmerTournamentModel.Game;
import programmerTournamentModel.GameHistory;

/**
 * Contains the logic for running a game of Liar's Dice.  Keeps track of the game history and players.
 */
public class LiarsDiceGame implements Game {
	GameHistory history;
	int numPlayers;
	int turnIndex;
	List<LiarsDicePlayer> players;
	Bid currentBid;
//	int counter = 0;
	boolean debug = false;
	
	/**
	 * Constructor.
	 * @param players A list of all the players who will participate in the game.
	 */
	public LiarsDiceGame(List<LiarsDicePlayer> players){
		history = new GameHistory();
		numPlayers = players.size();
		this.players = players;
		turnIndex = 0;
		currentBid = null;
	}
	
	/**
	 * Plays out the game with the players given to the constructor. (Until only one player has dice.)
	 */
	public Player runGame() {
		//TODO make sure that tournament decides play order before passing the list here (pass in bots in play order)
		//turnIndex = 0;
		while(numPlayers > 1){
//			System.out.println("new round " + counter++);
			playRound();
		}
		
		//determine the winner and report the results to everyone
		LiarsDicePlayer winner = null;
		for(LiarsDicePlayer p : players){
			p.reportGameResults(new GameHistory(history));
			if(p.getDice().size() > 0){
				assert (winner == null) : "error: multiple winners???";
				winner = p;
			}
		}
		assert (winner != null) : "error: runGame didn't have anyone with dice left!";
		return winner;		
	}
	
	/**
	 * Plays a single round of the game. (Until a player challenges, throws an exception, or returns an invalid decision.)
	 */
	private void playRound() {
		Result roundResult = Result.UNFINISHED;
		currentBid = null;
		history.addRound(new Round());
//		int roundcounter = 0;
		while(roundResult == Result.UNFINISHED){
//			System.out.println("roundresult = unfinished " + roundcounter++);
			//create the gameInfo object
			ArrayList<PlayerInfo> allPlayersInfo = new ArrayList<PlayerInfo>();
			for(Player p : players){
				allPlayersInfo.add(new PlayerInfo((LiarsDicePlayer)p));
			}
			GameInfo gi = new GameInfo(currentBid, new GameHistory(history), 
					players.get(turnIndex).getDice(), allPlayersInfo);
			
			//get the player's decision and dish out the consequences
			roundResult = collectAndProcessDecision(gi, roundResult);
//			System.out.println("currentBid after process: " + currentBid);
		}
		history.endRound(roundResult);
		
	}

	/**
	 * Given a player's decision, processes that decision and updates whose turn it is (and, if applicable, removes a die from a player).
	 * @param gi Current state of the game.
	 * @param roundResult Result of the current round. (Result.UNFINISHED if round isn't over yet)
	 * @return Result of the current round. (Result.UNFINISHED if round isn't over yet)
	 */
	private Result collectAndProcessDecision(GameInfo gi, Result roundResult) {
		
		//collect decision
		Decision decision = null;
		try{
//			int dice = players.get(turnIndex).getDice().size();
//			System.out.println(players.get(turnIndex).getID() + ": " + dice);
			decision = players.get(turnIndex).getDecision(gi);
			if(decision instanceof Bid){
				Bid b = (Bid)decision;
//				System.out.println("bid: " + b.getFrequency() + " " + b.getDieNumber() + "'s");
			}
		}catch(Exception e){ //checking against exceptions thrown by bot
			if(debug)
				e.printStackTrace(); //TODO log instead of printing error
			roundResult = Result.EXCEPTION;
			players.get(turnIndex).getStatistics().increaseExceptions();
			takeAwayDieAndSetNextTurn(turnIndex);
			return roundResult;
		}
		
		//process decision
		if(!isValidDecision(decision, currentBid)){
			roundResult = Result.INVALIDDECISION;
			players.get(turnIndex).getStatistics().increaseInvalidDecisions();
			//maybe log later
			takeAwayDieAndSetNextTurn(turnIndex);
		}
		else if(decision instanceof Challenge){
			if(numberOfDiceWithValue(currentBid.getFaceValue()) >= currentBid.getFrequency()){
				takeAwayDieAndSetNextTurn(turnIndex);
				roundResult = Result.LOSING_CHALLENGE;
			}
			else{
				takeAwayDieAndSetNextTurn(previousTurnIndex(turnIndex));
				roundResult = Result.WINNING_CHALLENGE;
			}
		}
		else //normal bid
		{
			history.addTurn(new Turn(players.get(turnIndex).getID(), decision));
			Bid bid = (Bid)decision;
			currentBid = bid;
//			System.out.println("currentBid during process: " + currentBid);
			turnIndex = nextTurnIndex(turnIndex);
		}
		return roundResult;
	}

	/**
	 * Determines whose turn it is next. (Skips over players with no dice.)
	 * @param turnIndex The current turn index.
	 * @return The next turn index.
	 */
	private int nextTurnIndex(int turnIndex) {
		int temp = turnIndex;
		//System.out.println("turnIndex: " + turnIndex);
		do{
			temp++;
			//System.out.println("temp: " + temp);
			if(temp >= players.size()){
				temp = 0;
			}
			if(temp == turnIndex){
				//System.out.println("nextTurnIndex error: turn == turnIndex");
				//not error - only one player left with dice
				break;
			}
		}while(players.get(temp).getDice().size() <= 0);
		return temp;
	}

	/**
	 * Determines whose turn it was last. (Skips over players with no dice.)
	 * @param turnIndex The current turn index.
	 * @return The last turn index.
	 */
	private int previousTurnIndex(int turnIndex) {
		int temp = turnIndex;
		do{
			temp--;
			if(temp < 0){
				temp = players.size() - 1;
			}
			if(temp == turnIndex){
				//System.out.println("previousTurnIndex error: turn == turnIndex");
				//not error - only one player left with dice
				break;
			}
		}while(players.get(temp).getDice().size() <= 0);
		return temp;
	}

	/**
	 * Counts up the total number of dice that count as the given dieNumber. (dieNumber + wilds)
	 * @param dieNumber The face value to total.
	 * @return Total number of dice with face value dieNumber + wild dice.
	 */
	private int numberOfDiceWithValue(int dieNumber) {
		int count = 0;
		for(LiarsDicePlayer p : players){
			for(Die d : p.getDice()){
				if(d.getValue() == dieNumber || d.getValue() == Die.WILD){
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * Takes away a die from the player at loseIndex and sets whose turn it is next.
	 * If this player has no more dice, this updates the number of players still in the game.
	 * @param loseIndex The player who must lose a die.
	 */
	private void takeAwayDieAndSetNextTurn(int loseIndex) {
		players.get(loseIndex).removeDie();
		if(players.get(loseIndex).getDice().size() <= 0){
			numPlayers--;
			this.turnIndex = nextTurnIndex(loseIndex);
		}
		else{
			this.turnIndex = loseIndex;
		}
	}

	/**
	 * Checks the validity of the given decision against the current bid.
	 * 
	 * Validity rules:
	 * 1. A Challenge when the current bid is null (first turn of the round) is invalid.
	 * 2. A Bid with frequency greater than the total number of dice left in the game is invalid.
	 * 3. To be valid, a new bid must increase (in relation to the current bid) either the frequency, or the face value, or both.
	 * 4. As long as it is not the first turn of a round (currentBid == null), a Challenge is always valid.
	 * @param decision The decision to be checked for validity.
	 * @param currentBid The current bid. (The current bid will be null on the first turn of the round).
	 * @return true if the given decision is valid, false otherwise
	 */
	public static boolean isValidDecision(Decision decision, Bid currentBid){
		if(decision == null){
			return false;
		}
		if(decision instanceof Challenge){
			if(currentBid == null){
				return false; //can't challenge first turn of the round
			}
			return true; //otherwise challenge is always valid
		}
		else{
			Bid bid = (Bid)decision;
//			System.out.println("bid: " + bid);
//			System.out.println("currentbid: " + currentBid);
			if(bid.getFaceValue() < 2 || bid.getFaceValue() > 6){
				return false; //invalid dieNumber
			}
			if(currentBid == null){ //first bid of round
				if(bid.getFrequency() < 1){
					return false; //can't bid 0 or less
				}
				return true;
			}
			else{ //not first bid of round
				if(bid.getFrequency() < currentBid.getFrequency()){
					return false; //frequency must be >= current frequency
				}
				else if(bid.getFrequency() > currentBid.getFrequency()){
					return true; //an increased frequency is always valid (assuming a valid dieNumber - above)
				}
				else{ //frequency == current frequency
					if(bid.getFaceValue() <= currentBid.getFaceValue()){
						return false; //must increase the dieNumber if not increasing frequency
					}
					return true;
				}
			}
		}
	}
}
