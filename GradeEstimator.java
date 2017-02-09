import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GradeEstimator {

	private static GradeEstimator estimator;
	private static String gradeInfo;
	private ScoreList scores;
	private String[] letterGrades;
	private int[] gradeThresholds;
	private String[] categories;
	private int[] catWeights;
	
	public static void main(String[] args) {
		// should call createGradeEstimatorFromFile()
		// then should call getEstimateRerport()	
		if (args.length != 1) {
			System.out.println(Config.USAGE_MESSAGE);
			gradeInfo = "-1";
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
	
	public static GradeEstimator createGradeEstimatorFromFile(String gradeInfo) 
		      throws FileNotFoundException, GradeFileFormatException {
		
		String[] strArr;
		int[] intArr;
		ScoreList scores = new ScoreList();
		String assignmentName;
		Double assignmentEarnedScore;
		Double assignmentMaxScore;
		Scanner sc = null;
		
		if (gradeInfo == "-1") {
			sc = new Scanner(Config.GRADE_INFO_FILE_FORMAT_EXAMPLE);
		}
		else {
			sc = new Scanner(new File(gradeInfo));
		}
		
		estimator = new GradeEstimator();
		try { // is there a better way to ensure this is closed?
			
			// Get letter grades
			strArr = cleanLine(sc.nextLine());
			estimator.setLetterGrades(strArr);
			
			// Get grade thresholds
			strArr = cleanLine(sc.nextLine());
			intArr = strArrToInt(strArr);
			estimator.setGradeThresholds(intArr);
			
			// Get category names
			strArr = cleanLine(sc.nextLine());
			estimator.setCategories(strArr);
			
			// Get category weights
			strArr = cleanLine(sc.nextLine());
			intArr = strArrToInt(strArr);
			estimator.setCatWeights(intArr);
			
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
		finally {
			sc.close();
		}
		return estimator;
	}
	
	
	/**Switches the Array of Strings to an Array of Integers
	 * @param strArr
	 * @return intArr
	 */
	static private int[] strArrToInt(String[] strArr) {
		int[] intArr = new int[strArr.length];
		
		for (int i = 0; i < strArr.length; i++) {
			intArr[i] = Integer.parseInt(strArr[i]);
		}
		return intArr;
	}
	
	/**Removes comments and spaces from the line and returns an array of the contents
	 * 
	 * @param line
	 * @return retArr
	 */
	static private String[] cleanLine(String line) {
		String COMMENT_DELIM = "#"; //Will read until this String
		String PARSE_DELIM = "[ ]+"; //Parses out the spaces
		String[] retArr;
		int strIndex = -1;
		
		strIndex = line.indexOf(COMMENT_DELIM);
		if (strIndex != -1) {
			line = line.substring(0, strIndex);
		}
		retArr = line.split(PARSE_DELIM);
		
		return retArr;
	}
	
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

		Use the String class's ability to format values.  Use format code %7.2f for the weighted average scores and weighted percent score (7 digits total and 2 digits after decimal value), use format code %5.2f for the category unweighted average scores, and use format code %2.0f for the category weights.

		Do not print anything within this method. Return the String that you construct and let the main() method handle the printing.
		 */
		String retStr; //REPLACE WITH NORMAL STRING IF NO INDIVIDUAL THINGS NEEDED
		Double[] catScore = new Double[this.categories.length];
		Double catWtScore = (double) 0;
		Double totalPctScore = (double) 0;
		
		retStr = String.format("Grade estimate is based on %d scores\n", scores.size());
		
		for (int i = 0; i < this.categories.length; i++) {
			catScore[i] = calcCatScore(scores, this.categories[i].substring(0,1), retStr);
			catWtScore = (catScore[i] * this.catWeights[i] / 100);
			totalPctScore += catWtScore;
			retStr = retStr + String.format("%7.2f%% = %5.2f%% of %2.0f%% for %s\n", catWtScore, catScore[i], (double) this.catWeights[i], this.categories[i]);
		}
		
		retStr = retStr + "--------------------------------\n";
		retStr = retStr + String.format("%7.2f%% weighted percent\n", totalPctScore);

		return retStr;
	}
	
	
	/**Calculates the Categories Score
	 * Makes an iterator for each category and builds up the percentage for each category.
	 * @param scores
	 * @param catName
	 * @param retStr
	 * @return
	 */
	private Double calcCatScore(ScoreList scores, String catName, String retStr) {

		ScoreIterator itr = new ScoreIterator(scores, catName);
		Score currScore = null;
		Double total = (double) 0;
		int count = 0;
		
		while (itr.hasNext()) {
			currScore = itr.next();
			total += currScore.getPercent();
			count ++;
			// might need to print each here
		}
		if (count == 0) {
			return (double) 100;
		}
		else {
			return (total / count);
		}
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

	public int[] getGradeThresholds() {
		return gradeThresholds;
	}

	public void setGradeThresholds(int[] gradeThresholds) {
		this.gradeThresholds = gradeThresholds;
	}

	public String[] getCategories() {
		return categories;
	}

	public void setCategories(String[] categories) {
		this.categories = categories;
	}

	public int[] getCatWeights() {
		return catWeights;
	}

	public void setCatWeights(int[] catWeights) {
		this.catWeights = catWeights;
	}


}
