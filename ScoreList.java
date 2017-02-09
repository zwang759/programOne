/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016 
// PROJECT:          p0
// FILE:             ScoreList.java
//
// Author1: Matt Marcouiller, mcmarcouille@wisc.edu, 9071489638, Lec 003
/////////////////////////////////////////////////////////////////////////////
/**
 * Provides code for ListADT methods to be used in further programs.
 *
 * @MattMarcouiller 
 */
public class ScoreList implements ScoreListADT
{
		
		//array that will house the scores entered by user 
		Score[] scoreArray = new Score[10];
		//int to keep track of number of scores currently in array
		private int numItems = 0;
		
		/** 
		 * Returns the number of Scores in the list or zero
		 * @return the number of scores in this list
		 */
		public int size()
		{
			return numItems;
		}

		
		/** 
		 * Extends array by doubling number of scores present 
		 * and copies old array into new larger one.
		 */
		private void expandArray() 
		{
			//new larger array
		    Score[] newArray = new Score[numItems*2];
		    
		    //for loop for copying old loop over
		    for (int k = 0; k < numItems; k++) 
		    {
		        newArray[k] = scoreArray[k];
		    }
		    scoreArray = newArray;
		}
		
		/** 
		 * Adds the score to the end of this list.
		 * 
		 * @param s a non-null Score to place as the last item in the list.
		 * @return 
		 * @throws IllegalArgumentException
		 */
		public void add(Score s)
		{	//if null throw exception 
			if (s == null)
			{
				throw new IllegalArgumentException();
			}
			//if the length and number of scores are equal, expand 
			if (scoreArray.length == numItems)
			{
				expandArray();
			}
			//assigning new score to end of list 
			scoreArray[numItems] = s;
			//increase number of scores 
			numItems++;
		}

		/**
		 * Removes and returns the item at index position i.
		 * If i is less than zero or greater than size()-1,
		 * will throw an IndexOutOfBoundsException.
		 * @param i must be greater than or equal to zero and less than size()
		 * @return the item at index i
		 * @throws IndexOutOfBoundsException
		 */
		public Score remove(int i)
		{	//if index below 0 or larger than the number of scores, throw excpt
			if (i < 0 || i >= numItems)
			{
				throw new IndexOutOfBoundsException();
			}
			//score to remove 
			Score  ob = scoreArray[i];
			
			//shifts scores down to fill positions 
			for( int k = i; k < numItems - 1; k++)
			{
				scoreArray[k] = scoreArray[k + 1];
			}
			//decrease number of scores 
			numItems--;
			
			//returning removed object 
			return ob;
	 
		}

		/**
		 * Returns (without removing) the item at index position i.
		 * If i is less than zero or greater than size()-1,
		 * will throw an IndexOutOfBoundsException.
		 * @param i must be greater than or equal to zero and less than size()
		 * @return the item at index i
		 * @throws IndexOutOfBoundsException
		 */
		public Score get(int i){
			
			//error check
			if( i < 0 || i >= numItems)
			{
				throw new IndexOutOfBoundsException();
			}
			
			//return item at pos 
			return scoreArray[i];
		}
	}
	

