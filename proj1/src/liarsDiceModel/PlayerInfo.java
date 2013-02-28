package liarsDiceModel;


/**
 * Information about a player which a player knows throughout the game.  (May be used to make decisions on their turns.)
 */
public class PlayerInfo {
	private int numDice;
	private int id;
	
	/**
	 * Constructor.
	 * @param numDice The number of dice this player still has.
	 * @param playerID The player's unique ID.
	 */
	public PlayerInfo(int numDice, int playerID) {
		this.numDice = numDice;
		this.id = playerID;
	}
	
	/**
	 * "Copy" constructor.
	 * @param p The player about whom we are creating this PlayerInfo object.
	 */
	public PlayerInfo(LiarsDicePlayer p){
		numDice = p.getDice().size();
		id = p.getID();
	}

	/**
	 * @return The number of dice this player still has.
	 */
	public int getNumDice() {
		return numDice;
	}

	/**
	 * @return The player's unique ID.
	 */
	public int getID() {
		return id;
	}
}
