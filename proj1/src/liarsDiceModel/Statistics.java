package liarsDiceModel;

public class Statistics {
	private int wins, losses, exceptions, invalidDecisions, timeouts;

	public Statistics() {
		wins = 0;
		losses = 0;
		exceptions = 0;
		invalidDecisions = 0;
		timeouts = 0;
	}

	public int getWins() {
		return wins;
	}
	
	public void increaseWins(){
		wins++;
	}

	public int getLosses() {
		return losses;
	}
	public void increaseLosses(){
		losses++;
	}

	public int getExceptions() {
		return exceptions;
	}
	
	public void increaseExceptions(){
		exceptions++;
	}

	public int getInvalidDecisions() {
		return invalidDecisions;
	}
	
	public void increaseInvalidDecisions(){
		invalidDecisions++;
	}

	public int getTimeouts() {
		return timeouts;
	}
	
	public void increaseTimeouts(){
		timeouts++;
	}
	
	public String toString(){
		return "Wins: " + wins + "\tLosses: " + losses + "\tExceptions: " + exceptions 
				+ "\tInvalid Decisions: " + invalidDecisions + "\tTimeouts: " + timeouts;
	}
}
