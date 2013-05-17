package main;

import java.util.List;

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
		List<List<Region>> regions = myGraph.getAllRegions();
		for(List<Region> tempList : regions) {
			for(Region tempRegion : tempList) {
				System.out.println(tempRegion + "\t");
			}
			System.out.println("\n");
		}
	}
}