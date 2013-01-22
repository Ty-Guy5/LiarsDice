import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GameHistory {
	private List<Round> rounds;
	
	public GameHistory(){
		rounds = new ArrayList<Round>();
		rounds.add(new Round());
	}
	
	public GameHistory(GameHistory gh){
		//don't ask what this is about. Just kidding.
		//This whole process is to give the bots an unmodifiable version
		//of the game history ... so they can't do anything stupid.
		List<Round> tempList = new ArrayList<Round>();
		for(int i = 0; i < gh.getRounds().size(); i++){
			tempList.add(new Round(gh.getRounds().get(i)));
		}
		rounds = Collections.unmodifiableList(tempList);
	}

	public List<Round> getRounds() {
		return rounds;
	}
}
