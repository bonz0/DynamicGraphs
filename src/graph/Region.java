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
}
