package p1MainClasses;

import java.io.FileNotFoundException;
import experimentalClasses.ExperimentController;
import experimentalClasses.StrategiesTimeCollection;
import setIntersectionFinders.P1P2Strategy;
import setIntersectionFinders.P3Strategy;
import setIntersectionFinders.P4Strategy;

/**
 * Main class for determining strategies time efficiency.
 * @author pedroirivera-vega
 *
 */
public class Part2Main {

	private static int[] parms = {10, 50, 1000, 50000, 1000, 200};
	// companies, crime events, min size, max size, size increment, number of trials per size/strategy
	
	public static void main(String[] args) throws CloneNotSupportedException {
		if (args.length > 6)
			System.out.println("Unexpected number of parameters. Must me <= 6.");
		for (int i=0; i<args.length; i++)
			parms[i] = Integer.parseInt(args[i]); 
		
		// Parm1: number of companies
		// Parm2: number of crime events
		// Parm4: initial size
		// Parm4: final size to consider
		// Parm5: incremental steps (size)
		// Parm6: trials per size
		ExperimentController ec = new ExperimentController(parms[0], parms[1], parms[2], parms[3], parms[4], parms[5]); 
		
		/**/	
		ec.addStrategy(new StrategiesTimeCollection<Integer>(new P1P2Strategy<Integer>("P1", 1)));
		ec.addStrategy(new StrategiesTimeCollection<Integer>(new P1P2Strategy<Integer>("P2", 2)));
		ec.addStrategy(new StrategiesTimeCollection<Integer>(new P3Strategy<Integer>("P3")));
		ec.addStrategy(new StrategiesTimeCollection<Integer>(new P4Strategy<Integer>("P4")));
		/**/

		ec.run();    // run the experiments on all the strategies added to the controller object (ec)
		
		// save the results for each strategy....
		try {
			ec.saveResults();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	}

}
