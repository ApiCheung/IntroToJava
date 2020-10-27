package Assignment2.PartI;

public class GravityCalculator {

	public static double calculatePosition(double time, double v, double x) {
		double a = -9.81;
		double position = 0.5 * a * Math.pow(time, 2) + v * time + x;
		return position;
	}
	
	public static void main(String[] args) {
		double t = 10;
		double v = 0;
		double x = 0;
		System.out.println("The object's position after " + t + " seconds is " +
				calculatePosition(t,v, 0) + " m.");
		
	}
}
