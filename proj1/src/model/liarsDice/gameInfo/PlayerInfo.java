package model.liarsDice.gameInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.liarsDice.gameLogic.Die;
import model.liarsDice.gameLogic.LiarsDicePlayer;


/**
 * Information about a player which a player knows throughout the game.  
 * (May be used to make decisions on their turns.)
 */
public class PlayerInfo {
	private List<Die> dice;
	private int id;
	private boolean diceHidden;
	
	/**
	 * Constructor.
	 * @param dice The number of dice this player still has.
	 * @param playerID The player's unique ID.
	 */
	public PlayerInfo(List<Die> dice, int playerID, boolean diceHidden) {
		this.dice = dice;
		this.id = playerID;
		this.diceHidden = diceHidden;
	}
	
	/**
	 * "Copy" constructor for creating a PlayerInfo object from a player.
	 * @param p The player about whom we are creating this PlayerInfo object.
	 */
	public PlayerInfo(LiarsDicePlayer p, boolean diceHidden){
		dice = new ArrayList<Die>();
		for (Die die : p.getDice()) {
			dice.add(die);
		}
		id = p.getID();
		this.diceHidden = diceHidden;
	}

	/**
	 * @return The number of dice this player still has.
	 */
	public int getNumDice() {
		return dice.size();
	}
	
	/**
	 * @return The player's dice, or null if the player's dice are hidden.
	 */
	public List<Die> getDice() {
		if (diceHidden)
			return null;
		else
			return dice;
	}

	/**
	 * @return The player's unique ID.
	 */
	public int getID() {
		return id;
	}
}
