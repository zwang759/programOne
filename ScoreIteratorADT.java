package p1;

import java.util.NoSuchElementException;

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
