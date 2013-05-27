package graph;

import java.util.LinkedList;
import java.util.Queue;

public class StableRegions {
	public Queue<Region> stableRegions;
	
	public StableRegions() {
		this.stableRegions = new LinkedList<Region>();
	}
	
	@Override
	public String toString() {
		StringBuilder returnString = new StringBuilder();
		for(Region tempRegion: this.stableRegions) {
			returnString.append(tempRegion.toString() + "\n");
		}
		return returnString.toString();
	}
}