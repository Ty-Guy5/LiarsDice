package programmerTournamentModel;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import liarsDiceModel.Player;


public class Tournament {
	private GameFactory gameFactory;
	List<Player> allPlayers;
	
	public Tournament(GameFactory gf)
	{
		gameFactory = gf;
		allPlayers = gameFactory.getPlayers(); //TODO this should be permanently stored in a map
		
	}
	
	public void runTournament(int botsPerGame)
	{
		if(botsPerGame > allPlayers.size()){
			botsPerGame = allPlayers.size();
		}
		/*
		Tournament
		    bring in all bots (implementations) (1) 
		        read in from file and use reflection (2) 
		        hard-code some test bots (1) 
		    for(as many times as you want the tourney to run) 
		        either: 
		            pit all bots against each other in one Game 
		            play the game 
		        or: 
		            arrange bots into different Games (in tourney format) 
		            play out the tournament 
		        update statistics (wins/losses/etc.)
		    display results 
		 */
		
		//TODO replace below with tournament logic
		runAllPermutations(botsPerGame, new LinkedList<Player>());
		
	}
	
	private void runAllPermutations(int botsPerGame, LinkedList<Player> playersSoFar){
		for(int i = 0; i < allPlayers.size(); i++){
			Player current = allPlayers.get(i);
			if(!playersSoFar.contains(current)){
				playersSoFar.add(current);
				if(playersSoFar.size() == botsPerGame){
					Game game = gameFactory.getGameInstance(playersSoFar);
					Player winner = game.runGame();
					//update stats
					System.out.println("winner: " + winner.getClass().getSimpleName() + ", ID: " + winner.getID());
				}
				else{
					runAllPermutations(botsPerGame, playersSoFar);
				}
				playersSoFar.removeLast();
			}
		}		
	}
}
