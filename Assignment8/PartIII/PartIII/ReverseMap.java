package Assignment8.PartIII.PartIII;

import java.util.*;
import java.util.Map.Entry;

public class ReverseMap {

	public static Map<Object, Set<Object>> getInverted(Map<Object, Object> mp) {
		Map<Object, Set<Object>> res = new HashMap<>();
		Collection<Object> val = mp.values();
		for(Object o : val){
			res.put(o, new HashSet<>());
		}

		Set<Map.Entry<Object, Object>> entrySet = mp.entrySet();
		for(Map.Entry e : entrySet){
			res.get(e.getValue()).add(e.getKey());
		}
		return res;

	}
	public static void main(String[] args) {
		Map<Object,Object> m = new HashMap<Object,Object>();
		m.put("Key1", new Integer(2));
		m.put("Key2", new Integer(5));
		m.put("Key4", new Integer(2));
		m.put("Key5", new Integer(8));
		m.put("Key6", new Integer(18));
		m.put("Key7", new Integer(24));
		System.out.println("hashmap is " + m);
		
		System.out.println("inverted: " + ReverseMap.getInverted(m));
		

	}

}
