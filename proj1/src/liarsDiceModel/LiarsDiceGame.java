package liarsDiceModel;

import java.util.ArrayList;
import java.util.List;

import programmerTournamentModel.Bot;
import programmerTournamentModel.Game;
import programmerTournamentModel.GameHistory;

public class LiarsDiceGame implements Game {
	GameHistory history;
	List<Player> players;
	
	
	public LiarsDiceGame(){
		players = new ArrayList<Player>();
	}
	
	public Bot runGame(List<Bot> botsPlaying) {
		//wrap each bot in a Player object
		int playerNumber = 1;
		for (Bot bot : botsPlaying)
		{
			//TODO check this for correctness
			players.add(new Player((LiarsDiceBot) bot, "" + playerNumber++));
		}
		//TODO make sure that tournament decides play order before passing the list here
		
		//TODO finish this stub
		return botsPlaying.get(0);
		
		/*
		Game/Liars Dice (# of bots)
			initialize dice, etc.
			while(>1 bot has dice){ -- (rounds)
				while(no challenges){ -- (turns)
					initialize GameInfo & PlayerInfo - both deep copies, don't include anything original!
					ask next Player to take a turn – get Decision
					if invalid bid{
						1. choose a valid bid for them (or challenge) (not good option)
						2. ask them again – (infinite loop possibility)
						3. they automatically lose the round 
						report what happened
					}
					if throw exception{
						automatically lose the round
						report what happened
					}
					How to handle infinite loop in their function?{
						automatically lose the round
						report what happened
					}
					if(decision == challenge)
						break;
					if(decision == bid)
						update history, current bid, update whose turn it is (skip if no dice)
				}
				determine who loses a die
					if(current bid == valid)
						challenger loses a die
						if(0 dice left)
							somehow remove from turn order
					else
						challenged loses a die
						if(0 dice left)
							somehow remove from turn order	
			}
			(whoever has dice left wins game)
			inform Bots who won: give them History
			report winner to Tournament class (return value?)
		 */
		
	}
	
	public static boolean isValidDecision(Decision decision, Bid currentBid){
		if(decision == null){
			return false;
		}
		if(decision instanceof Challenge){
			if(currentBid == null){
				return false; //can't challenge first turn of the round
			}
			return true; //otherwise challenge is always valid
		}
		else{
			Bid bid = (Bid)decision;
			if(bid.getDieNumber() < 2 || bid.getDieNumber() > 6){
				return false; //invalid dieNumber
			}
			if(currentBid == null){ //first bid of round
				if(bid.getFrequency() < 1){
					return false; //can't bid 0 or less
				}
				return true;
			}
			else{ //not first bid of round
				if(bid.getFrequency() < currentBid.getFrequency()){
					return false; //frequency must be >= current frequency
				}
				else if(bid.getFrequency() > currentBid.getFrequency()){
					return true; //an increased frequency is always valid (assuming a valid dieNumber - above)
				}
				else{ //frequency == current frequency
					if(bid.getDieNumber() <= currentBid.getDieNumber()){
						return false; //must increase the dieNumber if not increasing frequency
					}
					return true;
				}
			}
		}
	}
	
	public static boolean unitTest(){
		Bid good1 = new Bid(0, 2);
		assert(!isValidDecision(good1,null));
		//TODO: more...
		return true;
	}
}
