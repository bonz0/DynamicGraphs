package graph;

import java.util.BitSet;

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
