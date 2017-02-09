import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

public class ConnorTest {

	@Test
	public void test() {
		String fname;
		//String[] strlist;
		//strlist = new String[] {"C:/Users/conno/Downloads/temp filething.txt", "no"};
		/*
		
		*/
		/*Scanner sc = null;
		
		try {	
			 sc = new Scanner(new File(fname));
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
			e.printStackTrace();
		}
		System.out.println(sc);
	
		String line;
		String delim = "[ ]";
		GradeEstimator estimator;
		fname = "C:/Users/conno/Downloads/temp filething.txt";
		
		estimator = new GradeEstimator();
		
		//Get letter grades
		line = sc.nextLine();
		estimator.setLetterGrades(line.split(delim));
		for (String s : estimator.getLetterGrades()) {
			System.out.println(s);
		}
		*/
		
		fname = "C:/Users/conno/Downloads/temp filething.txt";
		String[] args = {fname};
		
		GradeEstimator.main(args);
		
		/*String[] s = new String[1];
		s[0] = "yes";
		System.out.println(s[0]);
		*/
		//GradeEstimator.main(strlist);
		//fail("Not yet implemented");
	}

}
