package main;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import graph.*;
import utils.Utils;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		final String inputFileName = "/home/farhang/Downloads/pruned_datasets/dblp.txt.sorted.processed.new";
		final String outputFileName = "/home/farhang/Downloads/pruned_datasets/dblp.regions1";
		ArrayList<int[]> inputGraph = Utils.readParseInputFile(inputFileName);
		System.out.println("Input file: " + inputFileName + " read!");
		System.out.println("File parsed!");
		System.gc();
		DynamicGraph myGraph = new DynamicGraph(inputGraph, Utils.getNumberOfNodes(inputGraph));
		System.out.println("Graph populated");
//		List<List<Region>> regions = myGraph.getAllRegions();
		List<StableRegions> regions = myGraph.getStableRegions(0.1, 0.3);
		System.out.println("Done!!!!!! " + regions.size());
		for(StableRegions temp: regions) {
			System.out.println(temp.toString());
		}
		System.out.println("All regions calculated");
//		Utils.writeOutputFile(outputFileName, regions);
		System.out.println("Output written to file: " + outputFileName + "!");
		long endTime = System.currentTimeMillis();
		System.out.println("Time taken: " + (endTime - startTime)/1000 + " seconds.");
	}
}