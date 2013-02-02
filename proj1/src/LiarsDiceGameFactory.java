import java.util.ArrayList;
import java.util.List;


public class LiarsDiceGameFactory implements GameFactory {
	private String name = "Liar's Dice";
	public LiarsDiceGameFactory(){
		
	}
	
	public Game getGameInstance() {
		return new LiarsDiceGame();
	}

	public List<Bot> getBots() {
		// TODO Reflection goes here - for now can hardcode here
		ArrayList<Bot> bots = new ArrayList<Bot>();
		bots.add(new TestBot1());
		
		return bots;
	}

	public String getGameName() {
		return name;
	}

}
