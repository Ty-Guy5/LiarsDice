package liarsDiceModel;

public abstract class Player {
	private int id;
	private Statistics stats;

	Player(int id){
		this.id = id;
		stats = new Statistics();
	}

	public int getID() {
		return id;
	}
	
	public abstract String getName();
	
	public Statistics getStatistics() {
		return stats;
	}

	public abstract String getBotName();
}
