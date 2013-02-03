package liarsDiceModel;

import java.util.ArrayList;
import java.util.List;

import programmerTournamentModel.Bot;
import programmerTournamentModel.Game;
import programmerTournamentModel.GameHistory;

public class LiarsDiceGame implements Game {
	GameHistory history;
	int numPlayers;
	List<LiarsDicePlayer> players;
	
	public LiarsDiceGame(List<LiarsDicePlayer> players){
		numPlayers = players.size();
		this.players = players;
	}
	
	public Player runGame() {
		
		//TODO make sure that tournament decides play order before passing the list here (pass in bots in play order)
		int turnIndex = 0;
		while(numPlayers > 1){
			boolean noChallenges = true;
			Bid currentBid = null;
			while(noChallenges){
				ArrayList<PlayerInfo> allPlayersInfo = new ArrayList<PlayerInfo>();
				for(Player p : players){
					allPlayersInfo.add(new PlayerInfo((LiarsDicePlayer)p));
				}
				GameInfo gi = new GameInfo(currentBid, new GameHistory(history), players.get(turnIndex).getDice(), allPlayersInfo);
				Decision decision = null;
				try{
					decision = players.get(turnIndex).getDecision(gi);
				}catch(Exception e){ //checking against exceptions thrown by bot
					e.printStackTrace();
					history.endRound(Result.EXCEPTION);
					//maybe log later
					loseDie(turnIndex);
					break;
				}
				if(!isValidDecision(decision, currentBid)){
					history.endRound(Result.INVALIDDECISION);
					loseDie(turnIndex);
					break;
				}
				if(decision instanceof Challenge){
					history.endRound(Result.CHALLENGE);
					/*
					determine who loses a die
						if(current bid == valid)
							challenger loses a die
							if(0 dice left)
								somehow remove from turn order
						else
							challenged loses a die
							if(0 dice left)index
								somehow remove from turn order
					 */
					if(numberOfDiceWithValue(currentBid.getDieNumber()) >= currentBid.getFrequency()){
						loseDie(turnIndex);
					}
					else{
						loseDie(previousTurnIndex(turnIndex));
					}
					break;
				}
				else{
					Bid bid = (Bid)decision;
					
				}
			}
			turnIndex = nextTurnIndex(turnIndex);
		}
		
		/*
		Game/Liars Dice (# of bots)
			initialize dice, etc.
			while(>1 bot has dice){ -- (rounds)
				while(no challenges){ -- (turns)
					initialize GameInfo & PlayerInfo - both deep copies, don't include anything original!
					ask next Player to take a turn – get Decision
					if invalid bid{
						they automatically lose the round 
						report what happened
					}
					if throw exception{
						automatically lose the round
						report what happened
					}
					*****Think about this later*****
					How to handle infinite loop in their function?{
						automatically lose the round
						report what happened
					}
					*****Think about this later*****
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
	
	private int nextTurnIndex(int turnIndex) {
		
		
		return 0;
	}

	private int previousTurnIndex(int turnIndex) {
		
		
		return 0;
	}

	private int numberOfDiceWithValue(int dieNumber) {
		int count = 0;
		for(LiarsDicePlayer p : players){
			for(Die d : p.getDice()){
				if(d.getValue() == dieNumber || d.getValue() == Die.WILD){
					count++;
				}
			}
		}
		return count;
	}

	private void loseDie(int turnIndex) {
		players.get(turnIndex).removeDie();
		if(players.get(turnIndex).getDice().size() <= 0){
			numPlayers--;
		}
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
