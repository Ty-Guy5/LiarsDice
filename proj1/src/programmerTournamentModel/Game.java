package programmerTournamentModel;
import java.util.List;

import liarsDiceModel.Player;
//done

public interface Game {
	public Player runGame();
	public void setTimeout(double secBeforeTimeout);
}
