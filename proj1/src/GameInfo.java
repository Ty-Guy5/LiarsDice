import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GameInfo {
	private Bid currentBid;
	private GameHistory gameHistory;
	private List<Die> myDice;
	private List<PlayerInfo> players;
	
	public GameInfo(){
		init(null, new GameHistory(), new ArrayList<Die>(), new ArrayList<PlayerInfo>());
	}
	
	public GameInfo(Bid currentBid, GameHistory gameHistory, List<Die> myDice, List<PlayerInfo> players) {
		init(currentBid, gameHistory, myDice, players);
	}
	
	public GameInfo(GameInfo gi){
		init(gi.getBid(), gi.getGameHistory(), gi.getMyDice(), gi.getPlayers());
	}

	//copies deeply
	public void init(Bid currentBid, GameHistory gameHistory, List<Die> myDice, List<PlayerInfo> players) {
		this.currentBid = currentBid;
		this.gameHistory = new GameHistory(gameHistory);
		this.myDice = Collections.unmodifiableList(myDice);
		this.players = Collections.unmodifiableList(players);
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
	
	public List<PlayerInfo> getPlayers() {
		return players;
	}
}
