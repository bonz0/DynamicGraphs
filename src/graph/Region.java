package graph;

import java.util.ArrayList;
import java.util.List;

public class Region {
	public static int currentRegion = -1;
	
	
	public List<Integer> nodes;
	public int regionNumber;
	
	public Region() {
		this.nodes = new ArrayList<Integer>();
		currentRegion++;
		this.regionNumber = currentRegion;
	}
	
	@Override
	public String toString() {
		String returnString = "{";
		for(int iii = 0; iii < this.nodes.size(); iii++) {
			returnString += Integer.toString((nodes.get(iii)));
			if(iii != this.nodes.size() - 1) {
				returnString += ", ";
			}
		}
		returnString += "}";
		return returnString;
	}
}
