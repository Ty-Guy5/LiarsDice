package liarsDiceModel;

import java.util.ArrayList;
import java.util.List;
//done
public class LiarsDicePlayer extends Player{
	private static final int INITIAL_NUM_DICE = 5;
	
	LiarsDiceBot bot;
	private List<Die> dice;

	public LiarsDicePlayer(LiarsDiceBot bot, int id) {
		super(id);
		this.bot = bot;
		dice = new ArrayList<Die>();
		int numDice = INITIAL_NUM_DICE;
		for (int i=0; i<numDice; i++) {
			dice.add(new Die());	
		}
	}

	public void removeDie() {
		//intentionally leaving open for exceptions
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

	public List<Die> getDice() {
		return dice;
	}

}
