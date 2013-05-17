package graph;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

	public DynamicGraph() {
		this(0, 0);
	}
	
	public void setBitAt(int row, int column, int time) {
		this.adjacencyMatrix[row][column].set(time);
	}
	
	public boolean getBitAt(int row, int column, int time) {
		return this.adjacencyMatrix[row][column].get(time);
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
	
	public List<Region> getRegionsAtTime(int timeSlot) {
		boolean[] visited = new boolean[this.numberOfNodes];
		Queue<Integer> nodeQueue = new LinkedList<Integer>();
		List<Region> regions = new ArrayList<Region>();
		for(int iii = 0; iii < this.adjacencyMatrix.length; iii++) {
			if(!visited[iii]) {
				Region currentRegion = new Region();
				currentRegion.nodes.add(iii);
				visited[iii] = true;
				nodeQueue.add(iii);
				while(!nodeQueue.isEmpty()) {
					Integer currentPoint = nodeQueue.poll();
					List<Integer> neighbors = this.getNeighborsAtTime(currentPoint, timeSlot);
					nodeQueue.addAll(neighbors);
					currentRegion.nodes.addAll(neighbors);
				}
				regions.add(currentRegion);
			}
		}
		return regions;
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
