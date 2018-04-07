package testerClasses;

import java.io.FileNotFoundException;

import dataGenerator.DataGenerator;
import interfaces.IntersectionFinder;
import interfaces.MySet;
import mySetImplementations.Set1;
import mySetImplementations.Set2;
import setIntersectionFinders.P1P2Strategy;
import setIntersectionFinders.P3Strategy;
import setIntersectionFinders.P4Strategy;

/**
 * This class is meant for testing the four strategies: P1, P2, P3 and P4.
 * @author jeanmerced
 */

public class StrategyTester {
	
	private static MySet<Integer>[] t;
	private static Integer[][][] dataSet;
	
	public static void main(String[] args) throws FileNotFoundException, CloneNotSupportedException {
		
		int str = 4; //1,2,3,4
		t = constructSets(str);
		printDataSet();
		printSetUnion();
		
		//IntersectionFinder<Integer> strategy = new P1P2Strategy<Integer>("P1", 1);
		//IntersectionFinder<Integer> strategy = new P1P2Strategy<Integer>("P2", 2);
		//IntersectionFinder<Integer> strategy = new P3Strategy<Integer>("P3");
		IntersectionFinder<Integer> strategy = new P4Strategy("P4");
		
		//Set1<Integer> T = (Set1<Integer>) strategy.intersectSets(sets);
		Set2<Integer> T = (Set2<Integer>) strategy.intersectSets(t);
		
		System.out.println("\nTesting: " + strategy.getName());
		System.out.println("Final Set by " + strategy.getName() + ": " + T);
	}
	
	private static Object[][][] readFiles() {
		DataGenerator dg = new DataGenerator(10, 10, 50);
		return dg.generateData();
	}
	private static MySet<Integer>[] constructSets(int str) {
		dataSet = (Integer[][][]) readFiles();
		MySet<Integer>[] setUnion = (MySet<Integer>[]) new MySet[dataSet[0].length];
		for(int j = 0; j < dataSet[0].length; j++) {
			if(str == 1)
				setUnion[j] = new Set1<Integer>();
			else
				setUnion[j] = new Set2<Integer>();
			for(int i = 0; i < dataSet.length; i++) 
				for(int k = 0; k < dataSet[i][j].length; k++) 
					setUnion[j].add(dataSet[i][j][k]);
		}
		return setUnion;
	}
	private static void printSetUnion() {
		System.out.println("\nPrinting set unions:");
		for(int i = 0; i < t.length; i++) {
			System.out.println(t[i]);
		}
	}
	
	private static void printDataSet() {
		System.out.println("\nPrinting data set:");
		for(int i = 0; i < dataSet.length; i++) {
			for(int j = 0; j < dataSet[0].length; j++) {
				System.out.print("F_" + i + "_" + j + " = [");
				for (int k = 0; k < dataSet[i][j].length; k++) 
					System.out.print(" " + dataSet[i][j][k]);
				System.out.println(" ]");
			}
		}
	}
}
