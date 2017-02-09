/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016 
// PROJECT:          p1 (ScoreList & Score)
// FILE:             p1
//
// Author: Savannah Mann
//punch
//////////////////////////// 80 columns wide //////////////////////////////////
/**
 * Stores the item Score in a List format, implements ScoreList ADT 
 *
 * @author Savannah Mann
 */


public class ScoreList implements ScoreListADT {
	
	
	Score[] storedScores = new Score[0]; //An array of all added Scores
	Score[] tempStorage = new Score[0]; //Used for expanding the storedScores Array
	Score tempRemove = new Score("temp",99999999,99999999); //For holding removed Scores
	/** 
	 * Returns the number of Scores in the list or zero
	 * @return the number of scores in this list
	 */
	@Override
	public int size() {
		return storedScores.length;
	}
	
	
	/** 
	 * Adds the score to the end of this list.
	 * 
	 * @param s a non-null Score to place as the last item in the list.
	 * @throws IllegalArgumentException
	 */
	@Override
	public void add(Score s) throws IllegalArgumentException {
	
		if (s == null){
			throw new java.lang.IllegalArgumentException();
		}
		else{
		
		/////ADD MORE SPACE TO THE ARRAY ONE BY ONE
		tempStorage = storedScores; //transfer items to temp location
		storedScores = new Score[tempStorage.length+1]; //expand the storage by one 
		//Transfer all items from temp
		for (int i = 0; i<tempStorage.length; i++){
			storedScores[i] = tempStorage[i];
		}
		
		storedScores[storedScores.length-1] = s; //moves Score s to an empty spot in the array

		}
	}

	
	/**
	 * Removes and returns the item at index position i.
	 * If i is less than zero or greater than size()-1,
	 * will throw an IndexOutOfBoundsException.
	 * @param i must be greater than or equal to zero and less than size()
	 * @return the item at index i
	 * @throws IndexOutOfBoundsException
	 */
	@Override
	public Score remove(int i) throws IndexOutOfBoundsException {
		
		if (i < 0 || i> storedScores.length-1){
			throw new java.lang.IndexOutOfBoundsException();
		}
		else{
			
			tempRemove = storedScores[i];
			tempStorage = storedScores; //transfer items to temp location
			storedScores = new Score[tempStorage.length-1]; //decrease the storage by one 
			
			//Transfer all items from temp, but leave out i
			for (int j = 0; j<i-1; j++){
				storedScores[j] = tempStorage[j];
			}
			
			for (int k = i+1; k<tempStorage.length; k++){
				storedScores[k-1] = tempStorage[k];
			}
		
			return tempRemove;
		}
	}

	
	/**
	 * Returns (without removing) the item at index position i.
	 * If i is less than zero or greater than size()-1,
	 * will throw an IndexOutOfBoundsException.
	 * @param i must be greater than or equal to zero and less than size()
	 * @return the item at index i
	 * @throws IndexOutOfBoundsException
	 **/
	@Override
	public Score get(int i) throws IndexOutOfBoundsException {
		if (i < 0 || i> storedScores.length-1){
			throw new java.lang.IndexOutOfBoundsException();
		}
		else{
		return storedScores[i];
		}
	}

}
