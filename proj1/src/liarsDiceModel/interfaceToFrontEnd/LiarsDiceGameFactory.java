package liarsDiceModel.interfaceToFrontEnd;
import genericModel.Bot;
import genericModel.Game;
import genericModel.GameFactory;
import genericModel.Player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import liarsDiceModel.bots.SmartBot;
import liarsDiceModel.gameLogic.LiarsDiceBot;
import liarsDiceModel.gameLogic.LiarsDiceGame;
import liarsDiceModel.gameLogic.LiarsDicePlayer;


/**
 * This class is a factory which returns an instance of the LiarsDiceGame class (and the players associated with it.
 */
public class LiarsDiceGameFactory implements GameFactory {
	private String name = "Liar's Dice";
	
	/**
	 * Constructor. (Does nothing.)
	 */
	public LiarsDiceGameFactory(){
		
	}
	
	/**
	 * @param players A list of players who will be included in the current Game instance.
	 * @return An instance of LiarsDiceGame set up with the given players.
	 */
	public Game getGameInstance(List<Player> players) {
		ArrayList<LiarsDicePlayer> liarsDicePlayers = new ArrayList<LiarsDicePlayer>();
		for(Player p : players){
			liarsDicePlayers.add((LiarsDicePlayer)p);
		}
		resetDice(liarsDicePlayers);
		return new LiarsDiceGame(liarsDicePlayers);
	}

	/**
	 * Resets the dice of each player in the given list of LiarsDicePlayers
	 * @param liarsDicePlayers List of players which need their dice reset.
	 */
	private void resetDice(ArrayList<LiarsDicePlayer> liarsDicePlayers) {
		for(LiarsDicePlayer p : liarsDicePlayers){
			p.resetDice();
		}
	}

	/**
	 * Uses reflection to get a List of all the submitted bots in the "/bots" folder.
	 * @return List of Players made from bots contained in the "/bots" folder.
	 */
	public List<Player> getPlayers() {
		//hardcoded bots
		ArrayList<Bot> bots = new ArrayList<Bot>();
//		bots.add(new TestBot0());
//		bots.add(new TestBot1());
//		bots.add(new TestBot2());
//		bots.add(new TestBot3());
//		bots.add(new SmartBot());
//		bots.add(new TestBot5());
		
		//gather bots in file via reflection
		ArrayList<String> botNames = findBotsInFolder("/src/liarsDiceModel/bots");
		for (String botName : botNames)
		{
			try{
				bots.add((LiarsDiceBot)Class.forName("liarsDiceModel.bots." + botName).newInstance());
			}catch (Exception e) {e.printStackTrace();}
			//TODO assumes that bot constructor will be fast and error free
		}
		
		
		//wrap each bot in a Player object
		int playerNumber = 1;
		ArrayList<Player> players = new ArrayList<Player>();
		for (Bot bot : bots)
		{
			players.add(new LiarsDicePlayer((LiarsDiceBot) bot, playerNumber++));
		}
		
		return players;
	}

	private ArrayList<String> findBotsInFolder(String string) {
		ArrayList<String> botNames = new ArrayList<String>();
		
		File dir = new File(System.getProperty("user.dir") + string);
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept (File dir, String name) {
				return name.endsWith(".java");
			}
		};
		String[] javaFilenames = dir.list(filter);
		if (javaFilenames == null) {
			System.err.println("Either dir does not exist or is not a directory");
		} 
		else {
			for (int i=0; i< javaFilenames.length; i++) {
				String filename = javaFilenames[i];
				File javaFile = new File(dir, filename);
				try {
					BufferedReader in = new BufferedReader(new FileReader(javaFile));
					String line = "";
					line = in.readLine();
					while (line != null) {
						String[] wordsInLine = line.split(" ");
						if (wordsInLine.length >= 4 
								&& wordsInLine[0].equals("public")
								&& wordsInLine[1].equals("class")
								&& wordsInLine[3].equals("extends")
								&& wordsInLine[4].equals("LiarsDiceBot")) {
							botNames.add(wordsInLine[2]);
						}
						line = in.readLine();
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} 
		
		return botNames;
	}

	/**
	 * @return The name of this Game implementation: "Liar's Dice"
	 */
	public String getGameName() {
		return name;
	}

}
