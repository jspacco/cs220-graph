package graph.impl;

import graph.INode;

public class Edge implements Comparable<Edge> {
	
    INode s;
    INode d;
    int weight;
	
	public Edge(INode a, INode b, int w) {
		this.s = a;
		this.d = b;
		this.weight = w;
	}

	@Override
	public int compareTo(Edge e) {
		// TODO Auto-generated method stub
		return this.weight - e.weight;
	}
	
	
	
}
