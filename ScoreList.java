package p1;
/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2017
// PROJECT:          p0
// FILE:             ScoreList.java
//
// Authors: Connor Beckerle
//
// ---------------- OTHER ASSISTANCE CREDITS 
// n/a
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * This class stores an orderd list of multiple instances of the score class.
 * 
 * <p>Bugs: n/a
 * 
 * @author Connor Beckerle
 */
public class ScoreList implements ScoreListADT {
	
	/** Total number of Scores in this scorelist */
	private int numScores = 0;
	
	/** Array used to hold Scores. Default size 10. */
	private Score[] scores = new Score[10];
	
	public ScoreList() {
	}

	/* (non-Javadoc)
	 * @see ScoreListADT#size()
	 */
	@Override
	public int size() {
		return numScores;
	}
	
	/* (non-Javadoc)
	 * @see ScoreListADT#add(Score)
	 */
	@Override
	public void add(Score s) throws IllegalArgumentException {
		if (s == null){
			throw new IllegalArgumentException("Cannot add null score to list");
		}
		if (numScores == scores.length) {
			expandArray();
		}
		scores[numScores] = s;
		numScores++;
	}

	/* (non-Javadoc)
	 * @see ScoreListADT#remove(int)
	 */
	@Override
	public Score remove(int i) throws IndexOutOfBoundsException {
		if (i < 0 || i >= numScores) {
			throw new IndexOutOfBoundsException();
		}
		Score retScore = scores[i];
		for (int k = i; k < (numScores - 1); k++) {
			scores[k] = scores[k+1]; // move scores down to fill in gap from removal
		}
		numScores--;
		return retScore;
	}

	/* (non-Javadoc)
	 * @see ScoreListADT#get(int)
	 */
	@Override
	public Score get(int i) throws IndexOutOfBoundsException {
		if (i < 0 || i >= numScores) {
			throw new IndexOutOfBoundsException();
		}
		return scores[i];
	}

	/**
	 * This doubles the size of the array used to store score data.
	 * 
	 * PRECONDITIONS: This should only be called by add() when it runs out of space.
	 * POSTCONDITIONS: Doubles the size of the scores array.
	 * 
	 */
	private void expandArray() {
		
		/** Temporarily holds scores in new, expanded array before
		 * transferring them back to the original array. */
		Score[] tempArr = new Score[this.scores.length * 2];
		
		for (int i = 0; i < this.scores.length; i++) {
			tempArr[i] = this.scores[i]; // transfer scores to temp array
		}
		this.scores = tempArr;
	}
}
