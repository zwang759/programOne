package p1;

import java.util.NoSuchElementException;

public class ScoreIterator implements ScoreIteratorADT {

	private ScoreList scoreList;
	private int currPos;
	private String scoreCat;
	
	public ScoreIterator(ScoreList scoreList, String scoreCat) {
		this.scoreList = new ScoreList();
		this.currPos = 0;
		this.scoreCat = scoreCat;
		for (int i = 0; i < scoreList.size(); i++) {
			if (scoreList.get(i).getCategory().equals(scoreCat)) {
				this.scoreList.add(scoreList.get(i));
			}
		}
	}
	
	public boolean hasNext() { 
		return currPos < scoreList.size();
	}
	
	public Score next() {
		Score result;
		if (! hasNext()) {
			throw new NoSuchElementException();
		}
		result = scoreList.get(currPos);
		currPos++;			
		return result;
	}

}
