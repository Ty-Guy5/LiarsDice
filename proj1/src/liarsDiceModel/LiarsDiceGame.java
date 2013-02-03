package liarsDiceModel;

import java.util.ArrayList;
import java.util.List;

import programmerTournamentModel.Bot;
import programmerTournamentModel.Game;
import programmerTournamentModel.GameHistory;

public class LiarsDiceGame implements Game {
	GameHistory history;
	int numPlayers;
	int turnIndex;
	List<LiarsDicePlayer> players;
	
	public LiarsDiceGame(List<LiarsDicePlayer> players){
		numPlayers = players.size();
		this.players = players;
		turnIndex = 0;
	}
	
	public Player runGame() {
		//TODO make sure that tournament decides play order before passing the list here (pass in bots in play order)
		//turnIndex = 0;
		while(numPlayers > 1){
			boolean noChallenges = true;
			Bid currentBid = null;
			history.addRound(new Round());
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
					if(numberOfDiceWithValue(currentBid.getDieNumber()) >= currentBid.getFrequency()){
						loseDie(turnIndex);
					}
					else{
						loseDie(previousTurnIndex(turnIndex));
					}
					break;
				}
				else{
					history.addTurn(new Turn(players.get(turnIndex).getID(), decision));
					Bid bid = (Bid)decision;
					currentBid = bid;
					turnIndex = nextTurnIndex(turnIndex);
				}
			}
		}
		LiarsDicePlayer winner = null;
		for(LiarsDicePlayer p : players){
			p.reportGameResults(new GameHistory(history));
			if(p.getDice().size() > 0){
				if(winner != null){
					System.out.println("error: multiple winners???");
				}
				winner = p;
			}
		}
		if(winner == null){
			System.out.println("error: runGame didn't have anyone with dice left!");
		}
		return winner;		
	}
	
	private int nextTurnIndex(int turnIndex) {
		int temp = turnIndex;
		do{
			temp++;
			if(temp >= players.size()){
				temp = 0;
			}
			if(temp == turnIndex){
				System.out.println("nextTurnIndex error: turn == turnIndex");
				break;
			}
		}while(players.get(temp).getDice().size() <= 0);
		return temp;
	}

	private int previousTurnIndex(int turnIndex) {
		int temp = turnIndex;
		do{
			temp--;
			if(temp < 0){
				temp = players.size() - 1;
			}
			if(temp == turnIndex){
				System.out.println("previousTurnIndex error: turn == turnIndex");
				break;
			}
		}while(players.get(temp).getDice().size() <= 0);
		return temp;
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

	private void loseDie(int loseIndex) {
		players.get(loseIndex).removeDie();
		if(players.get(loseIndex).getDice().size() <= 0){
			numPlayers--;
			this.turnIndex = nextTurnIndex(loseIndex);
		}
		else{
			this.turnIndex = loseIndex;
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
