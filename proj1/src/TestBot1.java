
public class TestBot1 extends LiarsDiceBot {

	@Override
	public Decision getDecision(GameInfo currentGameInfo) {
		//TODO: extend bot decision-making process
		return new Challenge();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
