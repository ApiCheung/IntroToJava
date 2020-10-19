import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.Arrays;

public class Matrix {
	//define exception class
	public static class ExceptionWrongMatrixValues extends NumberFormatException {
		public ExceptionWrongMatrixValues(String s){
			super(s);
		}

	}

	public static class ExceptionWrongMatrixDimension extends RuntimeException{
		public ExceptionWrongMatrixDimension(String s) {
			super(s);
		}

	}

	double[][] mtx;
	
	public Matrix(int m, int n) {
		this.mtx = new double[m][n];
		
	}
	
	public int numRows() {

		return mtx.length;
	}
	
	public double[] getRow(int i) {

		return mtx[i];
	}
	
	public double getElement(int i, int j) {
		return mtx[i][j];
	}
	
	public void setElement(int i, int j, double val) {

		mtx[i][j] = val;
	}
	
	public int numColumns() {

		return mtx[0].length;
	}
	
	public void save(String filename) {
		PrintWriter out = null;
		try{
			out = new PrintWriter(new FileWriter(filename));
			out.println(this.numRows() + " " + this.numColumns());
			for(int i = 0; i < this.numRows(); i++){
				for(int j = 0; j < this.numColumns(); j++){
					out.print(this.mtx[i][j] + " ");
				}
				out.println();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(out != null){
				out.close();
			}
		}

	}
	
	public static Matrix read(String filename) throws ExceptionWrongMatrixDimension, ExceptionWrongMatrixValues, IOException{
		FileReader f = new FileReader(filename);
		BufferedReader in = new BufferedReader(f);
		String line = in.readLine();
		String[] rowCol = line.split(" ");

		int row = Integer.parseInt(rowCol[0]);
		int col = Integer.parseInt(rowCol[1]);

		Matrix matrix = new Matrix(row, col);

		line = in.readLine();
		int i = 0;
		while(line != null) {
			String[] el = line.split(" ");


			if(el.length > col || el.length < col) throw new ExceptionWrongMatrixDimension("column out of bound");

			for(int j = 0; j < col; j++){

				if(i == row) {
					throw new ExceptionWrongMatrixValues("row out of bound");
				}else{
					try {
						matrix.setElement(i, j, Double.parseDouble(el[j]));
					}catch (NumberFormatException e){
						throw new ExceptionWrongMatrixValues("not a number");
					}
				}
			}
			line = in.readLine();
			i++;
		}
		return matrix;
	}
	
	public Matrix sum(Matrix m) {

		if(this.numRows() == m.numRows() && this.numColumns() == m.numColumns()){
			Matrix res = new Matrix(this.numRows(), this.numColumns());
			for(int i= 0; i < numRows(); i++){
				for(int j = 0; j < numColumns(); j++){
					res.setElement(i,j, (this.getElement(i, j) + m.getElement(i, j)));
				}
			}
			return res;
		}else{
			System.out.println("tow matrix have different dimension");
			return null;
		}


	}
	
	// this will come in handy for the multiplication part
	public double[] getColumn(int j) {
		if (j >= this.numColumns()) {
			return null;
		}
		double[] col = new double[this.numRows()];
		for (int i=0; i<mtx.length; i++) {
			col[i] = mtx[i][j];
		}
		return col;
	}


	
	public Double dotproduct(double[] v1, double[] v2) {
		Double result = null;
		if (v1.length == v2.length) {
			result = 0.0;
			for (int i=0; i<v1.length ; i++) {
				result += v1[i] * v2[i];
			}
		}
		return result;
	}
	
	public Matrix product(Matrix m) {
		//System.out.println("??");
		//System.out.println(m.numColumns() + " " +m.numRows());
		if(this.numColumns() != m.numRows()) {
			System.out.println("dimension is not productable fo matrix");
			return null;
		}
		//System.out.println("!!");

		Matrix res = new Matrix(this.numRows(), m.numColumns());
		//System.out.println(this.numRows() + " " + this.numColumns());
		for(int j = 0; j < this.numRows(); j ++) {
			double[] row = new double[this.numColumns()];
			for (int i = 0; i < this.numColumns(); i++) {
				row[i] = this.getElement(j,i);
			}

			//System.out.println(Arrays.toString(row));

			for(int r = 0; r < m.numColumns(); r++){
				//System.out.println(Arrays.toString(m.getColumn(r)));
				//System.out.println(this.dotproduct(row, m.getColumn(r)));
				res.setElement(j, r, this.dotproduct(row, m.getColumn(r)));
				}
			}



		return res;
	}
	
	public static void main(String[] args) {
		Matrix m1 = new Matrix(2,3);
		m1.setElement(0, 0, 1.0);
		m1.setElement(0, 1, 1.0);
		m1.setElement(0, 2, 1.0);
		m1.setElement(1, 0, 1.0);
		m1.setElement(1, 1, 1.0);
		m1.setElement(1, 2, 1.0);
		
		Matrix m2 = new Matrix(3,2);
		m2.setElement(0, 0, 2.0);
		m2.setElement(0, 1, 2.0);
		m2.setElement(1, 0, 2.0);
		m2.setElement(1, 1, 3.0);
		m2.setElement(2, 0, 3.0);
		m2.setElement(2, 1, 3.0);
		
		Matrix result = m1.product(m2);
		m1.save("m1");
		m2.save("m2");
		result.save("result");
		
		try {
			m1 = Matrix.read("m1");
			m2 = Matrix.read("m2");
			Matrix m3 = Matrix.read("result");
		} catch (Exception e) {
			System.err.println("error: " + e);
		}

		try {
			Matrix badMatrix2 = Matrix.read("wrongrows");
		} catch (ExceptionWrongMatrixDimension e) {

			System.err.println("wrong dimensions: " + e);
		} catch (ExceptionWrongMatrixValues e) {
			System.err.println("wrong matrix values: " + e);

		} catch (ArrayIndexOutOfBoundsException e){

		}
		catch (IOException e ){
			System.out.println(e);
		}

		try {
			Matrix badMatrix = Matrix.read("wrongcolumns");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ExceptionWrongMatrixValues e2) {
			System.err.println("wrong matrix values: " + e2);
		} catch (ExceptionWrongMatrixDimension e1) {
			System.err.println("wrong dimensions: " + e1);
		}

		//test product function
		try{
			Matrix matrixinput1 = Matrix.read("matrixinput1");
			Matrix matrixinput2 = Matrix.read("matrixinput2");
			Matrix res2 = matrixinput1.product(matrixinput2);
			//sum 2 different dimension
			Matrix res4 = matrixinput1.sum(matrixinput2);

			res2.save("res2");

		}catch(Exception e){
			System.out.println(e);
		}

		try{
			Matrix matrixinput2 = Matrix.read("matrixinput2");
			Matrix matrixinput3 = Matrix.read("matrixinput3");
			Matrix res3 = matrixinput2.sum(matrixinput3);

			res3.save("res3");

		}catch(Exception e){
			System.out.println(e);
		}

		//cannot be product 
		try{
			Matrix m11 = Matrix.read("m1");
			Matrix matrixinput11 = Matrix.read("matrixinput1");
			Matrix res5 = m11.product(matrixinput11);

		}catch(Exception e){
			System.out.println(e);
		}



		
	}
	
}
