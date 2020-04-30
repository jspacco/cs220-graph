package graph.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;


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
    HashMap<String,INode> nodes = new HashMap<String,INode>();
    
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
    public INode getOrCreateNode(String name) {
        if(this.containsNode(name)){
        	return nodes.get(name);
        } else {
        	nodes.put(name, new Node(name));
        	return nodes.get(name);
        	
        }
    }

    /**
     * Return true if the graph contains a node with the given name,
     * and false otherwise.
     * 
     * @param name
     * @return
     */
    public boolean containsNode(String name) {
        return nodes.containsKey(name);
    }

    /**
     * Return a collection of all of the nodes in the graph.
     * 
     * @return
     */
    public Collection<INode> getAllNodes() {
        return nodes.values();
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
        LinkedList<INode> fringe = new LinkedList<INode>();
        //add to the end
        HashSet<String> visited = new HashSet<String>();
        
        INode curr = this.getOrCreateNode(startNodeName);
        fringe.add(curr);
        
        while(fringe.isEmpty() != true) {
        	if(visited.contains(curr.getName())) {
        		//don't visit more than once
        		continue;
        	} else {
        		v.visit(curr);
            	visited.add(curr.getName());
            	fringe.addAll(curr.getNeighbors());
        	}
        	curr = fringe.getFirst(); //so it's FIFO
        }
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
    { //copied from breadth first
    	LinkedList<INode> fringe = new LinkedList<INode>();
        //add to the end
        HashSet<String> visited = new HashSet<String>();
        
        INode curr = this.getOrCreateNode(startNodeName);
        
        while(fringe.isEmpty() != true) {
        	if(visited.contains(curr.getName())) {
        		//don't visit more than once
        		continue;
        	} else {
        		v.visit(curr);
            	visited.add(curr.getName());
            	fringe.addAll(curr.getNeighbors());
        	}
        	curr = fringe.getLast();//so it's LIFO
        }
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
    public Map<INode,Integer> dijkstra(String startName) {
        // TODO: Implement this method
        throw new UnsupportedOperationException("Implement this method");
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
        //TODO Implement this method
        throw new UnsupportedOperationException();
    }
}