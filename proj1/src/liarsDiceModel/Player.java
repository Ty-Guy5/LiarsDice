package liarsDiceModel;

public abstract class Player {
	private int id;
	
	Player(int id){
		this.id = id;
	}

	public int getID() {
		return id;
	}

	public abstract String getBotName();
}
