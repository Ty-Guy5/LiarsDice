package liarsDiceModel;

public class TestBot0 extends LiarsDiceBot {

	public String getName() {
		return "Problematic";
	}

	public Decision getDecision(GameInfo currentGameInfo) {
		if(Math.random() > .7){
			int[] array = new int[1];
			array[2] = 3; //throw Exception
		}
		return new Challenge();
	}

}