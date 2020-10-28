package Assignment6.PartIII;

import java.util.ArrayList;
import java.util.Collection;
import javafx.util.Pair;

/**
 * @author Esmee Zhang
 * @date 10/23/20 7:26 下午
 * @projectName IntroToJava-NYU
 */
public class ResultPrinter{

	MathOperation op;

	
	public ResultPrinter(MathOperation op) {
		this.op = op;
	}


	public void go(double a, double b) {

		System.out.println("result is " + (a - b));
	}

	public static void go(double a, double b, MathOperation op) {
		System.out.println("result is " + op.operation(a, b));
	}
	
	public static void go(Collection<Pair<Double,Double>> c, MathOperation op) {
		c.forEach(p -> System.out.println("result is " + op.operation(p.getKey(), p.getValue())));

	}


	
	public static void main(String[] args) {
		MathOperation op = (a, b) -> a - b;
		ResultPrinter rp = new ResultPrinter(op);
		
		rp.go(3.0, 4.0);
		
		ResultPrinter.go(4.0, 2.0, op);
		
		ArrayList<Pair<Double,Double>> al = new ArrayList<Pair<Double,Double>>();
		Pair<Double, Double> p = new Pair<Double, Double>(3.0, 4.0);
		al.add(p);
		p = new Pair<Double, Double>(9.0, 6.0);
		al.add(p);
		p = new Pair<Double, Double>(7.0, 8.0);
		al.add(p);
		
		ResultPrinter.go(al, op);
		
	}
}
