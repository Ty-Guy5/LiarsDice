package model.liarsDice;

import java.util.concurrent.Semaphore;

import model.liarsDice.gameInfo.GameHistory;
import model.liarsDice.gameInfo.GameInfo;
import model.liarsDice.gameLogic.Decision;
import model.liarsDice.gameLogic.LiarsDiceBot;


/**
 * This class will interface with the player view to get the human player's input.
 */
public class HumanController extends LiarsDiceBot {
	
	ViewCommunication viewCommunication;

    public ViewCommunication getViewCommunication() {
		return viewCommunication;
	}

	//private static HumanController instance = null;
	
	public HumanController() {
		viewCommunication = new ViewCommunication();
	}

    /*public static HumanController getInstance() {
    	if (instance == null) {
    		synchronized (HumanController.class){
    			if (instance == null) {
    				instance = new HumanController ();
    			}
    		}
    	}
    	return instance;
    }*/

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
			//Do nothing. Game is over and this thread will die. 
			//Also, this block may never be reached.
			//TODO log if it is reached?
		} 
		
		return userDecision;
	}

	/**
	 * @param gameInfo The current state of the game.
	 */
	public void reportGameResults(GameInfo gameInfo) {
		viewCommunication.reportGameResults(gameInfo);
	}
	
	public class ViewCommunication
	{
		private Semaphore s;
		private LiarsDiceView view;
		private Decision currentDecision;
		
		public ViewCommunication() {
			s = new Semaphore(0);
		}
		
		/*********** HumanController-end methods ***********/
		
		public void sendDecisionRequest(GameInfo gameInfo) {
			view.decisionRequest(gameInfo);
		}
		
		public void reportGameResults(GameInfo gameInfo) {
			view.reportGameResults(gameInfo);
		}
		
		public Decision getDecision() throws InterruptedException {
			s.acquire();
			Decision rVal = currentDecision;
			return rVal;
		}
		
		/*********** View-end methods ***********/
		
		public void registerView(LiarsDiceView view) {
			this.view = view;
		}
		
		public void setDecision(Decision d) {
			currentDecision = d;
			s.release();
		}
	}

}
