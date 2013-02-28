package liarsDiceModel;
import java.util.concurrent.Semaphore;


import programmerTournamentModel.GameHistory;


/**
 * This class will interface with the player view to get the human player's input.
 */
public class HumanController extends LiarsDiceBot {
	
	ViewCommunication viewCommunication;

    private static HumanController instance = null;
	
	private HumanController() {
		viewCommunication = new ViewCommunication();
	}

    public static HumanController getInstance() {
    	if (instance == null) {
    		synchronized (HumanController .class){
    			if (instance == null) {
    				instance = new HumanController ();
    			}
    		}
    	}
    	return instance;
    }

	/**
	 * @return The name of the human player.
	 */
	public String getName() {
		return "HumanPlayer";
	}

	/**
	 * @param currentGameInfo The current state of the game.
	 * @return The human's decision.
	 */
	public Decision getDecision(GameInfo currentGameInfo) {
		Decision userDecision = null;
		
		//update view
		//request input from view to make decision
		
		return userDecision;
	}

	/**
	 * @param gameInfo The current state of the game.
	 */
	public void reportGameResults(GameHistory gameHistory) {
		//update view
		//wait for user choosing to continue
	}
	
	public class ViewCommunication
	{
		Semaphore s;
		LDView view;
		Decision currentDecision;
		
		public void registerView(LDView view) {
			this.view = view;
		}
		
		public void sendViewDecisionRequest(GameHistory gameHistory) {
			currentDecision = null;
			view.decisionRequest();
		}
		
		public void reportGameResults(GameHistory gameHistory) {
			view.reportGameResults();
		}
		
		public void setDecision(Decision d) {
			currentDecision = d;
		}
	}

}
