package liarsDiceModel;
//done
public class PlayerInfo {
	private int numDice;
	private int playerID;
	
	public PlayerInfo(int numDice, int playerID) {
		this.numDice = numDice;
		this.playerID = playerID;
	}
	
	public PlayerInfo(LiarsDicePlayer p){
		numDice = p.getDice().size();
		playerID = p.getID();
	}

	public int getNumDice() {
		return numDice;
	}

	public int getBotID() {
		return playerID;
	}
}
