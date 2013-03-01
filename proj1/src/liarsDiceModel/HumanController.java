package liarsDiceModel;
import java.util.concurrent.Semaphore;


import programmerTournamentModel.GameHistory;


/**
 * This class will interface with the player view to get the human player's input.
 */
public class HumanController extends LiarsDiceBot {
	
	ViewCommunication viewCommunication;

    public ViewCommunication getViewCommunication() {
		return viewCommunication;
	}

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
		
		viewCommunication.sendDecisionRequest(currentGameInfo);
		try {
			userDecision = viewCommunication.getDecision();
		} catch (InterruptedException e) {
			viewCommunication.reportInterruption();
		} 
		
		return userDecision;
	}

	/**
	 * @param gameInfo The current state of the game.
	 */
	public void reportGameResults(GameHistory gameHistory) {
		viewCommunication.reportGameResults(gameHistory);
	}
	
	public class ViewCommunication
	{
		private Semaphore s;
		private LDView view;
		private Decision currentDecision;
		
		public ViewCommunication() {
			s = new Semaphore(1);
		}
		
		/*********** HumanController-end methods ***********/
		
		public void sendDecisionRequest(GameInfo gameInfo) {
			view.decisionRequest();
		}
		
		public void reportGameResults(GameHistory gameHistory) {
			view.reportGameResults();
		}
		
		public Decision getDecision() throws InterruptedException {
			s.acquire();
			Decision rVal = currentDecision;
			return rVal;
		}
		
		public void reportInterruption() {
			view.reportInterruption();
		}
		
		/*********** View-end methods ***********/
		
		public void registerView(LDView view) {
			this.view = view;
		}
		
		public void setDecision(Decision d) {
			currentDecision = d;
			s.release();
		}
	}

}
