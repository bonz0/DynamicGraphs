package graph;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import graph.StableRegions;

public class DynamicGraph {
	int numberOfNodes;
	int numberOfTimeSlots;
	BitSet[][] adjacencyMatrix;
	
	public DynamicGraph(int numberOfNodes, int numberOfTimeSlots) {
		this.numberOfNodes = numberOfNodes;
		this.numberOfTimeSlots = numberOfTimeSlots;
		this.adjacencyMatrix = new BitSet[this.numberOfNodes][this.numberOfNodes];
		for(int iii = 0; iii < this.numberOfNodes; iii++) {
			for(int jjj = 0; jjj < this.numberOfNodes; jjj++) {
				this.adjacencyMatrix[iii][jjj] = new BitSet(this.numberOfTimeSlots);
			}
		}
	}
	
	public DynamicGraph(int[][] inputGraphArray, int numberOfNodes) {
		this(numberOfNodes, (inputGraphArray[inputGraphArray.length - 1][2] + 1));
		for(int iii = 0; iii < inputGraphArray.length; iii++) {
			this.setBitAt(inputGraphArray[iii][0], inputGraphArray[iii][1], inputGraphArray[iii][2]);
			this.setBitAt(inputGraphArray[iii][1], inputGraphArray[iii][0], inputGraphArray[iii][2]);
		}
	}
	
	public DynamicGraph(List<int[]> inputGraph, int numberOfNodes) {
		this(numberOfNodes, (inputGraph.get(inputGraph.size() - 1)[2] + 1));
		for(int iii = 0; iii < inputGraph.size(); iii++) {
			this.setBitAt(inputGraph.get(iii)[0], inputGraph.get(iii)[1], inputGraph.get(iii)[2]);
			this.setBitAt(inputGraph.get(iii)[1], inputGraph.get(iii)[0], inputGraph.get(iii)[2]);
		}
	}

	public DynamicGraph() {
		this(0, 0);
	}
	
	public void setBitAt(int row, int column, int time) {
		this.adjacencyMatrix[row][column].set(time);
	}
	
	public boolean getBitAt(int row, int column, int time) {
		return this.adjacencyMatrix[row][column].get(time);
	}
	
	public int getNumberOfTimeSlots() {
		return this.numberOfTimeSlots;
	}
	
	private List<Integer> getNeighborsAtTime(int currentPoint, int timeSlot) {
		List<Integer> neighbors = new ArrayList<Integer>();
		for(int iii = 0; iii < this.adjacencyMatrix.length; iii++) {
			if(this.adjacencyMatrix[currentPoint][iii].get(timeSlot)) {
				neighbors.add(iii);
			}
		}
		return neighbors;
	}
	
	private List<Region> getRegionsAtTime(int timeSlot) {
		boolean[] visited = new boolean[this.numberOfNodes];
		Queue<Integer> nodeQueue = new LinkedList<Integer>();
		List<Region> regions = new ArrayList<Region>();
		for(int iii = 0; iii < this.adjacencyMatrix.length; iii++) {
			if(!visited[iii]) {
				Region currentRegion = new Region(timeSlot);
				currentRegion.nodes.add(iii);
				visited[iii] = true;
				nodeQueue.add(iii);
				while(!nodeQueue.isEmpty()) {
					Integer currentPoint = nodeQueue.poll();
					visited[currentPoint] = true;
					List<Integer> neighbors = this.getNeighborsAtTime(currentPoint, timeSlot);
					for(Integer temp : neighbors) {
						if(!visited[temp]) {
							nodeQueue.add(temp);
							currentRegion.nodes.add(temp);
						}
					}
				}
				regions.add(currentRegion);
			}
		}
		return regions;
	}
	
	public List<List<Region>> getAllRegions() {
		List<List<Region>> timeRegions = new ArrayList<List<Region>>();
		for(int iii = 0; iii < this.getNumberOfTimeSlots(); iii++) {
			timeRegions.add(this.getRegionsAtTime(iii));
		}
		return timeRegions;
	}
	
	public void printEdges(String fileName) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(fileName));
			for(int iii = 0; iii < this.adjacencyMatrix.length; iii++) {
				for(int jjj = 0; jjj < this.adjacencyMatrix[iii].length; jjj++) {
					bw.write(this.adjacencyMatrix[iii][jjj].toString().replaceAll("[,{}]", "") + "\n");
				}
			}
		} catch (IOException e) {
			System.out.println("Unable to write to file: " + fileName + "!");
			e.printStackTrace();
			System.exit(-1);
		} finally {
			try {
				if(bw != null) bw.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<StableRegions> getStableRegions(double percentage, double similarity) {
		List<List<Region>> regions = this.getAllRegions();
		int minTimeSlots = (int) ((int) this.numberOfTimeSlots * percentage);
		ArrayList<StableRegions> allRegions = new ArrayList<StableRegions>();
		for(int iii = 0; iii < regions.size() - 1; iii++) {
			List<Region> timeList = regions.get(iii);
			for(int jjj = 0; jjj < timeList.size(); jjj++) {
				if(!timeList.get(jjj).visited) {
					StableRegions currentStableRegion = new StableRegions();
					timeList.get(jjj).visited = true;
					currentStableRegion.stableRegions.add(timeList.get(jjj));
					getNextSimilarRegion(currentStableRegion, regions, iii + 1, similarity);
					if(currentStableRegion.stableRegions.size() >= minTimeSlots) allRegions.add(currentStableRegion);
				}
			}
		}
		return allRegions;
	}
	
	private static void getNextSimilarRegion(StableRegions currentRegions, List<List<Region>> list, int index, double similarity) {
		for(int iii = index; iii < list.size(); iii++) {
			Region thisRegion = currentRegions.stableRegions.peek();
			double maxSimilarity = Double.MIN_VALUE;
			int maxIndex = 0;
			List<Region> currentList = list.get(iii);
			for(int jjj = 0; jjj < currentList.size(); jjj++) {
				if(!currentList.get(jjj).visited) {
					double thisSimilarity = thisRegion.getSimilarity(currentList.get(iii));
					maxIndex = (thisSimilarity >= maxSimilarity) ? jjj : maxIndex;
					maxSimilarity = (thisSimilarity >= maxSimilarity) ? thisSimilarity : maxSimilarity;
				}
			}
			if(maxSimilarity >= similarity) {
				currentRegions.stableRegions.add(list.get(iii).get(maxIndex));
				list.get(iii).get(maxIndex).visited = true;
			} else {
				break;
			}
		}
	}
	
	private static Region getNextSimilarRegion(Region currentRegion, List<Region> nextList, double similarity) {
		double maxSimilarity = Double.MIN_VALUE;
		int maxIndex = 0;
		for(int iii = 0; iii < nextList.size(); iii++) {
			if(!nextList.get(iii).visited) {
				double thisSimilarity = currentRegion.getSimilarity(nextList.get(iii));
				maxIndex = (thisSimilarity >= maxSimilarity) ? iii : maxIndex;
				maxSimilarity = (thisSimilarity >= maxSimilarity) ? thisSimilarity : maxSimilarity;
			}
		}
		return (maxSimilarity >= similarity) ? nextList.get(maxIndex) : null;
	}
	
	@Override
	public String toString() {
		String returnString = "";
		for(int iii = 0; iii < this.numberOfNodes; iii++) {
			for(int jjj = 0 ; jjj < this.numberOfNodes; jjj++) {
				returnString += this.adjacencyMatrix[iii][jjj].toString() + "\t";
			}
			returnString += "\n";
		}
		return returnString;
	}
}
