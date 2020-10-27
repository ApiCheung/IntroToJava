package Assignment2.PartII;

public class Grades {

	public static String letterGrade(float score) {
		if(score <= 100 && score >= 90){
			return "A";
		}else if(score >=80 && score <= 89){
			return "B";

		}else if(score >=70 && score <=79){
			return "C";
		}else if(score >=60 && score <=69){
			return "D";
		}else if(score <=59 && score >= 0){
			return "F";
		}else{
			return null;
		}
	

	}
	
	public static void main(String[] args) {
		
		/* you probably want to use user input for the
		 * score using Scanner because you will have to convert
		 * a command line argument to a float, and we haven't
		 * gotten to string parsing yet
		 * 
		 * But you can also just set the "score" variable
		 * to whatever you want in the code, and that's fine too
		 */
		
		float score = 101;
		if(score > 100){
			System.out.println("invalid score");
		}else{
			String grade = letterGrade(score);

			// if the grade is not null print this out:
			System.out.println("The grade for a score of " + score + " is " + grade);
			// if the grade is null, print this out:

		}


		
	}
}
