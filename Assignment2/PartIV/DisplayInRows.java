package Assignment2.PartIV;

public class DisplayInRows {

	static final int ROW_LENGTH = 10;
	
	public static void main(String[] args) {
		
		int[] intArray = new int[100];

		
		for (int i=0;i< intArray.length;i++) {
			intArray[i] = (int)(Math.random()*100);
		}

		for(int i = 0; i < ROW_LENGTH; i++){
			for(int j = 0; j < 10; j++){
				System.out.print(intArray[i+j] + " ");
			}
			System.out.println();

		}




		
	}
}
