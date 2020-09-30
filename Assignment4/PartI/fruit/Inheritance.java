package Assignment4.PartI.fruit;

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

		Fruit fruit = new Fruit();
		Citris citris = new Citris();

		System.out.println(fruit.equals(apple));
		System.out.println(apple.equals(fruit));
		System.out.println(apple.equals(lemon));
		System.out.println(lemonWithArg.equals(appleWithArg));
		System.out.println(citris.equals(apple));
		System.out.println(apple.equals(citris));


	}

}
