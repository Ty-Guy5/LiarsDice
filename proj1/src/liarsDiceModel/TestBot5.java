package liarsDiceModel;

public class TestBot5 extends LiarsDiceBot {

	public String getName() {
		return "Infinite Looper";
	}

	public Decision getDecision(GameInfo currentGameInfo) {
		while (true) ;
	}

}
