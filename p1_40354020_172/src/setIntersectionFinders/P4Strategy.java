package setIntersectionFinders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import interfaces.MySet;
import mySetImplementations.Set2;

/**
 * This class represents an object for intersection finder implementing strategies P4
 * @author jeanmerced
 */

public class P4Strategy<E> extends AbstractIntersectionFinder<E> {

	private ArrayList<E> allElements;
	
	public P4Strategy(String name) {
		super(name);
	}

	/** To intersect an array of sets
	 * @param t array of sets representing the union of sets
	 * @return set representing the intersection of the sets given
	 */
	@Override
	public MySet<E> intersectSets(MySet<E>[] t) {
		allElements = fillAllElements(t);
		HashMap<E, Integer> map = new HashMap<>(); 
		
		// Adds the elements to a HashMap along with their count
		// of repetitions in allElements. If the element's count 
		// is equal to m, then it is added to T. The final T is
		// the intersection of the sets.
		for (E e : allElements) { 
		     Integer c = map.getOrDefault(e, 0); 
		     map.put(e, c+1); 
		}
		MySet<E> T = new Set2<>(); 
		for (Map.Entry<E, Integer> entry : map.entrySet())
		     if (entry.getValue() == t.length) // t.length == m
		        T.add(entry.getKey()); 
		return T;
	}
	
	private ArrayList<E> fillAllElements(MySet<E>[] t) {
		allElements = new ArrayList<>();
		for(int i = 0; i < t.length; i++) {
			Set2<E> s = (Set2<E>) t[i];
			for(E e : s) {
				allElements.add(e);
			}
		}
		return allElements;
	}
}
