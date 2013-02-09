package programmerTournamentModel;
import java.util.ArrayList;
import java.util.List;
//done

import liarsDiceModel.Result;
import liarsDiceModel.Round;
import liarsDiceModel.Turn;

/**
 * Keeps track of the history of a game (in the form of a collection of Rounds, which are a collection of Turns).
 */
public class GameHistory {
	private List<Round> rounds;
	
	/**
	 * Constructor.
	 */
	public GameHistory(){
		rounds = new ArrayList<Round>();
		rounds.add(new Round());
	}
	
	/**
	 * Copy constructor. (Makes a deep copy.)
	 * @param gh The GameHistory object to be copied.
	 */
	public GameHistory(GameHistory gh){
		//deep copy:
		List<Round> tempList = new ArrayList<Round>();
		for(int i = 0; i < gh.getRounds().size(); i++){
			tempList.add(new Round(gh.getRounds().get(i)));
		}
		rounds = tempList;
	}

	/**
	 * @return List of this game's Rounds.
	 */
	public List<Round> getRounds() {
		return rounds;
	}

	/**
	 * Adds a Round to the history.
	 * @param round Round to be added.
	 */
	public void addRound(Round round){
		rounds.add(round);
	}
	
	/**
	 * Adds a Turn to the current Round in the history.
	 * @param round Turn to be added.
	 */
	public void addTurn(Turn turn){
		rounds.get(rounds.size() - 1).addTurn(turn);
	}
	
	/**
	 * Ends the current Round with the given Result.
	 * @param result End result of the current Round.
	 */
	public void endRound(Result result){
		rounds.get(rounds.size() - 1).end(result);
	}
}
