package programmerTournamentModel;
import java.util.List;
//done

public interface Game {
	//Tyler says: this interface can't know about the Liar's Dice Bot class, 
	// 			  so I think it will have to return a String for the winning player.
	public Bot runGame(List<Bot> botsPlaying);
}
