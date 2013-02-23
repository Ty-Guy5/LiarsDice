package liarsDiceModel;

import java.util.ArrayList;
import java.util.List;

import programmerTournamentModel.GameFactory;
import programmerTournamentModel.Tournament;

/**
 * This class serves as an interface for the view.  The view classes only need to call methods on this class.  
 * They don't need to rely on other classes in the model.
 */
public class Facade {
	private Tournament tournament;
	
	/**
	 * Constructor.
	 */
	public Facade()
	{
		//default to Liar's Dice as the game choice
        chooseGame(new LiarsDiceGameFactory());
	}

	/**
	 * @return A list of all players which can be used in the tournament.
	 */
	public List<Player> getPlayers() {
		//TODO should this make a deep copy of the list, so a gui can't modify?
		return tournament.getPlayers();
	}

	/**
	 * Sets which game the tournament should run.
	 * @param factory A GameFactory instance specific to the game which should be used for the tournament.
	 */
	public void chooseGame(GameFactory factory) {
		tournament = new Tournament(factory);
	}
	
	/**
	 * Changes which game the tournament should be running.
	 * @param gameName Name of the game which should be changed to.
	 */
	public void changeGame(String gameName){
		//When adding in new games, expand this switch statement:
		switch(gameName){ //note: switching on a string will not work on anything before Java 1.7
			case "LiarsDice":
				tournament = new Tournament(new LiarsDiceGameFactory());
				break;
			default:
				tournament = new Tournament(new LiarsDiceGameFactory());
				break;
		}
	}

	/**
	 * Runs a tournament with the given constraints.
	 * @param botsPerGame How many bots will participate in each game.
	 * @param gameRepeats How many times to repeat each game.
	 */
	public void runTournament(int botsPerGame, int gameRepeats) {
		tournament.runTournament(botsPerGame, gameRepeats);
	}
	
	/**
	 * Sets the allowed length of time each bot will have to take a turn.
	 * @param timeoutInSeconds Time (in milliseconds) allowed for each bot's turn.
	 */
	public void setTimeout(double timeoutInSeconds) {
		tournament.setTimeout(timeoutInSeconds);
	}

	/**
	 * Adds or removes a player from the collection of players which will participate in the tournament.
	 * @param b true if the player should be added, false if the player should be removed.
	 * @param index The index (with respect to all players) of the player to be added to or removed from the collection of participating players.
	 */
	public void addOrRemovePlayer(Boolean b, int index) {
		if(b){
			tournament.addPlayer(index);
		}
		else{
			tournament.removePlayer(index);
		}
	}
	
	//TODO Still not sure how to step through turns in the Play view. May 
	// need more methods for that here. Think about how the view will get 
	// the info in the GameInfo object, including history of moves and 
	// current bid.
	
	
	/***************************TODO implement or scrap*************************/
	
	public int numGamesForSettings(int botsPerGame, int gameRepeats) {
		return 0;
	}
	
	/* The idea for getParticipants and setParticipants is that the GUI will want 
	 * to tell the Tournament which bots are participating. I'm not sure yet all 
	 * the details of these transactions. Maybe the GameFactory will have to 
	 * offer a list of the class names of the bots so that the Play view can 
	 * display them for the user to select from. Then the GameFactory would 
	 * have a getPlayers function that takes a list of class names and returns 
	 * only players on the list.
	 * 
	 * Maybe we should just make Bot a generic class again.
	 */
	public List<String> getParticipants() {
		List<String> participants = new ArrayList<String>(); 
		for (Player p : tournament.getPlayers()) {
			participants.add(p.getName()); //here we might want to get the bots' class names
		}
		return participants;
	}
	
	public void setParticipants(List<String> botsParticipating) {
		
	}
	
	/* If we want to have a faster tournament, we could randomize turn order 
	 * instead of doing all possible permutations. 
	 * 
	 * We would still have to do all possible combinations to be fair. 
	 * (Alternatively, we could put in dummy bots to make the number of 
	 * players in the tournament equal to a power of n, where n is the number of 
	 * bots in a single game. But that seems too drastic, don't you think?)
	 */
	public void setAllPermutations(boolean choice) {
		
	}
	
	/* These two settings could be left as arguments in the call to 
	 * runTournament, of course.
	 */
	public void setNumPlayersPerGame(int numPerGame) {
		
	}
	
	public void setNumGameRepeatsPerTournament(int numPerTournament) {
		
	}

	public void resetPlayerStats() {
		tournament.resetPlayerStats();		
	}

}
