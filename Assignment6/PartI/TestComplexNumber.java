package Assignment6.PartI;
/**
 * @author Esmee Zhang
 * @date 10/22/20 5:42 下午
 * @projectName IntroToJava-NYU
 */
import java.util.Scanner;

public class TestComplexNumber {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter the first complex number: ");
    double a = input.nextDouble();
    double b = input.nextDouble();
    ComplexNumber c1 = new ComplexNumber(a, b);

    System.out.print("Enter the second complex number: ");
    double c = input.nextDouble();
    double d = input.nextDouble();
    ComplexNumber c2 = new ComplexNumber(c, d);

    ComplexNumber c3 = (ComplexNumber)c1.clone();
    System.out.println(c1 == c3);
    System.out.println(c3.getReal ());
    System.out.println(c3.getImaginary ());

    System.out.println("(" + c1+ ")" + " + " + "(" + c2 + ")" + " = " + c1.add(c2));
    System.out.println("(" + c1 + ")" + " - " + "(" + c2 + ")" + " = " + c1.subtract(c2));
    System.out.println("(" + c1 + ")" + " * " + "(" + c2 + ")" + " = " + c1.multiply(c2));
    System.out.println("(" + c1 + ")" + " / " + "(" + c2 + ")" + " = " + c1.divide(c2));
    System.out.println("|" + c1 + "| = " + c1.abs());

	System.out.println(c1.getReal());
	System.out.println(c1.getImaginary());
  }
}
