import liarsDiceModel.LiarsDiceGameFactory;
import gui.GUI;
import programmerTournamentModel.GameHistory;
import programmerTournamentModel.Tournament;



public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
//		GUI mainGUI = new GUI();
//
//		GameHistory gh = new GameHistory();
//		GameHistory gh2 = new GameHistory(gh);
		
		Tournament t = new Tournament(new LiarsDiceGameFactory());
		t.runTournament(2, 4);
	}

}
