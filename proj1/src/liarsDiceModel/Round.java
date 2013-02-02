package liarsDiceModel;
import java.util.ArrayList;
import java.util.List;


public class Round {
	private List<Turn> turns;
	private Result result;
	
	public Round(){
		turns = new ArrayList<Turn>();
		result = Result.UNFINISHED;
	}
	
	public Round(List<Turn> turns, Result result){
		this.turns = turns;
		this.result = result;
	}
	
	public Round(Round r){
		//copies each turn in the other round into a new turns array
		//don't need deep copy because turns are not modifiable
		turns = new ArrayList<Turn>(r.getTurns());
		result = r.result;
	}

	public List<Turn> getTurns() {
		return turns;
	}
	
	public Result getResult(){
		return result;
	}
	
	public void addTurn(Turn turn){
		turns.add(turn);
	}

	public void end(Result result) {
		this.result = result;
	}
}
