package Assignment5.PartI;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
 
public class ListOfNumbers {
	
    private List<Integer> list;
    private String inFile;
 
    public ListOfNumbers () {
        list = new ArrayList<Integer>();
    }
    

    public ListOfNumbers (String inFile) {
    	this();
    	this.inFile = inFile;	
    }
    
    public void readList() throws IOException {
        try {
            FileReader f = new FileReader(inFile);
            BufferedReader in = new BufferedReader(f);
            String ln = in.readLine();
            while(ln != null){
                list.add(Integer.parseInt(ln));
                ln = in.readLine();
            }
        }catch (IOException ex){

            System.out.println("could not find file");
        }


        writeList();
    	
    }
    
    public void writeList() {
        PrintWriter out = null;
        try {
            System.out.println("Entering try statement");
            out = new PrintWriter(new FileWriter("outFile.txt"));
            for (int i = 0; i < list.size(); i++)
                out.println("Value at: " + i + " = " + list.get(i));
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Caught IndexOutOfBoundsException: " +
                                 e.getMessage());
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        } finally {
            if (out != null) {
                System.out.println("Closing PrintWriter");
                out.close();
            } else {
                System.out.println("PrintWriter not open");
            }
        }
    }
    
    public static void cat(String fileName) throws IOException {
        RandomAccessFile input = null;
        String line = null;
        File file = new File(fileName);
        try {
            input = new RandomAccessFile(file, "r");
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            return;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("could not find file");
        } finally {
            if (input != null) {
                input.close();
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
    	ListOfNumbers listOfNumbers = new ListOfNumbers("Assignment5/numberfile.txt");
    	ListOfNumbers.cat("Assignment5/numberfile.txt");
    	try {
            listOfNumbers.readList();
        }catch(IOException ex){
            System.out.println("Could not find file");
        }

    }

}
