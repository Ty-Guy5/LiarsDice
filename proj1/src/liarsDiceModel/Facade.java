package liarsDiceModel;

import java.util.ArrayList;
import java.util.List;

import programmerTournamentModel.GameFactory;
import programmerTournamentModel.Tournament;

public class Facade {
	private Tournament tournament;
	
	public Facade()
	{
		//default to Liar's Dice as the game choice
        chooseGame(new LiarsDiceGameFactory());
	}

	public List<Player> getPlayers() {
		return tournament.getPlayers();
	}

	public List<Player> getParticipants() {
		return tournament.getParticipatingPlayers();
	}

	public void chooseGame(GameFactory factory) {
		tournament = new Tournament(factory);
	}
	
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

	public void runTournament(int botsPerGame, int gameRepeats) {
		tournament.runTournament(botsPerGame, gameRepeats);
	}
	
	public void setTimeout(long microsecBeforeTimeout) {
		tournament.setTimeout(microsecBeforeTimeout);
	}

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

}
