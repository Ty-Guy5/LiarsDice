package programmerTournamentModel;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import liarsDiceModel.Player;

/**
 * This class runs the overall tournament.
 */
public class Tournament {
	private GameFactory gameFactory;
	private List<Player> allPlayers, participatingPlayers;
//	private int counter = 0;
	private long microsecBeforeTimeout;
	
	//TODO: Add in functionality to add/remove players - for the checkboxes.
	
	/**
	 * Constructor. (Pass in the GameFactory associated with the game you want to play.)
	 * @param gf The specific GameFactory to be used for the tournament.
	 */
	public Tournament(GameFactory gf)
	{
		gameFactory = gf;
		allPlayers = gameFactory.getPlayers();
		participatingPlayers = new LinkedList<Player>();
		for (Player p : allPlayers) {
			participatingPlayers.add(p);
		}
	}

	/**
	 * @return List of all player implementations.
	 */
	public List<Player> getPlayers() {
		return allPlayers;
	}

	/**
	 * @return List of all player implementations competing in the tournament.
	 */
	public List<Player> getParticipatingPlayers() {
		return participatingPlayers;
	}
	
	/**
	 * Adds a player to the collection of players to be included in the tournament.
	 * @param index The index (with respect to all players) of the player to be added to the collection of participating players.
	 */
	public void addPlayer(int index){
		Player p = allPlayers.get(index);
		if(!participatingPlayers.contains(p)){
			participatingPlayers.add(p);
		}
	}
	
	/**
	 * Removes a player from the collection of players to be included in the tournament.
	 * If that player is not currently participating, this method does nothing.
	 * @param index The index (with respect to all players) of the player to be removed from the collection of participating players.
	 */
	public void removePlayer(int index){
		Player p = allPlayers.get(index);
		if(participatingPlayers.contains(p)){
			participatingPlayers.remove(p);
		}
	}
	
	/**
	 * Runs the tournament with all the players.  Runs every possible permutation of the 
	 * players (repeated as many times as requested).  
	 * For example, (players = A,B,C -- botsPerGame = 2 -- gameRepeats = 2) would yield the following games:
	 * AB, AB, AC, AC, BA, BA, BC, BC, CA, CA, CB, CB
	 * This guarantees that each player will play each other player at least once, and that each each player
	 * will be guaranteed to start at least one game against each other player.
	 * 
	 * A tournament can only be run if there are at least two players.
	 * @param botsPerGame The number of players to play in each individual game. (2 <= botsPerGame <= total # players)
	 * @param gameRepeats The number of times to repeat each permutation of the tournament.
	 */
	public void runTournament(int botsPerGame, int gameRepeats)
	{
		//reset stats for all players
		for(Player p : allPlayers){
			p.resetStatistics();
		}
		
		if(participatingPlayers.size() < 2 || gameRepeats < 1){
			return;
		}
		if(botsPerGame > participatingPlayers.size()){
			botsPerGame = participatingPlayers.size();
		}
		else if(botsPerGame < 2){
			botsPerGame = 2;
		}
		System.out.println("before tourney");
		for(Player p : participatingPlayers){
			System.out.println(p.getID() + ": " + p.getStatistics());
		}
		
		long start = System.currentTimeMillis();
		runAllPermutations(botsPerGame, gameRepeats, new LinkedList<Player>());
		long end = System.currentTimeMillis();
		System.out.println("tournament time: " + (end - start) + "ms");
		
		System.out.println("\nTournament Statistics:\n");
		for(Player p : allPlayers){
			System.out.println("Player " + p.getID() + " \"" + p.getName() + "\":\t" + p.getStatistics());
		}
		
	}
	
	/**
	 * Recursively runs a game for every permutation, as constrained by botsPerGame and gameRepeats.
	 * Also updates statistics at end of each game.
	 * @param botsPerGame The number of players to play in each individual game.
	 * @param gameRepeats The number of times to repeat each permutation of the tournament.
	 * @param playersSoFar The players who are already included in the current game being constructed.
	 */
	private void runAllPermutations(int botsPerGame, int gameRepeats, LinkedList<Player> playersSoFar){
//		System.out.println("in run all permutations " + counter++);
		for(int i = 0; i < participatingPlayers.size(); i++){
			Player current = participatingPlayers.get(i);
			if(!playersSoFar.contains(current)){
				playersSoFar.add(current);
				if(playersSoFar.size() == botsPerGame){

					for(int j = 0; j < gameRepeats; j++){
						Game game = gameFactory.getGameInstance(playersSoFar);
						game.setTimeout(microsecBeforeTimeout);
//						long start = System.currentTimeMillis();
						Player winner = game.runGame();
//						long end = System.currentTimeMillis();
						//update stats
						for(Player p : playersSoFar){
							if(p == winner){
								p.getStatistics().increaseWins();
							}
							else{
								p.getStatistics().increaseLosses();
							}
						}
//						System.out.println("winner: " + winner.getClass().getSimpleName() + ", ID: " + winner.getID());
//						System.out.println("game time: " + (end - start));
					}
//TODO why is the below code necessary? Is it?
/*
					Game game = gameFactory.getGameInstance(playersSoFar);
					Player winner = game.runGame();
					//update stats
					System.out.println("winner: " + winner.getName() + ", ID: " + winner.getID());
*/
				}
				else{
					runAllPermutations(botsPerGame, gameRepeats, playersSoFar);
				}
				playersSoFar.removeLast();
			}
		}		
	}

	/**
	 * Sets the limit on turn times for each bot.  (If a player goes over that time, they lose the round/game.)
	 * @param secBeforeTimeout Number of seconds allowed for each player to take a turn. (Can be less than 1.)
	 */
	public void setTimeout(long microsecBeforeTimeout) {
		this.microsecBeforeTimeout = microsecBeforeTimeout;
	}
	
	/**
	 * Resets all of the statistics of all players to zero.
	 */
	public void resetPlayerStats() {
		for(Player p : allPlayers){
			p.resetStatistics();
		}
	}
}
