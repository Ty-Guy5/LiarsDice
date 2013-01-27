
public class HumanInterface extends Bot {

	@Override
	public String getName() {
		return "HumanPlayer";
	}

	@Override
	public Decision getDecision(GameInfo currentGameInfo) {
		//TODO stubbed out
		return new Challenge();
	}

}
