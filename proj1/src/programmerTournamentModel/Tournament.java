package programmerTournamentModel;
import java.util.ArrayList;
import java.util.List;

import liarsDiceModel.Player;


public class Tournament {
	private GameFactory gameFactory;
	
	public Tournament(GameFactory gf)
	{
		gameFactory = gf;
	}
	
	public void runTournament(int botsPerGame, int repeatGames)
	{
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
		List<Player> allPlayers = gameFactory.getPlayers(); //TODO this should be permanently stored in a map
		
		//TODO replace below with tournament logic
		for(int i = 0; i < allPlayers.size(); i++){
			//somehow need to run all permutations of combinations of bots in a game...
			Game game = gameFactory.getGameInstance(allPlayers);
			
			game.runGame();
			//TODO finish
		}
	}
}
