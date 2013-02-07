package programmerTournamentModel;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import liarsDiceModel.Player;


public class Tournament {
	private GameFactory gameFactory;
	List<Player> allPlayers;
//	int counter = 0;
	
	public Tournament(GameFactory gf)
	{
		gameFactory = gf;
		allPlayers = gameFactory.getPlayers();
	}

	public List getPlayers() {
		return allPlayers;
	}
	
	public void runTournament(int botsPerGame, int gameRepeats)
	{
		if(botsPerGame > allPlayers.size()){
			botsPerGame = allPlayers.size();
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
	
	private void runAllPermutations(int botsPerGame, int gameRepeats, LinkedList<Player> playersSoFar){
//		System.out.println("in run all permutations " + counter++);
		for(int i = 0; i < allPlayers.size(); i++){
			Player current = allPlayers.get(i);
			if(!playersSoFar.contains(current)){
				playersSoFar.add(current);
				if(playersSoFar.size() == botsPerGame){

					for(int j = 0; j < gameRepeats; j++){
						Game game = gameFactory.getGameInstance(playersSoFar);
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

					Game game = gameFactory.getGameInstance(playersSoFar);
					Player winner = game.runGame();
					//update stats
					System.out.println("winner: " + winner.getBotName() + ", ID: " + winner.getID());

				}
				else{
					runAllPermutations(botsPerGame, gameRepeats, playersSoFar);
				}
				playersSoFar.removeLast();
			}
		}		
	}
}
