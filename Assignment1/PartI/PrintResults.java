package Assignment1.PartI;

public class PrintResults {

	public static void main(String[] args) {
		
		System.out.println("The value of 1+1 is " + (1+1));
		int a = 10 + 5;
		int b = 50 - 23;
		int c = 12 * 13;
		double d = 20.0 / 3.0;
		int e = 100 % 7;
		double f = Math.pow(4, 3);
		String s1 = "the value of ";
		String s2 = " is ";
		System.out.println(s1 + "a" + s2 + a);
		System.out.println(s1 + "b" + s2 + b);
		System.out.println(s1 + "c" + s2 + c);
		System.out.println(s1 + "d" + s2 + d);
		System.out.println(s1 + "e" + s2 + e);
		System.out.println(s1 + "f" + s2 + f);
	}
}
