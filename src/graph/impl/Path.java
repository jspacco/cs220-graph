package graph.impl;

public class Path implements Comparable<Path>{

	private String name;
	private int cost;

	public Path(String name, int cost) {
		this.name = name;
		this.cost = cost;
	}

	public int compareTo(Path o) {
		// TODO Auto-generated method stub
		return this.cost - o.cost;
	}
	
	public String nodename() {
		return this.name;
	}
	
	public int getCost() {
		return cost;
	}

}
