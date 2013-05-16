package main;

import graph.DynamicGraph;

import java.util.Arrays;
import utils.Utils;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String fileName = "/home/farhang/Downloads/pruned_datasets/dblp.txt.sorted.processed";
		// TODO Auto-generated method stub
		String inputFile = Utils.readInputFile(fileName);
		int[][] fileArray = Utils.getIntArray(inputFile);
		DynamicGraph myGraph = new DynamicGraph(fileArray, Utils.getNumberOfNodes(fileArray));
		System.out.println(myGraph.getBitAt(2, 3, 0));
		System.out.println("Done!");
	}

}
