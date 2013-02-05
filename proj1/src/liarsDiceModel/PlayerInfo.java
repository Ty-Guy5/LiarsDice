package liarsDiceModel;
//done
public class PlayerInfo {
	private int numDice;
	private int id;
	
	public PlayerInfo(int numDice, int playerID) {
		this.numDice = numDice;
		this.id = playerID;
	}
	
	public PlayerInfo(LiarsDicePlayer p){
		numDice = p.getDice().size();
		id = p.getID();
	}

	public int getNumDice() {
		return numDice;
	}

	public int getID() {
		return id;
	}
}
