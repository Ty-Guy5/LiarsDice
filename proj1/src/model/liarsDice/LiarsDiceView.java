package model.liarsDice;

import model.liarsDice.gameInfo.GameInfo;

public interface LiarsDiceView {

	void decisionRequest(GameInfo gameInfo);

	void reportGameResults();

	void reportInterruption();

}
