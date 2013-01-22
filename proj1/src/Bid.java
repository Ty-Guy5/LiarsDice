//done
public class Bid implements Decision {
	private int dieNumber, frequency;
	
	public Bid(int frequency, int dieNumber){
		this.frequency = frequency;
		this.dieNumber = dieNumber;
	}
	
	public int getDieNumber(){
		return dieNumber;
	}

	public int getFrequency() {
		return frequency;
	}
}
