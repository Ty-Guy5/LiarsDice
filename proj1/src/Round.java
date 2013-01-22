import java.util.Collections;
import java.util.List;


public class Round {
	private List<Turn> turns;
	
	public Round(){
		
	}
	
	public Round(Round r){
		turns = Collections.unmodifiableList(r.getTurns());
	}

	public List<Turn> getTurns() {
		return turns;
	}
}
