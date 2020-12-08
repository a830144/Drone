package util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class Graph
{
	
	public int getV() {
		return V;
	}

	private final int V;
	Map<Integer,Set> adj = new HashMap<Integer,Set>();

	// Constructor
	Graph(int V) {
		this.V = V;
		for(int i=1;i<= V;i++){
			adj.put(i, new HashSet());
		}
	}
	
	public void addEdge(int v,int w){
		adj.get(v).add(w);
		adj.get(w).add(v);
	}
	
	public Iterator adj(int v){
		return adj.get(v).iterator();
	}
}
