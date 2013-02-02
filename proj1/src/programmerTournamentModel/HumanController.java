package programmerTournamentModel;
import liarsDiceModel.Challenge;
import liarsDiceModel.Decision;
import liarsDiceModel.GameInfo;
import liarsDiceModel.LiarsDiceBot;


public class HumanController extends LiarsDiceBot {

	public String getName() {
		return "HumanPlayer";
	}

	public Decision getDecision(GameInfo currentGameInfo) {
		//TODO stubbed out
		//will need to talk up at the View level to get the human input
		return new Challenge();
	}

}
