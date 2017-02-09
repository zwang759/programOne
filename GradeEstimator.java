import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GradeEstimator {

	public static void main(String[] args) {
		boolean readFromFile = true; 
		if (args.length != 1){
			System.out.println(Config.USAGE_MESSAGE);
			readFromFile = false; 

		}
		else {
			try {
				GradeEstimator estimator = createGradeEstimatorFromFile(args[0]);
			} catch (FileNotFoundException | GradeFileFormatException e) {
				System.out.print(e.getMessage());
				System.exit(0);
			}
			
		}
	}
	public static GradeEstimator createGradeEstimatorFromFile( String gradeInfo )
			throws FileNotFoundException, GradeFileFormatException {
		FileInputStream fstream = new FileInputStream(gradeInfo);
		Scanner fileScnr = new Scanner(fstream);
		
		List<String> gradeLetter = new ArrayList<String>();
		String[] line = fileScnr.nextLine().split(" ");
		for (int i = 0; i < line.length; i++) {
			if (line[i].equals("#") || line[i].startsWith("#")) {
				break;
			}
			gradeLetter.add(line[i]);
		}
		
		double[] gradeThresh = new double[gradeLetter.size()];
		for (int i = 0; i < gradeThresh.length; i++) {
			if (fileScnr.hasNextDouble()) { 
				gradeThresh[i] = fileScnr.nextDouble();
			} else if (fileScnr.next().equals("#")) {
				break;
			} else {
				throw new GradeFileFormatException("fix this later");
			}
		}
		fileScnr.nextLine();
		String[] catKey = fileScnr.
		getEstimateReport();
	}
	
	public String getEstimateReport() {
		
	}
}

