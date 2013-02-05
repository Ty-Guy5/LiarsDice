package liarsDiceModel;

public class TestBot3 extends LiarsDiceBot {

	@Override
	public String getName() {
		return "TestBot3";
	}

	@Override
	public Decision getDecision(GameInfo currentGameInfo) {
		Bid b = currentGameInfo.getCurrentBid();
		if(b == null){
			return new Bid(2, 2);
		}
		else{
			return new Bid(b.getFrequency() + 1, b.getDieNumber());
		}
	}

}
