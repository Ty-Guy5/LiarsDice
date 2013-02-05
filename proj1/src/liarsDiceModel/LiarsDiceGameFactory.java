package liarsDiceModel;
import java.util.ArrayList;
import java.util.List;

import programmerTournamentModel.Bot;
import programmerTournamentModel.Game;
import programmerTournamentModel.GameFactory;

public class LiarsDiceGameFactory implements GameFactory {
	private String name = "Liar's Dice";
	public LiarsDiceGameFactory(){
		
	}
	
	public Game getGameInstance(List<Player> players) {
		ArrayList<LiarsDicePlayer> liarsDicePlayers = new ArrayList<LiarsDicePlayer>();
		for(Player p : players){
			liarsDicePlayers.add((LiarsDicePlayer)p);
		}
		resetDice(liarsDicePlayers);
		return new LiarsDiceGame(liarsDicePlayers);
	}

	private void resetDice(ArrayList<LiarsDicePlayer> liarsDicePlayers) {
		for(LiarsDicePlayer p : liarsDicePlayers){
			p.resetDice();
		}
	}

	public List<Player> getPlayers() {
		// TODO Reflection goes here - for now can hardcode here
		ArrayList<Bot> bots = new ArrayList<Bot>();
		bots.add(new TestBot1());
		bots.add(new TestBot2());
		bots.add(new TestBot3());
		
		//wrap each bot in a Player object
		int playerNumber = 1;
		ArrayList<Player> players = new ArrayList<Player>();
		for (Bot bot : bots)
		{
			players.add(new LiarsDicePlayer((LiarsDiceBot) bot, playerNumber++));
		}
		
		return players;
	}

	public String getGameName() {
		return name;
	}

}
