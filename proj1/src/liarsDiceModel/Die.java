package liarsDiceModel;
import java.util.Random;
//done
public class Die {
	private int value;
	
	public Die(){
		rollDie();
	}
	
	public int getValue(){
		return value;
	}
	
	private void rollDie(){
		Random rand = new Random();
		value = rand.nextInt(6) + 1;
	}
}
