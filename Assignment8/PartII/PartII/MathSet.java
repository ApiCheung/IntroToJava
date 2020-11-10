package Assignment8.PartII.PartII;

import java.util.HashSet;
import java.util.Set;

public class MathSet<E> extends HashSet<E> {

	public Set<E> intersection(Set<E> s2) {
		Set<E> res = new HashSet<>();
		for(E e2 : s2){
			for(E e1 : this){
				if(e1.equals(e2)) res.add(e2);
			}
		}
		return res;
		
	}
	
	public Set<E> union(Set<E> s2) {
		Set<E> res = new HashSet<>();
		res.addAll(this);
		for(E e2 : s2){
			if(!res.contains(e2)){
				res.add(e2);
			}else{
				continue;
			}
		}
		return res;
	}
	
	public <T> Set<Pair<E,T>> cartesianProduct(Set<T> s2) {
		Set<Pair<E, T>> res = new HashSet<>();
		for(E e : this){
			for(T t : s2){
				res.add(new Pair<>(e, t));
			}

		}
		return res;
	}
	
	public static void main(String[] args) {
		// create two MathSet objects of the same type.
		// See if union and intersection works.
		

		MathSet<Integer> s1 = new MathSet<>();
		s1.add(1);
		s1.add(5);
		s1.add(8);
		s1.add(10);
		MathSet<Integer> s2 = new MathSet<>();
		s2.add(5);
		s2.add(2);
		s2.add(8);
		s2.add(7);

		System.out.println("the intersection is " + s1.intersection(s2));
		System.out.println("the union is " + s1.union(s2));

		// create another MathSet object of a different type
		// calculate the cartesian product of this set with one of the
		// above sets

		MathSet<Double> s3 = new MathSet<>();
		s3.add(2.4);
		s3.add(1.2);
		s3.add(9.3);
		s3.add(5.2);

		System.out.println("the product is " + s1.cartesianProduct(s3));



	}
}
