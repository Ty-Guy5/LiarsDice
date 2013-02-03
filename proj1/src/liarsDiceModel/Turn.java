package liarsDiceModel;
//done

public class Turn {
	private int playerID;
	private Decision decision;
	
	public Turn(int playerID, Decision decision)
	{
		this.playerID = playerID;
		this.decision = decision;
	}

	public int getPlayerID() {
		return playerID;
	}

	public Decision getDecision() {
		return decision;
	}
}
