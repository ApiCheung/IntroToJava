package edu.nyu.cs9053.midterm.hierarchy;

public abstract class WinterSportPlayer {
	private String name;
	private int age;

	public WinterSportPlayer(String name, int age){
		this.name = name;
		this.age = age;
	}

	public String getName() {
		
		return name;
	}
	
	public int getAge() {
		
		return age;
	}

	public boolean equals(WinterSportPlayer o){
		if(this.age == o.age && this.name == o.name){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public String toString() {
		return "WinterSportPlayer{" +
				"name='" + name + '\'' +
				", age=" + age +
				'}';
	}
}
