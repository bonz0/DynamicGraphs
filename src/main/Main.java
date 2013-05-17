package main;

import graph.*;

import utils.Utils;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final String fileName = "/home/farhang/Downloads/pruned_datasets/dblp.txt.sorted.processed";
//		final String fileName = "/home/farhang/Downloads/pruned_datasets/test.graph";
		// TODO Auto-generated method stub
		String inputFile = Utils.readInputFile(fileName);
		int[][] fileArray = Utils.getIntArray(inputFile);
		DynamicGraph myGraph = new DynamicGraph(fileArray, Utils.getNumberOfNodes(fileArray));
	}
}