package Assignment4.PartII.arraylists;
import java.util.ArrayList;
import java.util.Objects;

import Assignment4.PartI.fruit.*;

public class FruitArraylist {




	public static void main(String[] args) {

		
		// this ArrayList MUST be parameterized
		ArrayList<Fruit> fruitArrayList = new ArrayList<>();

		fruitArrayList.add(new Apple("Sweet", "Crisp", "Red", false));
		fruitArrayList.add(new Apple("Tart", "Soft", "Green", true));
		fruitArrayList.add(new Lemon("Sour", false, (int)(Math.random() * 100 )));
		fruitArrayList.add(new Lemon("Sour", false, (int)(Math.random() * 100 )));
		fruitArrayList.add(new Lemon("Sour", false, (int)(Math.random() * 100 )));
		fruitArrayList.add(new Orange("Sweet", true, "Mandarin"));
		fruitArrayList.add(new Orange("Sweet", true, "Mandarin"));
		fruitArrayList.add(new Apple("Tart", "Soft", "Green", true));


		double avgSour = 0;
		// this is the variable you should retain to compare

		for(Fruit f : fruitArrayList){
			if(f instanceof Lemon){
				System.out.println("The sourness is " + ((Lemon) f).getSourness());
				avgSour +=((Lemon) f).getSourness() / 3;
			}
		}
		System.out.println("The average sourness is " + avgSour);



		// to the other objects in the arraylist
		Apple matchApple = (Apple)fruitArrayList.get(1);

		for(Fruit f : fruitArrayList){
			if (f == matchApple) {
				System.out.println("The same object Apple is " + f.getId());
				System.out.println(f.toString());
			}
		}

		for(int i = 0; i < fruitArrayList.size(); i++){
			if(fruitArrayList.get(i).getColor() == matchApple.getColor()){
				fruitArrayList.remove(i);
			}
		}


		//after remove
		System.out.println("The remaining fruit are");
		for(Fruit f : fruitArrayList){
			System.out.println(f.toString());
		}


		//Apple rottenGreenApple1;
	}
}
