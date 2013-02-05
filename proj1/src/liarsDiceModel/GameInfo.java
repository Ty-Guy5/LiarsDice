package liarsDiceModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import programmerTournamentModel.GameHistory;
//done


public class GameInfo {
	private Bid currentBid;
	private GameHistory gameHistory;
	private List<Die> myDice;
	private List<PlayerInfo> playersInfo;
	
	public GameInfo(){
		init(null, new GameHistory(), new ArrayList<Die>(), new ArrayList<PlayerInfo>());
	}
	
	public GameInfo(Bid currentBid, GameHistory gameHistory, List<Die> myDice, List<PlayerInfo> players) {
		init(currentBid, gameHistory, myDice, players);
	}
	
	public GameInfo(GameInfo gi){
		init(gi.getBid(), gi.getGameHistory(), gi.getMyDice(), gi.getPlayersInfo());
	}

	//copies deeply
	public void init(Bid currentBid, GameHistory gameHistory, List<Die> myDice, List<PlayerInfo> playersInfo) {
		this.currentBid = currentBid;
		this.gameHistory = new GameHistory(gameHistory);
		this.myDice = Collections.unmodifiableList(myDice);
		this.playersInfo = Collections.unmodifiableList(playersInfo);
	}

	public Bid getBid(){
		return currentBid;
	}

	public Bid getCurrentBid() {
		return currentBid;
	}

	public GameHistory getGameHistory() {
		return gameHistory;
	}

	public List<Die> getMyDice() {
		return myDice;
	}
	
	public List<PlayerInfo> getPlayersInfo() {
		return playersInfo;
	}
	
	//returns 0 if no winner
	public int getWinnerID()
	{
		int winner = 0;
		
		for(PlayerInfo p : playersInfo){
			if(p.getNumDice() > 0){
				if (winner != 0)
					return 0;
				winner = p.getID();
			}
		}
		
		return winner;
	}
}
