package programmerTournamentModel;
import java.util.List;
//done

import liarsDiceModel.Player;

public interface GameFactory {
	
	public Game getGameInstance(List<Player> players);
	
	public String getGameName();
	
	public List<Player> getPlayers();
	
}
