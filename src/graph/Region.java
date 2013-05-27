package graph;

import java.util.HashSet;
import java.util.Iterator;

public class Region {
	public static int currentRegion = -1;
	
	public HashSet<Integer> nodes;
	public int regionNumber;
	public int time;
	public boolean visited;
	
	public Region(int time) {
		this.nodes = new HashSet<Integer>();
		this.time = time;
		currentRegion++;
		this.regionNumber = currentRegion;
		this.visited = false;
	}
	
	public Region() {
		this(0);
	}
	
	public double getSimilarity(Region compRegion) {
		int intersectionCount = 0;
		Iterator<Integer> iter = this.nodes.iterator();
		while(iter.hasNext()) {
			Integer current = iter.next();
			if(compRegion.nodes.contains(current)) intersectionCount++;
		}
		HashSet<Integer> union = new HashSet<Integer>();
		union.addAll(this.nodes);
		union.addAll(compRegion.nodes);
		return ((double)intersectionCount)/(double)(union.size());
	}
	
	@Override
	public String toString() {
		String returnString = this.time +  " -> {";
		Iterator<Integer> iter = this.nodes.iterator();
		while(iter.hasNext()) {
			returnString += iter.next().toString();
			if(iter.hasNext()) returnString += ", ";
		}
		returnString += "}";
		return returnString;
	}
}
