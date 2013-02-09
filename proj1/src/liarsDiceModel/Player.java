package liarsDiceModel;

/**
 * Abstract class which represents a generic Player - should be usable for any game implementation.
 * Keeps track of a unique ID as well as its tournament statistics.
 */
public abstract class Player {
	private int id;
	private Statistics stats;

	/**
	 * Constructor.
	 * @param id The unique ID this Player will use for the tournament.
	 */
	Player(int id){
		this.id = id;
		stats = new Statistics();
	}

	/**
	 * @return This player's unique ID.
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * @return The chosen name of the player.
	 */
	public abstract String getName();
	
	/**
	 * @return This player's current tournament statistics.
	 */
	public Statistics getStatistics() {
		return stats;
	}
}
