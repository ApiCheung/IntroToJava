package Assignment2.PartIV;

public class ArrayStats {

	public static void main(String[] args) {
		int[] intArray = new int[50];
		
		int smallestValue = 100;
		int largestValue = -1;
		double meanAverage;
		int sum = 0;
		
		/* initialize the source Array */
		for (int i = 0;i < intArray.length; i++) {
			intArray[i] = (int)(Math.random()*100);
		}

		for(int i = 0; i < intArray.length; i++){
			if(intArray[i] < smallestValue){
				smallestValue= intArray[i];
			}
			if(intArray[i] > largestValue){
				largestValue = intArray[i];
			}
		}

		for(int i = 0; i < intArray.length; i++){
			sum += intArray[i];
		}
		meanAverage = sum/100;

		
		System.out.println("Smallest value in the array is " + 
							smallestValue);
		System.out.println("Largest value in the array is " + 
							largestValue);
		System.out.println("Mean average value of array elements is " + 
							meanAverage);
	}
}
