package liarsDiceModel;


public class Turn {
	private String playerName;
	private Decision decision;
	
	public Turn(String playerName, Decision decision)
	{
		this.playerName = playerName;
		this.decision = decision;
	}

	public String getPlayerName() {
		return playerName;
	}

	public Decision getDecision() {
		return decision;
	}
}
