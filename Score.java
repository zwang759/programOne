/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2017
// PROJECT:          p0
// FILE:             Score.java
//
// Authors: Connor Beckerle
//
// ---------------- OTHER ASSISTANCE CREDITS 
// n/a
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * This class stores data for one score, corresponding to one assignment.
 * The data stored is the name, the points earned, and the maximum possible
 * points.
 * 
 * <p>Bugs: n/a
 * 
 * @author Connor Beckerle
 */
public class Score { 
	
	/** The name of this Score. The first character is the category. */
	private String name;
	
	/** The points earned. Should not exceed maxPossible */ 
	private double points;
	
	/** The maximum possible points to be earned */
	private double maxPossible;
	
	/**
	 * Constructor method for new Score. Assumes:
	 * 1. Non-null name
	 * 2. pointsEarned, maxPossible >= 0
	 * 3. pointsEarned <= maxPossible
	 * 
	 * @param assignmentName
	 * @param pointsEarned
	 * @param maxPossible
	 */
	public Score(String name, double pointsEarned, double maxPossible) {
		super();
		if (name == null) {
			throw new IllegalArgumentException("Score name can't be null");
		}
		if (pointsEarned < 0) {
			throw new IllegalArgumentException("Points earned can't be less than 0");
		}
		if (maxPossible < 0) {
			throw new IllegalArgumentException("Max points can't be less than 0");
		}
		if (pointsEarned > maxPossible) {
			throw new IllegalArgumentException("Points earned can't greater than max points");
		}
		this.name = name;
		this.points = pointsEarned;
		this.maxPossible = maxPossible;
	}

	/**
	 * Gets the name of this instance.
	 * 
	 * PRECONDITIONS: None
	 * POSTCONDITIONS: None
	 * 
	 * @return the name of this instance
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the points of this instance.
	 * 
	 * PRECONDITIONS: None
	 * POSTCONDITIONS: None
	 * 
	 * @return the points of this instance
	 */
	public double getPoints() {
		return points;
	}

	/**
	 * Gets the max points of this instance.
	 * 
	 * PRECONDITIONS: None
	 * POSTCONDITIONS: None
	 * 
	 * @return the max points of this instance
	 */
	public double getMaxPossible() {
		return maxPossible;
	}

	/**
	 * Gets the first character of this instance's name.
	 * 
	 * PRECONDITIONS: None
	 * POSTCONDITIONS: None
	 * 
	 * @return the first character of this instance's name
	 */
	public String getCategory() {
		return name.substring(0,1);
	}
	
	/**
	 * Gets the percent of maximum points earned on this instance.
	 * 
	 * PRECONDITIONS: None
	 * POSTCONDITIONS: None
	 * 
	 * @return the percent of maximum points earned on this instance
	 */
	public double getPercent() {
		return (100 * (points/maxPossible));
	}
}
