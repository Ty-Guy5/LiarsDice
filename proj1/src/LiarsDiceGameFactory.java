
public class LiarsDiceGameFactory implements GameFactory {

	public LiarsDiceGameFactory(){
		
	}
	
	public Game getGameInstance() {
		return new LiarsDiceGame();
	}

}
