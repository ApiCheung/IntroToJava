package Assignment4.PartI.fruit;

import Assignment5.PartI.shapes.GeometricObject;

public class Inheritance {

	public static void main(String[] args) {
		// Here's some scratch space to experiment/debug your Fruit objects

		

		Apple apple = new Apple();
		Apple appleWithArg = new Apple("Sweet", "Hard", "Red",true);

		Orange orange = new Orange();
		Orange orangeWithArg = new Orange("Sweet", false, "round");

		Lemon lemon = new Lemon();
		Lemon lemonWithArg = new Lemon("Sour", true, 5);

		System.out.println(apple.toString());
		System.out.println(appleWithArg.toString());
		System.out.println(orange.toString());
		System.out.println(orangeWithArg.toString());
		System.out.println(lemon.toString());
		System.out.println(lemonWithArg.toString());

		apple.setTaste("Sour");
		appleWithArg.setTexture("Soft");
		appleWithArg.setRotten(false);

		orange.setType("Navel");
		orangeWithArg.setType("Navel");
		orangeWithArg.setRotten(true);

		lemon.setSourness(10);
		lemonWithArg.setColor("green");
		lemonWithArg.setSourness(2);

		System.out.println(apple.toString());
		System.out.println(appleWithArg.toString());
		System.out.println(orange.toString());
		System.out.println(orangeWithArg.toString());
		System.out.println(lemon.toString());
		System.out.println(lemonWithArg.toString());

		Apple apple1 = new Apple("Sweet", "Crisp", "yellow", false);
		Lemon lemon1 = new Lemon("Sour", false, 9);
		Orange orange1= new Orange("Sweet", false, "round");


		//same  from Citris and  same vairable
		System.out.println(lemon1.equals(apple1));
		//same super class and same vairable of fruit
		System.out.println(apple1.equals(lemon1));
		//different variable same superclasses
		System.out.println(orange1.equals(appleWithArg));



	}

}
