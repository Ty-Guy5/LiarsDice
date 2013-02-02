package liarsDiceModel;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private static final int INITIAL_NUM_DICE = 5;
	
	LiarsDiceBot bot;
	private List<Die> dice;
	private String name;

	public Player(LiarsDiceBot bot, String name) {
		this.bot = bot;
		this.name = name;
		dice = new ArrayList<Die>();
		int numDice = INITIAL_NUM_DICE;
		for (int i=0; i<numDice; i++) {
			dice.add(new Die());	
		}
	}
	
	public void removeDie() {
		if (dice.size() > 0)
			dice.remove(0);
	}
	
	public void rerollDice() {
		int numDice = dice.size();
		dice = new ArrayList<Die>();
		for (int i=0; i<numDice; i++) {
			dice.add(new Die());	
		}
	}
	
	public Decision getDecision(GameInfo gameInfo) {
		return bot.getDecision(gameInfo);
	}
	
	public String getName() {
		return name;
	}

}
