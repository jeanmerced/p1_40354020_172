package experimentalClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.AbstractMap;
import java.util.ArrayList;
import dataGenerator.DataGenerator;

/**
 * This class represents an object data type that is able to carry the 
 * necessary experiments to estimate execution times of particular 
 * strategies to solve the set intersections problem as in p1_40354020_172. 
 * 
 * @author pedroirivera-vega
 *
 */
public class ExperimentController{
	
	private int n;						// the number of companies  
	private int m;						// the number of crime events
	private int initialSize;				// initial size for experimentations
	private int finalSize;				// final size for experimentations
	private int repetitionsPerSize;		// number of repetitions for each size
	private int incrementalSizeStep;		// increment of sizes
	
	private ArrayList<StrategiesTimeCollection<Integer>> resultsPerStrategy; 
	// The i-th position will contain a particular strategy being tested. 
	// At the end, the i-th position will also contain a list of 
	// pairs (n, t), where t is the estimated time for size n for
	// the strategy at that position. 
	
	public ExperimentController(int n, int m, int isize, int fsize, int istep, int rep) { 
		this.n = n;
		this.m = m;
		initialSize = isize; 
		finalSize = fsize; 
		repetitionsPerSize = rep; 
		incrementalSizeStep = istep; 

		resultsPerStrategy = new ArrayList<>(); 
	}
	
	public void addStrategy(StrategiesTimeCollection<Integer> strategy) { 
		resultsPerStrategy.add(strategy); 
	}

	public void run() throws CloneNotSupportedException { 
		if (resultsPerStrategy.isEmpty())
			throw new IllegalStateException("No strategy has been added."); 
		for (int size=initialSize; size<=finalSize; size+=incrementalSizeStep) { 
			// For each strategy, reset the corresponding variable that will be used
			// to store the sum of times that the particular strategy exhibits for
			// the current size size
			for (StrategiesTimeCollection<Integer> strategy : resultsPerStrategy) 
				strategy.resetSum();  
			
			// Run all trials for the current size. 
			for (int r = 0; r<repetitionsPerSize; r++) {
				// The following will be the common dataSet to be used in the current 
				// trial by all the strategies being tested.
				Integer[][][] dataSet = generateData(n, m, size);  
				
				// Apply each one of the strategies being tested using the previous 
				// dataSet (of size size) as input; and, for each, estimate the time
				// that the execution takes. 
				for (StrategiesTimeCollection<Integer> strategy : resultsPerStrategy) {  
					// no need to clone the data set to be used by each strategy since
					// no modification of it is done in the process...
					long startTime = System.nanoTime();		// Measure system’s clock time before

					strategy.runTrial(dataSet);				// Run the strategy using the data in dataSet
					
					long endTime = System.nanoTime();		// Measure system’s clock time after

					int estimatedTime = (int) (endTime-startTime);   // The estimated time
					// accumulate the estimated time (add it) to sum of times that
					// the current strategy has exhibited on trials for dataSets
					// of the current size. 
					strategy.incSum(estimatedTime);    
					
				}
			}
			
			for (StrategiesTimeCollection<Integer> strategy : resultsPerStrategy) { 
				strategy.add(new AbstractMap.SimpleEntry<Integer, Float>
				(size, (strategy.getSum()/((float) repetitionsPerSize)))); 
			}

			System.out.println(size); 

		}
	}
	
	private Integer[][][] generateData(int n, int m, int size) {
		DataGenerator dg = new DataGenerator(n, m, size);
		Integer[][][] data = (Integer[][][]) dg.generateData();  
		return data;
	}

	public void saveResults() throws FileNotFoundException { 
		PrintStream out = new PrintStream(new File("experimentalResults", "allResults.txt"));
		out.print("Size");
		for (StrategiesTimeCollection<Integer> trc : resultsPerStrategy) 
			out.print("\t" + trc.getStrategyName()); 
		out.println();

		int numberOfExperiments = resultsPerStrategy.get(0).size(); 
		for (int i=0; i<numberOfExperiments; i++) {
			out.print(resultsPerStrategy.get(0).get(i).getKey());
			for (StrategiesTimeCollection<Integer> trc : resultsPerStrategy)
				out.print("\t" + trc.get(i).getValue());
			out.println(); 
		}
			
		out.close();
	}
}


