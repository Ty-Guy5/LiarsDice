package programmerTournamentModel;
import java.util.List;
//done

public interface GameFactory {
	
	public Game getGameInstance();
	
	public String getGameName();
	
	public List<Bot> getBots();
	
}
