
public class PlayerInfo {
	private int numDice;
	private String botName;
	
	public PlayerInfo(int numDice, String botName) {
		this.numDice = numDice;
		this.botName = botName;
	}

	public int getNumDice() {
		return numDice;
	}

	public String getBotName() {
		return botName;
	}
}
