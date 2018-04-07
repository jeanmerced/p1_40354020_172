package setIntersectionFinders;

import interfaces.MySet;
import mySetImplementations.Set1;
import mySetImplementations.Set2;

/**
 * This class represents an object for intersection finder implementing strategies P1 and P2
 * @author jeanmerced
 */

public class P1P2Strategy<E> extends AbstractIntersectionFinder<E> {
	
	private int strategy;

	public P1P2Strategy(String name, int strategy) {
		super(name);
		this.strategy = strategy;
	}
	/** To intersect an array of sets
	 * @param t array of sets representing the union of sets
	 * @return set representing the intersection of the sets given
	 */
	@Override
	public MySet<E> intersectSets(MySet<E>[] t) throws CloneNotSupportedException {
		MySet<E> T;
		MySet<E> tClone;
		
		// determines which type of set to use according to strategy
		if(strategy == 1) {
			T = (Set1<E>) t[0];
			tClone = (Set1<E>) T.clone();
		}
		else {
			T = (Set2<E>) t[0];
			tClone = (Set2<E>) T.clone();
		}
		
		// fills set T with t[0] elements. For each element of t's sets, 
		// if any of T's elements is not contained in that particular set
		// then the element is removed from T. What remains of T is the
		// intersection of the sets.
		for(int j = 1; j < t.length; j++) 
			for(E e : tClone) 
				if(!(t[j].contains(e)))
					T.remove(e);
		return T;
	}
}
