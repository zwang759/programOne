/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016 
// PROJECT:          p0
// FILE:             Score.java
//
// Author: Chase Wember, cwember@wisc.edu, 9066586711, LEC 003
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 *  A container that stores references to instances of type Score in a 
 * list of those instances, which is unbroken and systemized.
 * @author Chase Wember
 */
public class Score {

	private String name;
	private double pointsEarned; 
	private double pointsPossible; 

	/**
	 * a constructor that accepts an assignment name, the 
	 * points earned, and the points possible for that assignment.
	 *
	 * @param name the name of the assignment 
	 * @param pointsEarned the number of points earned, must be 
	 * greater than or equal to zero. 
	 * @param pointsPossible the number of possible points for that
	 * assignment 
	 * @throws IllegalArgumentException 
	 */
	public Score(String name, double pointsEarned, double pointsPossible) {
		this.name = name;
		this.pointsEarned = pointsEarned; 
		this.pointsPossible = pointsPossible; 

		if (name == null) {
			throw new IllegalArgumentException();

		}

		if (pointsEarned < 0 || pointsPossible < 0) {
			throw new IllegalArgumentException();

		}

		if (pointsEarned > pointsPossible) {
			throw new IllegalArgumentException(); 

		}
	}

	/**
	 * returns the name of the assignment.
	 * @return name of the assignment.
	 */
	public String getName() {
		return name;

	}

	/**
	 * returns the points earned on the assignment.
	 * @return points earned on the assignment.
	 */
	public double getPoints() {
		return pointsEarned; 

	}

	/**
	 * returns the maximum possible points of an assignment.
	 * @return maximum possible points of an assignment.
	 */
	public double getMaxPossible(){
		return pointsPossible;

	}

	/**
	 * returns the first character of the name.
	 * @return first character of the name.
	 */
	public String getCategory(){
		//holds the first character of name. 
		String category = Character.toString(name.charAt(0));
		return category; 

	}

	/** 
	 * calculates and returns the percent earned on a assignment. 
	 * @return percent earned on a assignment.
	 */
	public double getPercent(){
		//holds the value of calculated percent. 
		double percent = (pointsEarned / pointsPossible) * 100;
		return percent;

	}
} //end of Score class 
