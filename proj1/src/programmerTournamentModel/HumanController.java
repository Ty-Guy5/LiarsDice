package programmerTournamentModel;
import liarsDiceModel.Challenge;
import liarsDiceModel.Decision;
import liarsDiceModel.GameInfo;
import liarsDiceModel.LiarsDiceBot;

/**
 * This class will interface with the player view to get the human player's input.
 */
public class HumanController extends LiarsDiceBot {

	/**
	 * @return The name of the human player.
	 */
	public String getName() {
		return "HumanPlayer";
	}

	/**
	 * @param currentGameInfo The current state of the game. (Probably don't need this - human can see it.)
	 * @return The human's decision.
	 */
	public Decision getDecision(GameInfo currentGameInfo) {
		//TODO stubbed out
		//will need to talk up at the View level to get the human input
		return new Challenge();
	}

}
