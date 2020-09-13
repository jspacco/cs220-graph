package graph.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import graph.IGraph;
import graph.INode;
import graph.NodeVisitor;

/**
 * A basic representation of a graph that can perform BFS, DFS, Dijkstra,
 * and Prim-Jarnik's algorithm for a minimum spanning tree.
 * 
 * @author jspacco
 *
 */
public class Graph implements IGraph
{
    
//    public class Path {
//
//		public Path(String startName, int i) {
//			// TODO Auto-generated constructor stub
//		}
//
//		public static Object nodename() {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		public int cost() {
//			// TODO Auto-generated method stub
//			return 0;
//		}



	/**
     * Return the {@link Node} with the given name.
     * 
     * If no {@link Node} with the given name exists, create
     * a new node with the given name and return it. Subsequent
     * calls to this method with the same name should
     * then return the node just created.
     * 
     * @param name
     * @return
     */
	public Map<String,INode> nodes = new HashMap<String,INode>();

	
    public INode getOrCreateNode(String name) {
    	if(nodes.containsKey(name)) {
    		return nodes.get(name);
    	}
    	INode obj = new Node(name);
    	nodes.put(name, obj);
    	return nodes.get(name);
        //throw new UnsupportedOperationException("Implement this method");
    }

    /**
     * Return true if the graph contains a node with the given name,
     * and false otherwise.
     * 
     * @param name
     * @return
     */
    public boolean containsNode(String name) {
    	if(nodes.containsKey(name))
    		return true;
    	return false;
        //throw new UnsupportedOperationException("Implement this method");
    }

    /**
     * Return a collection of all of the nodes in the graph.
     * 
     * @return
     */
    public Collection<INode> getAllNodes() {
    	return nodes.values();
        //throw new UnsupportedOperationException("Implement this method");
    }
    
    /**
     * Perform a breadth-first search on the graph, starting at the node
     * with the given name. The visit method of the {@link NodeVisitor} should
     * be called on each node the first time we visit the node.
     * 
     * 
     * @param startNodeName
     * @param v
     */
    public void breadthFirstSearch(String startNodeName, NodeVisitor v)
    {
    	Set<INode> visited = new HashSet();
    	Queue<INode> tsd = new LinkedList();
    	tsd.add(nodes.get(startNodeName));
    	while(!tsd.isEmpty()) {
    		INode n = tsd.poll();
    		if(visited.contains(n))
    			continue;
    		v.visit(n);
    		visited.add(n);
    		Collection<INode> neighbours = n.getNeighbors();
    		for(INode neigh: neighbours) {
    			if(!visited.contains(neigh))
    				tsd.add(neigh);
    		}
    	}
        //throw new UnsupportedOperationException("Implement this method");
    }

    /**
     * Perform a depth-first search on the graph, starting at the node
     * with the given name. The visit method of the {@link NodeVisitor} should
     * be called on each node the first time we visit the node.
     * 
     * 
     * @param startNodeName
     * @param v
     */
    public void depthFirstSearch(String startNodeName, NodeVisitor v)
    {
    	Set<INode> visited = new HashSet();
    	Stack<INode> tsd = new Stack();
    	tsd.add(nodes.get(startNodeName));
    	while(!tsd.isEmpty()) {
    		INode n = tsd.pop();
    		if(visited.contains(n))
    			continue;
    		v.visit(n);
    		visited.add(n);
    		Collection<INode> neighbours = n.getNeighbors();
    		for(INode neigh: neighbours) {
    			if(!visited.contains(neigh))
    				tsd.add(neigh);
    		}
    	}
        //throw new UnsupportedOperationException("Implement this method");
    }

    /**
     * Perform Dijkstra's algorithm for computing the cost of the shortest path
     * to every node in the graph starting at the node with the given name.
     * Return a mapping from every node in the graph to the total minimum cost of reaching
     * that node from the given start node.
     * 
     * <b>Hint:</b> Creating a helper class called Path, which stores a destination
     * (String) and a cost (Integer), and making it implement Comparable, can be
     * helpful. Well, either than or repeated linear scans.
     * 
     * @param startName
     * @return
     */
    
//    //public class Path implements Comparable<Path>{
//
//    	private String name;
//		private int cost;
//
//		public Path(String name, int cost){
//    		this.name=name;
//    		this.cost=cost;
//    	}
//    	
//		@Override
//		public int compareTo(Path o) {
//			// TODO Auto-generated method stub
//			return this.cost-o.cost;
//		}
//	
//    	
//    }
    
//    public int numofnodes(String startNodeName)
//    {
//    	Set<INode> visited = new HashSet();
//    	Stack<INode> tsd = new Stack();
//    	tsd.add(nodes.get(startNodeName));
//    	while(!tsd.isEmpty()) {
//    		INode n = tsd.pop();
//    		if(visited.contains(n))
//    			continue;
//    		visited.add(n);
//    		Collection<INode> neighbours = n.getNeighbors();
//    		for(INode neigh: neighbours) {
//    			if(!visited.contains(neigh))
//    				tsd.add(neigh);
//    		}
//    	}
//    	return visited.size();
//        //throw new UnsupportedOperationException("Implement this method");
//    }
    
    //create a path class that implements a comparable of path (it can compare a path to another path)
    public Map<INode,Integer> dijkstra(String startName) {//use priority queue instead of queue
        Map<INode,Integer> dnodes = new HashMap<INode,Integer>();
        PriorityQueue<Path> pQueue = new PriorityQueue<Path>();
        pQueue.add(new Path(startName,0));//creates new path between the startnode and itself so weight is 0
        while(dnodes.size()< nodes.size()) {//while map we are putting paths into still has nodes to add
        	Path nextpath = pQueue.poll();//get shortest path out of queue
        	INode name = nodes.get(nextpath.nodename());//getting the destination node from the shortest path by using its name
        	if (dnodes.containsKey(name))//if already have that path, continue to next path
        		continue;
        	int cost = nextpath.getCost();//use getCost() method of Path class to add the path to the new map with that cost 
        	dnodes.put(name, cost);//add the path into map with the cost
    		for(INode neigh: nodes.get(name.getName()).getNeighbors()) {//go through all neighbors of current destination node, getting neighbors by accessing current destination node's neighbors in original map
    			pQueue.add(new Path(neigh.getName(), cost + name.getWeight(neigh)));//adds new path of current neighbor with it's total cost from startName node to current destination node's neighbor
    		}
        }
		return dnodes;//return the map of the cheapest paths from startName's node to all other nodes in the graph
    }
    
    /**
     * Perform Prim-Jarnik's algorithm to compute a Minimum Spanning Tree (MST).
     * 
     * The MST is itself a graph containing the same nodes and a subset of the edges 
     * from the original graph.
     * 
     * @return
     */
    public IGraph primJarnik() {
    	IGraph rgraph = new Graph();//creating new (minimum spanning tree) represented as a graph (that uses a map) that we will eventually return
        PriorityQueue<Edge> pQueue = new PriorityQueue<Edge>();//priority queue that will organise edges according to value, keeps cheapest at front to be added to map
        INode startnode = nodes.values().iterator().next();//first node, accessed by in-built methods of map
        for(INode neigh: startnode.getNeighbors()) {//for every neighbour of startnode:
        	Edge e = new Edge(startnode,neigh,startnode.getWeight(neigh));//creates new edge between startnode and one of it's neighbors and uses getWeight() method from Node class to access weight--These are all entered as parameters to Edge class
            pQueue.add(e);//adds this new edge(that contains the source,destination node, and weight between them) to the Priority Queue
        }
        while(rgraph.getAllNodes().size()<nodes.size()){//while our new graph has less nodes that number of nodes that exist in original graph, done by using size method which works on collections (which we have as maps here)
        	Edge nextedge = pQueue.poll();//access the next cheapest edge to look at by polling the queue-this gets the head of the queue
        	if(rgraph.containsNode(nextedge.d.getName()))//if the graph already contains the edge, go to next one
        		continue;
        	INode b = rgraph.getOrCreateNode(nextedge.d.getName());//creating destination node of edge in new graph by accessing the destination node currently stored in this instance of Edge class
        	INode a = rgraph.getOrCreateNode(nextedge.s.getName());//creating source node of edge in new graph by accessing the source node currently stored in this instance of Edge class
        	a.addUndirectedEdgeToNode(b, nextedge.weight);//creates an undirected edge between a (look at line 249) and b (look at line 250) and accesses weight of this instance of Edge class
        	for(INode neigh: nodes.get(nextedge.d.getName()).getNeighbors()) {//gets d's name (from this instance of Edge class) to pass into "nodes" map which can then be used to return d's neighbours
            	Edge e = new Edge(nextedge.d,neigh,nextedge.d.getWeight(neigh));//creates new Edge with each one of current destination nodes' neighbors
            	if(!rgraph.containsNode(neigh.getName()))//if the graph doesn't already have the current neighbour node the
            		pQueue.add(e);//add an edge between current destination node and it's neighbor 
            }
        }
        return rgraph;//return the (minimum spanning tree) graph
    }
}