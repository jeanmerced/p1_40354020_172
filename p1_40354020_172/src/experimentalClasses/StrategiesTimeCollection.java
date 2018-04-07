package experimentalClasses;

import interfaces.IntersectionFinder;
import interfaces.MySet;
import mySetImplementations.Set1;
import mySetImplementations.Set2;

import java.util.ArrayList;
import java.util.Map;


/**
 * An object of this type will contain the results of random experiments
 * to estimate the average execution time per size of a particular strategy. 
 * It also stores the partial sum of the times that the particular strategy
 * has taken during the experimental trials. 
 * 
 * An object of this type will embed a particular strategy. When that particular
 * strategy is executed from an ExperimentController object, this object will 
 * contain the computed average execution times for each input size that it
 * has experimented with. 
 * 
 * Notice that this is implemented as a subclass of 
 * ArrayList<Map.Entry<Integer, Float>>
 * 
 * @author pedroirivera-vega
 *
 */
public class StrategiesTimeCollection<E> extends ArrayList<Map.Entry<Integer, Float>> { 
    
	private IntersectionFinder<Integer> strategy;    // the strategy
    private float sum;   
    // variable to accumulate the sum of times that different
    // executions for the same time take. It is eventually used
    // to determine the average execution time for a particular 
    // size.....
    
    public StrategiesTimeCollection(IntersectionFinder<Integer> strategy) { 
        this.strategy = strategy; 
    } 
    
    public String getStrategyName() { 
        return strategy.getName(); 
    }
    
    public void runTrial(Integer[][][] dataSet) throws CloneNotSupportedException { 
    		MySet<Integer>[] setUnion = (MySet<Integer>[]) new MySet[dataSet[0].length];
    		
    		for(int j = 0; j < dataSet[0].length; j++) {
    			if(strategy.getName() == "P1")
    				setUnion[j] = new Set1<Integer>();
    			else
    				setUnion[j] = new Set2<Integer>();
			for(int i = 0; i < dataSet.length; i++)
				for(int k = 0; k < dataSet[i][j].length; k++) 
					setUnion[j].add(dataSet[i][j][k]);
    		}
    		strategy.intersectSets(setUnion);
    }
    
    public void resetSum() { 
    		sum = 0.0f; 
    }
    
    public void incSum(float t) { 
    		sum += t; 
    }
    
    public float getSum() { 
    		return sum; 
    }
}