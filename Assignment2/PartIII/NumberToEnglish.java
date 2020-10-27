package Assignment2.PartIII;

import java.util.Scanner;

public class NumberToEnglish {

	public static String numberToEnglish(int num) {

		String unit[] = {"Zero", "One", "Two", "Three", "Four", "Five","Six", "seven", "Eight", "Nine"};
		String teen[] = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
		String ten[] = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

		String res = new String();
		if(num < 10){
			res = unit[num];
		}else if(num < 20){
			res = ten[num - 10];
		}else if(num < 100){
			res = ten[num/10] + " " + numberToEnglish(num % 10);
		}else if(num < 1000){
			res = numberToEnglish(num/100) + " Hundred " + numberToEnglish(num % 100);
		}else if(num < 1000000){
			res = numberToEnglish(num/1000) + " Thousand " + numberToEnglish(num % 1000);
		}else if(num < 1000000000){
			res = numberToEnglish(num/1000000) + " Million " + numberToEnglish(num % 1000000);
		}else res = numberToEnglish(num/1000000000) + "Billion" + numberToEnglish(num % 1000000000);
		return res.trim();




	}


	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a number: " );
		
		int number = in.nextInt();
		if(number > 10) {

			System.out.println("The number " + number + " in English is " + NumberToEnglish.numberToEnglish(number));
		}else{

			System.out.println("The number " + number + " in English is negative " + NumberToEnglish.numberToEnglish(-number));
		}
	}
}
