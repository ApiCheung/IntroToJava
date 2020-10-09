package Assignment5.PartI;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

import Assignment5.PartI.shapes.*;
import Assignment5.PartI.shapes.Rectangle;

/* your tasks:
 * create a class called ShapeException
 * createShape should throw a ShapeException
 * in main(), you should catch the ShapeException
 * 
 */
public class ReadShapeFile {

	public static GeometricObject createShape(String shapeName) throws ShapeException {
		
		/* if shapeName is "Circle" return Circle();
		 * if shapeName is "Square" return Square();
		 * if shapeName is "Rectangle" return Rectangle();
		 * if it is not any one of these, it should throw
		 * a ShapeException
		 */
		if(shapeName.equals("Circle")){
			return new Circle();
		}else if(shapeName.equals("Rectangle")) {
			return new Rectangle();
		}else if(shapeName.equals("Square")) {
			return new Square();
		}else{
			throw new ShapeException("No such shape");
		}

	}
	public static class ShapeException extends Exception{
		ShapeException(String s){
			super(s);
		}
	}
	
	public static void main(String[] args) throws IOException, ShapeException {
		ArrayList<GeometricObject> shapeList = new ArrayList<GeometricObject>();
		File f = new File("Assignment5/shapes.txt");
		
		String inString = null;
		
		/* create a loop to read the file line-by-line */
		FileReader file = new FileReader(f);
		BufferedReader in = new BufferedReader(file);
		inString = in.readLine();
		while(inString != null){
			try {
				GeometricObject shape = createShape(inString);
				shapeList.add(shape);

			} catch (ShapeException ex) {
				System.err.println("Cannot create shape: " + inString);
				//System.out.println(ex);
			}
			inString = in.readLine();


		}

		for(GeometricObject o : shapeList){
			System.out.println(o);
		}
		System.out.println("The size of shapeList is " + shapeList.size());



		
	}
}
