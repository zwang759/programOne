/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016 
// PROJECT:          p0 (ScoreList & Score)
// FILE:             p0
//
// Author: Savannah Mann
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * An item that stores a name, points recieved on an assignment, and points max for an assignment
 *
 * @author Savannah Mann
 */


public class Score {
	
	//Class variables
	String name; //Assignment name
	double points; //Points earned
	double possible; //Points possible
	
	
	/**Constructor
	 * @param name
	 * @param points
	 * @param possible
	 * @throws IllegalArgumentException
	 */
	public Score(String name, double points, double possible) throws IllegalArgumentException{
		if (name == null || points <0 || possible < points || possible < 0){
			throw new java.lang.IllegalArgumentException();
		}
		else{
		
		this.name = name;
		this.points = points;
		this.possible = possible;
		}
	}
	
	////////////// GETTERS ///////////////////
	
		/**Returns the name of the assignment
		 * 
		 * 
		 * @return
		 */
		public String getName(){
			return name;
		}
		/**Returns the points earned of the assignment
		 * 
		 * 
		 * @return
		 */
		public double getPoints(){
			return points;
		}
		
		/**Returns the points possible for the assignment
		 * 
		 * 
		 * @return
		 */
		public double getMaxPossible(){
			return possible;
		}
	
		
	//////////// OTHER METHODS /////////////////	
		
		
		
		/**
		 * Returns the first character of the name
		 * @return
		 */
		public String getCategory()	{
			return Character.toString(name.charAt(0)); 
		}
		
		/**
		 * Returns the percentage of points/possible times 100
		 * @return
		 */
		public double getPercent(){
			return (points/possible)*100;
		}
		
		
}
