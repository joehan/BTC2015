package tankRush;

import java.util.ArrayList;

public class BattleMap<S, T> {
	ArrayList<S> keys;
	ArrayList<T> values;
	
	public BattleMap() {
		keys = new ArrayList<S>();
		values = new ArrayList<T>();
	}
	
	public void put(S key, T value) {
		keys.add(key);
		values.add(value);
	}
	
	public T get(S key) {
		return values.get(keys.indexOf(key));
	}
	
	public int size() {
		return keys.size();
	}
	
	public T remove(S key) {
		int index = keys.indexOf(key);
		keys.remove(index);
		return values.remove(index);
	}
	
	public ArrayList<S> keys() {
		return keys;
	}
}
