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
	
	public void runTournament()
	{
		List<Player> allPlayers = gameFactory.getPlayers(); //TODO this should be permanently stored in a map
		
		//TODO replace below with tournament logic
		Game game = gameFactory.getGameInstance(allPlayers);
		
		game.runGame();
		//TODO finish
	}
}
