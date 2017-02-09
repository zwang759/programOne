/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016 
// PROJECT:          p0
// FILE:             ScoreList.java
//
// Author: Chase Wember, cwember@wisc.edu, 9066586711, LEC 003
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * A custom array list of scores. A Score keeps track of a name and points 
 * earned  vs points possible. 
 *
 * @author Chase Wember 
 */
public class ScoreList implements ScoreListADT {

	private Score[] points; 
	private int numPoints;

	/** 
	 * Creates an array of size 1. Keeps track of array size. 
	 */
	public ScoreList() {
		points = new Score[1]; //new array 
		numPoints = 0; //keeps track of array size. 

	}

	@Override

	/** 
	 * Returns the number of scores or "numPoints" in the list or zero
	 * @return the number of scores or "numPoints" in this list
	 */
	public int size() {
		return numPoints;

	}

	@Override

	/** 
	 * Adds the score to the end of this list. 
	 * Calls expandArray() if there is no space to add to the array. 
	 * @param s a non-null Score to place as the last item in the list.
	 * @throws IllegalArgumentException
	 */
	public void add(Score s) throws IllegalArgumentException {
		//s must not be null.
		if (s == null) {
			throw new IllegalArgumentException();

		}

		//expands array if array is not large enough to add to. 
		if (numPoints >= points.length) {
			expandArray();

		}

		points[numPoints] = s;
		numPoints++;

	}

	@Override

	/**
	 * Removes and returns the item at index position i.
	 * If i is less than zero or greater than numPoints-1,
	 * will throw an IndexOutOfBoundsException. Shifts remaining 
	 * items into correct index, once item is removed. 
	 * @param i must be greater than or equal to zero and less 
	 * than numPoints
	 * @return the item at index i
	 * @throws IndexOutOfBoundsException
	 */
	public Score remove(int i) throws IndexOutOfBoundsException {
		if (i < 0 || i > numPoints - 1) {
			throw new IndexOutOfBoundsException();

		}

		//holds the score that is to be removed 
		Score scoreToBeRemoved = points[i]; 
		//shifts remaining items into correct index. 
		for(int j = i +1; j < numPoints; j++) {
			points[j - 1] = points[j];

		}

		numPoints--;
		return scoreToBeRemoved;

	}

	@Override

	/**
	 * Returns (without removing) the item at index position i.
	 * If i is less than zero or greater than numPoints-1,
	 * will throw an IndexOutOfBoundsException.
	 * @param i must be greater than or equal to zero and less than size()
	 * @return the item at index i
	 * @throws IndexOutOfBoundsException
	 */
	public Score get(int i) throws IndexOutOfBoundsException {
		if (i < 0 || i > numPoints - 1) {
			throw new IndexOutOfBoundsException();

		}

		return points[i];

	}

	/**
	 * Doubles the size of the array points, if points runs out of space. 
	 */
	private void expandArray(){
		//Temporary array that is 2 times the size of points.
		Score[] a = new Score[points.length*2];
		//Copies items in points to a.
		for(int i = 0; i < numPoints; i++) {
			a[i] = points[i]; 

		}

		//Reassigns points as a. 
		points = a; 

	}
} //end of ScoreList class 
