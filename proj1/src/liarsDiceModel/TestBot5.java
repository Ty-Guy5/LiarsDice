<<<<<<< HEAD
package liarsDiceModel;

public class TestBot5 extends LiarsDiceBot {

	public String getName() {
		return "Infinite Looper";
	}

	public Decision getDecision(GameInfo currentGameInfo) {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
		}
		return new Challenge();
	}

}
=======
package liarsDiceModel;

/**
 * Very simple test bot which randomly throws exceptions or Challenges.
 */
public class TestBot5 extends LiarsDiceBot {

	public String getName() {
		return "Takes Forever";
	}

	public Decision getDecision(GameInfo currentGameInfo) {
		while(true);
	}

}
>>>>>>> branch 'master' of https://github.com/Ty-Guy5/LiarsDice.git
