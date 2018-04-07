package p1MainClasses;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import dataGenerator.DataReader;
import interfaces.IntersectionFinder;
import interfaces.MySet;
import mySetImplementations.Set1;
import mySetImplementations.Set2;
import setIntersectionFinders.P1P2Strategy;
import setIntersectionFinders.P3Strategy;
import setIntersectionFinders.P4Strategy;

/**
 * Main class of the project. Executes the four strategies and prints their corresponding outputs.
 * @author jeanmerced
 */
public class Part1Main<E> {

	private static IntersectionFinder<Integer> str;
	
	public static void main(String[] args) throws FileNotFoundException, CloneNotSupportedException {	
		
		int strategy = 0; //default
		if(args.length <= 1) {
			if(args.length == 1) 
				if(Integer.parseInt(args[0]) > 0 && Integer.parseInt(args[0]) <= 4)
					strategy = Integer.parseInt(args[0]);
				else
					System.out.println("Invalid strategy number. Must be 0 < input <= 4.");
					
			MySet<Integer>[] t;
			switch(strategy) {
				case 1:
					t = constructSets(1);
					Set1<Integer> T1 = (Set1<Integer>) finalSet(t, 1);
					showResults(T1);
					break;
				case 2: 
					t = constructSets(2);
					Set2<Integer> T2 = (Set2<Integer>) finalSet(t, 2);
					showResults(T2);
					break;
				case 3:
					t = constructSets(3);
					Set2<Integer> T3 = (Set2<Integer>) finalSet(t, 3);
					showResults(T3);
					break;
				case 4:
					t = constructSets(4);
					Set2<Integer> T4 = (Set2<Integer>) finalSet(t, 4);
					showResults(T4);
					break;
				default:
					t = constructSets(1);
					Set1<Integer> set1T = (Set1<Integer>) finalSet(t, 1);
					showResults(set1T);
					t = constructSets(2);
					Set2<Integer> set2T = (Set2<Integer>) finalSet(t, 2);
					showResults(set2T);
					t = constructSets(3);
					set2T = (Set2<Integer>) finalSet(t, 3);
					showResults(set2T);
					t = constructSets(4);
					set2T = (Set2<Integer>) finalSet(t, 4);
					showResults(set2T);
					break;
			}	
		}
		else
			System.out.println("Invalid number of parameters. Must be <= 1.");
	}
	
	private static Object[][][] readFiles() throws FileNotFoundException {
		DataReader dr = new DataReader();
		Object[][][] dataSet = dr.readDataFiles();
		return dataSet;
	}
	
	private static MySet<Integer>[] constructSets(int strategy) throws FileNotFoundException {
		Integer[][][] dataSet = (Integer[][][]) readFiles();		
		MySet<Integer>[] setUnion = (MySet<Integer>[]) new MySet[dataSet[0].length];

		for(int j = 0; j < dataSet[0].length; j++) {
			if(strategy == 1)
				setUnion[j] = new Set1<Integer>();
			else
				setUnion[j] = new Set2<Integer>();
			for(int i = 0; i < dataSet.length; i++) 
				for(int k = 0; k < dataSet[i][j].length; k++) 
					setUnion[j].add(dataSet[i][j][k]);
		}
		return setUnion;
	}

	private static MySet<Integer> finalSet(MySet<Integer>[] t, int strategy) throws CloneNotSupportedException {
		
		switch(strategy) {
			case 1:
				str = new P1P2Strategy<>("P1", 1);
				break;
			case 2:
				str = new P1P2Strategy<>("P2", 2);
				break;
			case 3:
				str = new P3Strategy<>("P3");
				break;
			default:
				str = new P4Strategy<>("P4");
				break;
		}
		return str.intersectSets(t);
	}
	
	private static void showResults(MySet<Integer> T) {
		System.out.println("Final Set by " + str.getName() + ": " + T);
	}
}	