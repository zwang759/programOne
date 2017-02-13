/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2017
// PROJECT:          p1
// FILE:             GradeEstimator.java
//
// TEAM:    Team 82 (Baked Goods Dinosaurs)
// Authors
// Author1: Connor Beckerle, cbeckerle@wisc.edu, 9077878594, 003
// Author2: Zhiheng Wang, zwang759@wisc.edu, 9074796922, 003
// Author3: Chase Wember, cwember@wisc.edu, 9066586711, 003
// Author4: Matt Marcouiller, mcmarcouille@wisc.edu, 9071489638, 003
// Author5: Savannah Mann, smann5@wisc.edu, ?, 003
// Author6: Evangeline Li, zli483@wisc.edu, ?, 003
//
// ---------------- OTHER ASSISTANCE CREDITS 
// Persons: N/A
// 
// Online sources: N/A
//////////////////////////// 80 columns wide //////////////////////////////////


import java.util.NoSuchElementException;

/**
 * Creates an iterator object used to iterate over a list of Score objects.
 * Takes a category parameter, and only returns Scores of that category.
 * 
 * <p>Bugs: none
 * 
 * @author Connor Beckerle, Zhiheng Wang, Chase Wember, Matt Marcouiller, Savannah Mann, Evangeline Li
 *
 **/
public class ScoreIterator implements ScoreIteratorADT {

	private ScoreList scoreList; //The current ScoreList to be checked
	private int currPos; //The current position of the iterator
	
	/**Constructor
	 * Creates a new iterator, with a new ScoreList that is a subset of Scores
	 * that match the category.
	 * 
	 * PRECONDITIONS: N/A
	 * 
	 * POSTCONDITIONS: N/A
	 * 
	 * @param scoreList
	 * @param scoreCat
	 */
	public ScoreIterator(ScoreList scoreList, String scoreCat) {
		this.scoreList = new ScoreList();
		this.currPos = 0;
		
		// adds Scores to the new list for the iterator if they match the category
		for (int i = 0; i < scoreList.size(); i++) {
			if (scoreList.get(i).getCategory().equals(scoreCat)) {
				this.scoreList.add(scoreList.get(i));
			}
		}
	}
	
	//Interface inherited methods
	
	/**
	 * Indicates if there is another item remaining in the iterator.
	 * 
	 * PRECONDITIONS: N/A
	 * 
	 * POSTCONDITIONS: N/A
	 * 
	 * @return true if there is another item remaining in the iterator, else false
	 */
	public boolean hasNext() { 
		return currPos < scoreList.size();
	}
	
	/**
	 * Returns the next item in the iterator and increments the position.
	 * 
	 * PRECONDITIONS: Assumes there is a next item.
	 * 
	 * POSTCONDITIONS: N/A
	 * 
	 * @return the next item in the iterator
	 */
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
