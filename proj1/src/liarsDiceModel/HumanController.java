package liarsDiceModel;

import programmerTournamentModel.GameHistory;

public class HumanController extends LiarsDiceBot {
	
	String name; //should a user be able to change this?
	
	public HumanController() {
		name = "Human Player"; 
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Decision getDecision(GameInfo currentGameInfo) {
		Decision userDecision = null;
		
		//update view
		//request input from view to make decision
		
		return userDecision;
	}

	@Override
	public void reportGameResults(GameHistory gameHistory) {
		//update view
		//wait for user choosing to continue
	}
	
}
