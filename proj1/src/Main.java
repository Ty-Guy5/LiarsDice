import gui.GUI;
import programmerTournamentModel.GameHistory;



public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		GUI mainGUI = new GUI();

		GameHistory gh = new GameHistory();
		GameHistory gh2 = new GameHistory(gh);
	}

}
