package Assignment3.PartI;

import java.util.ArrayList;

public class Holiday {
	private String name;
	private int day;
	private String month;
	
	public static void main(String[] args) {
		
		Holiday[] holidays = new Holiday[5];
		holidays[0] = new Holiday("May Day", 1, "May");
		holidays[1] = new Holiday("Independence Day", 4, "July");
		holidays[2] = new Holiday("Same month with Ind", 7, "July");
		holidays[3] = new Holiday("Star War Day", 4, "May");
		holidays[4] = new Holiday("New Year", 1, "January");

		System.out.println(inSameMonth(holidays[1], holidays[2]));
		System.out.println(inSameMonth(holidays[0], holidays[4]));

		System.out.println(avgDate(holidays));

		System.out.println(holidays[3].toString());

		
	}

	public Holiday(String name, int day, String month){
		this.name = name;
		this.day = day;
		this.month = month;
	}

	public static Boolean inSameMonth(Holiday a, Holiday b){
		if(a.month.equals(b.month) ){
			return true;
		}else{
			return false;
		}
	}

	public static double avgDate(Holiday[] holiday){
		int avg = 0;
		for(int i = 0; i < holiday.length; i++){
			avg += holiday[i].day;
		}

		return avg / holiday.length;

	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString(){
		return name + ": " + month + " " + day;
	}
}
