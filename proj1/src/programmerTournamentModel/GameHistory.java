package programmerTournamentModel;
import java.util.ArrayList;
import java.util.List;
//done

import liarsDiceModel.Result;
import liarsDiceModel.Round;
import liarsDiceModel.Turn;

public class GameHistory {
	private List<Round> rounds;
	
	public GameHistory(){
		rounds = new ArrayList<Round>();
		rounds.add(new Round());
	}
	
	public GameHistory(GameHistory gh){
		//deep copy:
		List<Round> tempList = new ArrayList<Round>();
		for(int i = 0; i < gh.getRounds().size(); i++){
			tempList.add(new Round(gh.getRounds().get(i)));
		}
		rounds = tempList;
	}

	public List<Round> getRounds() {
		return rounds;
	}

	public void addRound(Round round){
		rounds.add(round);
	}
	
	public void addTurn(Turn turn){
		rounds.get(rounds.size() - 1).addTurn(turn);
	}
	
	public void endRound(Result result){
		rounds.get(rounds.size() - 1).end(result);
	}
}
