/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2017
// PROJECT:          p1
// FILE:             GradeEstimator.java
//
// TEAM:    Team 82 (Baked Goods Dinosaurs)
// Authors
// Author1: Connor Beckerle, cbeckerle@wisc.edu, cbeckerle, 003
// Author2: Zhiheng Wang, zwang759@wisc.edu, zwang759, 003
// Author3: Chase Wember, cwember@wisc.edu, cwember, 003
// Author4: Matt Marcouiller, mcmarcouille@wisc.edu, mcmarcouille, 003
// Author5: Savannah Mann, smann5@wisc.edu, smann5, 003
// Author6: Evangeline Li, zli483@wisc.edu, zli483, 003
//
// ---------------- OTHER ASSISTANCE CREDITS 
// Persons: N/A
// 
// Online sources: N/A
//////////////////////////// 80 columns wide //////////////////////////////////


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Reads in a flat file with grade information, creates an instance of 
 * GradeEstimator with that info, estimates a final score, and prints a 
 * summary of all of this.
 * 
 * <p>Bugs: none
 * 
 * @author Connor Beckerle, Zhiheng Wang, Chase Wember, Matt Marcouiller, Savannah Mann, Evangeline Li
 *
 */
public class GradeEstimator {

	// the instance of the GradeEstimator used to store data
	private static GradeEstimator estimator;
	// the string containing the file name and/or location of grade data
	private static String gradeInfo;
	// the list of all scores read in
	private ScoreList scores;
	// the list of letter grades
	private String[] letterGrades;
	// the list of grade thresholds (corresponds to letterGrades)
	private Double[] gradeThresholds;
	// the list of grade categories (single letter prefixes)
	private String[] categories;
	// the list of category weights (corresponds to categories)
	private Double[] catWeights;
	
	/**
	 * Main method - creates a gradeEstimator instance and prints a summary report
	 * 
	 * PRECONDITIONS: Single argument that is the filename of a correctly
	 * formatted grade list
	 * 
	 * POSTCONDITIONS: N/A
	 * 
	 * @return VOID
	 */
	public static void main(String[] args) {

		// validates that only one argument was passed
		if (args.length != 1) {
			System.out.println(Config.USAGE_MESSAGE);
			gradeInfo = "-1"; // flag that the argument count was invalid
		}
		else {
			gradeInfo = args[0];
		}		
		
		try {
			estimator = createGradeEstimatorFromFile(gradeInfo);
		}
		catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			return;
		}
		catch (GradeFileFormatException e) {
			System.out.println(e.getMessage());
			return;
		}
		System.out.println(estimator.getEstimateReport());
	}
	
	/**
	 * Creates a gradeEstimator instance from input file
	 * 
	 * PRECONDITIONS: Correct formatting of input file
	 * 
	 * POSTCONDITIONS: N/A
	 * 
	 * @param gradeInfo - string for grade filename
	 * @return the gradeEstimator instance
	 */
	public static GradeEstimator createGradeEstimatorFromFile(String gradeInfo) 
		      throws FileNotFoundException, GradeFileFormatException {
		
		// temporary array of String data from parsing each line
		String[] strArr;
		// temporary array of Double data for lines that must be converted
		Double[] doubleArr;
		// the ScoreList to hold all the scores
		ScoreList scores = new ScoreList();
		// temporary name for each assignment
		String assignmentName;
		// temporary converted score for each assignment
		Double assignmentEarnedScore;
		// temporary converted max score for each assignment
		Double assignmentMaxScore;
		// scanner to parse input file
		Scanner sc = null;
		
		// checks for invalid arguments flag
		if (gradeInfo == "-1") {
			sc = new Scanner(Config.GRADE_INFO_FILE_FORMAT_EXAMPLE);
		}
		else {
			sc = new Scanner(new File(gradeInfo));
		}
		
		estimator = new GradeEstimator();
		try {
			
			// Get letter grades
			strArr = cleanLine(sc.nextLine());
			estimator.setLetterGrades(strArr);
			
			// Get grade thresholds
			strArr = cleanLine(sc.nextLine());
			doubleArr = strArrToDouble(strArr);
			estimator.setGradeThresholds(doubleArr);
			
			// Get category names
			strArr = cleanLine(sc.nextLine());
			estimator.setCategories(strArr);
			
			// Get category weights
			strArr = cleanLine(sc.nextLine());
			doubleArr = strArrToDouble(strArr);
			estimator.setCatWeights(doubleArr);
			
			// Build Score List
			while (sc.hasNextLine()) {
				strArr = cleanLine(sc.nextLine());
				assignmentName = strArr[0];
				assignmentEarnedScore = Double.parseDouble(strArr[1]);
				assignmentMaxScore = Double.parseDouble(strArr[2]);
				scores.add(new Score(assignmentName, assignmentEarnedScore, assignmentMaxScore));
			}
			estimator.setScores(scores);
		}
		catch (NumberFormatException e) { // converts number format errors from invalid file format
			throw new GradeFileFormatException("GradeFileFormatException");
		}
		finally {
			sc.close();
		}
		return estimator;
	}
	
	
	/**
	 * Switches the Array of Strings to an Array of Double
	 * 
	 * @param strArr
	 * @return doubleArr
	 */
	static private Double[] strArrToDouble(String[] strArr) {
		// temporary Double array to return
		Double[] doubleArr = new Double[strArr.length];
		
		for (int i = 0; i < strArr.length; i++) {
			doubleArr[i] = Double.parseDouble(strArr[i]);
		}
		return doubleArr;
	}
	
	/**
	 * Removes comments and spaces from the line and returns an array of the contents
	 * 
	 * @param line
	 * @return retArr
	 */
	static private String[] cleanLine(String line) {
		String COMMENT_DELIM = "#"; // Will read until this String
		String PARSE_DELIM = "[ ]+"; // Regex to parse out the spaces
		String[] retArr; // array to return data in
		int strIndex = -1; // default value of the comment delimiter position
		
		strIndex = line.indexOf(COMMENT_DELIM);
		if (strIndex != -1) { // truncates string if there is a comment
			line = line.substring(0, strIndex); 
		}
		retArr = line.split(PARSE_DELIM);
		
		return retArr;
	}
	
	/**
	 * Builds a grade estimate report string from the info in a GradeEstimator
	 * 
	 * PRECONDITIONS: N/A
	 * 
	 * POSTCONDITIONS: N/A
	 * 
	 * @return grade estimate report in string format
	 */
	public String getEstimateReport(){
		//creates formatted output report
		/*
		 * Grade estimate is based on [length of score list] scores.
		[categoryA weighted average score]% = [categoryA average score]% * [categoryA weight]% for [categoryA name]
		[categoryB weighted average score]% = [categoryB average score]% * [categoryB weight]% for [categoryB name]
		[...repeat for each category...]
		--------------------------------
		[weighted percent score]% weighted percent
		Letter Grade Estimate:
		[letter grade estimate]
		 */
		
		// single-element array to build return string
		String[] retStr = new String[1];
		// array to hold scores of each category
		Double[] catScore = new Double[this.categories.length];
		// score of that category times its weight
		Double catWtScore = (double) 0;
		// total score earned by student
		Double totalPctScore = (double) 0;
		// body of summary
		String summaryBody = "";
		// letter grade earned
		String letterGrade = "";
		
		retStr[0] = "";
		
		// main loop that iterates through each category in turn
		for (int i = 0; i < this.categories.length; i++) {
			// NOTE - calcCatScores adds each assignment to retStr in addition to calculating scores
			catScore[i] = calcCatScore(scores, this.categories[i].substring(0,1), retStr);
			catWtScore = (catScore[i] * this.catWeights[i] / 100);
			totalPctScore += catWtScore;
			summaryBody = summaryBody + String.format("%7.2f%% = %5.2f%% of %2.0f%% for %s\n", catWtScore, catScore[i], (double) this.catWeights[i], this.categories[i]);
		}
		letterGrade = getLetterGradeFromPercent(totalPctScore);

		retStr[0] = retStr[0] + String.format("Grade estimate is based on %d scores\n", scores.size());
		retStr[0] = retStr[0] + summaryBody;
		retStr[0] = retStr[0] + "--------------------------------\n";
		retStr[0] = retStr[0] + String.format("%7.2f%% weighted percent\n", totalPctScore);
		retStr[0] = retStr[0] + String.format("Letter Grade Estimate: %s", letterGrade);

		return retStr[0];
	}
	
	
	/**
	 * Calculates score for one category at a time
	 * Makes an iterator for that category and builds up the percentage for it
	 * NOTE - this weights all scores within the category equally
	 * 
	 * PRECONDITIONS: N/A
	 * 
	 * POSTCONDITIONS: adds all the assignments to retStr
	 * 
	 * @param scores
	 * @param catName
	 * @param retStr
	 * @return score for that category
	 */
	private Double calcCatScore(ScoreList scores, String catName, String[] retStr) {

		// iterator to loop through that category's scores
		ScoreIterator itr = new ScoreIterator(scores, catName);
		// current score to process
		Score currScore = null;
		// total score of that category
		Double total = (double) 0;
		// count of all scores
		int count = 0;
		
		while (itr.hasNext()) {
			currScore = itr.next();
			total += currScore.getPercent(); // adds up score percentages
			count ++;
			// adds one assignment line
			retStr[0] = retStr[0] + String.format("%s %7.2f%%\n", currScore.getName(), currScore.getPercent());
		}
		// handles edge case of no scores for this category, returns 100
		if (count == 0) { 
			return (double) 100;
		}
		// returns average score.
		else {
			return (total / count);
		}
	}
	
	/**
	 * Picks the letter grade based on where the total score is wrt the thresholds
	 * 
	 * PRECONDITIONS: N/A
	 * 
	 * POSTCONDITIONS: N/A
	 * 
	 * @param totalPctScore
	 * @return letter grade
	 */
	private String getLetterGradeFromPercent(double totalPctScore) { 		
		
		for(int i = 0; i < gradeThresholds.length; i++){ 		
			if (gradeThresholds[i] <= totalPctScore){		
				return letterGrades[i];		
			}		
		}
		// if the score is below all thresholds, return this:
		return String.format("unable to estimate letter grade for %7.2f%%", totalPctScore);		
	}
	
	///// GETTERS AND SETTERS

	public static GradeEstimator getEstimator() {
		return estimator;
	}

	public static void setEstimator(GradeEstimator estimator) {
		GradeEstimator.estimator = estimator;
	}

	public static String getGradeInfo() {
		return gradeInfo;
	}

	public static void setGradeInfo(String gradeInfo) {
		GradeEstimator.gradeInfo = gradeInfo;
	}

	public ScoreList getScores() {
		return scores;
	}

	public void setScores(ScoreList scores) {
		this.scores = scores;
	}

	public String[] getLetterGrades() {
		return letterGrades;
	}

	public void setLetterGrades(String[] letterGrades) {
		this.letterGrades = letterGrades;
	}

	public Double[] getGradeThresholds() {
		return gradeThresholds;
	}

	public void setGradeThresholds(Double[] gradeThresholds) {
		this.gradeThresholds = gradeThresholds;
	}

	public String[] getCategories() {
		return categories;
	}

	public void setCategories(String[] categories) {
		this.categories = categories;
	}

	public Double[] getCatWeights() {
		return catWeights;
	}

	public void setCatWeights(Double[] catWeights) {
		this.catWeights = catWeights;
	}


}
