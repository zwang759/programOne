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
 * An iterator to iterate through a subset of Scores of a certain category.
 * 
 **/
public interface ScoreIteratorADT {
	
	/**
	 * Indicates if there is another Score
	 * 
	 * @return True if there is another, False if not
	 */
	boolean hasNext();
	
	/**
	 * returns the next Score and increments position within the score list
	 * 
	 * @return the next Score
	 * @throws NoSuchElementException
	 */
	Score next() throws NoSuchElementException;
	
}
