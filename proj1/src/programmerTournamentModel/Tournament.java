package programmerTournamentModel;
import java.util.ArrayList;
import java.util.List;


public class Tournament {
	private GameFactory gameFactory;
	
	public Tournament(GameFactory gf)
	{
		gameFactory = gf;
	}
	
	public void runTournament()
	{
		Game game = gameFactory.getGameInstance();
		
		List<Bot> allBots = gameFactory.getBots(); //TODO this should be permanently stored in a map
		
		List<Bot> botsPlaying = new ArrayList<Bot>();
		botsPlaying.add(allBots.get(0));
		game.runGame(botsPlaying);
		//TODO finish
	}
}
