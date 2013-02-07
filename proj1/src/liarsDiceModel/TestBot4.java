package liarsDiceModel;

import java.util.List;

public class TestBot4 extends LiarsDiceBot {

	@Override
	public String getName() {
		return "Smart Bot";
	}

	@Override
	public Decision getDecision(GameInfo currentGameInfo) {
		Bid currentBid = currentGameInfo.getCurrentBid();
		if(currentBid == null){
			return calculateFirstBid(currentGameInfo);
		}
		else{
			return makeDecision(currentGameInfo);
		}
	}

	private Decision makeDecision(GameInfo currentGameInfo) {
		int[] myDiceFrequencies = getMyDiceFrequencies(currentGameInfo);
		int totalDice = getTotalDice(currentGameInfo);
		int othersDice = totalDice - currentGameInfo.getMyDice().size();
		Bid currentBid = currentGameInfo.getCurrentBid();
		int onesGuess = othersDice/6 + myDiceFrequencies[0];
		int bidFrequencyGuess = myDiceFrequencies[currentBid.getDieNumber() - 1] + othersDice/6;
		if(currentBid.getFrequency() > (onesGuess + bidFrequencyGuess)){ //I don't think there are that many in the game
			return new Challenge();
		}
		else if(currentBid.getFrequency() < (onesGuess + bidFrequencyGuess)){
			return new Bid(currentBid.getFrequency() + 1, currentBid.getDieNumber());
		}
		else{ //currentBid is spot on with my guess
			int myDiceOfCurrentBid = myDiceFrequencies[currentBid.getDieNumber() - 1];
			for(int i = currentBid.getDieNumber(); i < myDiceFrequencies.length; i++){
				if(myDiceFrequencies[i] >= myDiceOfCurrentBid){
					return new Bid(currentBid.getFrequency(), i + 1);
				}
			}
			for(int i = 1; i < currentBid.getDieNumber(); i++){
				if(myDiceFrequencies[i] > myDiceOfCurrentBid){
					return new Bid(currentBid.getFrequency() + 1, i + 1);
				}
			}
			return new Challenge();
		}
	}

	private int getTotalDice(GameInfo currentGameInfo) {
		int totalDice = currentGameInfo.getMyDice().size();
		List<PlayerInfo> players = currentGameInfo.getPlayersInfo();
		for(PlayerInfo p : players){
			totalDice += p.getNumDice();
		}
		return totalDice;
	}

	private Decision calculateFirstBid(GameInfo currentGameInfo) {
		int[] myDiceFrequencies = getMyDiceFrequencies(currentGameInfo);
		int maxValue = -1, maxFrequency = -1;
		for(int i = 1; i < myDiceFrequencies.length; i++){
			if(myDiceFrequencies[i] >= maxFrequency){
				maxValue = i+1;
				maxFrequency = myDiceFrequencies[i];
			}
		}
		int bidFrequency = maxFrequency + myDiceFrequencies[0];
		return new Bid(bidFrequency, maxValue);
	}
	
	private int[] getMyDiceFrequencies(GameInfo currentGameInfo) {
		int[] myDiceFrequencies = new int[6];
		for(Die d : currentGameInfo.getMyDice()){
			myDiceFrequencies[d.getValue() - 1]++;
		}
		return myDiceFrequencies;
	}

}
