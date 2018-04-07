package setIntersectionFinders;

import java.util.ArrayList;

import interfaces.MySet;
import mySetImplementations.Set2;

/**
 * This class represents an object for intersection finder implementing strategies P3
 * @author jeanmerced
 */

public class P3Strategy<E> extends AbstractIntersectionFinder<E> {
	
	private ArrayList<E> allElements;
	
	public P3Strategy(String name) {
		super(name);
	}

	/** To intersect an array of sets
	 * @param t array of sets representing the union of sets
	 * @return set representing the intersection of the sets given
	 */
	@Override
	public MySet<E> intersectSets(MySet<E>[] t) {
		allElements = fillAllElements(t); 
		allElements.sort(null); 		
		MySet<E> T = new Set2<E>();
		E e = allElements.get(0); 
		Integer c = 1;
		
		// Counts if the elements is repeated m times in the
		// array list allElements. If yes, then that elements
		// is added in set T. The final T is the intersection
		// of the sets.
		for(int i=1; i < allElements.size(); i++) {
		    if (allElements.get(i).equals(e)) 
		       c++;
		    else { 
		       if (c == t.length) // t.length == m
		          T.add(e);    
		       e = allElements.get(i); 
		       c = 1; 
		    } 
		}
		if (c == t.length)
		    T.add(allElements.get(allElements.size()-1));
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
