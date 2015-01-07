package basePlayerMine;

import java.util.HashMap;
import java.util.Map;

public class tester {

	public static void main(String[] args) {
		double x = 0.0;
		Map<Integer, Double> a = new HashMap<Integer, Double>();
		a.put(1, x);
		x+=3;
		a.put(2, x);
		x+=4;
		System.out.println(a.get(1));
		System.out.println(a.get(2));
		System.out.println(x);
	}

}
