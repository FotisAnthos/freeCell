package freeCell;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import freeCell.BestFirst.heuristicComparator;

public class AStar {
	Node solutionNode;

	public AStar(Node root) {//A* Search
		
		if(root == null) {
			System.err.println("Best First: root in null");
			return;
		}
		Comparator<Node> comparator = new heuristicComparator();
		Queue<Node> openList = new PriorityQueue<Node>(comparator);//Initialize the open list
		ArrayList<Node> closedList = new ArrayList<Node>();//Initialize the closed list
		
		openList.add(root);//put the starting node on the open list 
		
		while(!openList.isEmpty()) {//while the open list is not empty
			Node tempNode = openList.poll();//find the node with the least f on the open list //pop q off the open list
			
			ArrayList<Node> newNodes = tempNode.expandNode();//generate q's 8 successors and set their parents to q
			for(Node n : newNodes) {
				if(n.isTarget()) {//if successor is the goal, stop search
					solutionNode = n;
					return;
				}
				
				//calcHeuristicValue(n);
				//fringe.add(n);
			}
		}

		
		/*
		// A* Search Algorithm
		1.  Initialize the open list
		2.  Initialize the closed list
		    put the starting node on the open 
		    list (you can leave its f at zero)

		3.  while the open list is not empty
		    a) find the node with the least f on 
		       the open list, call it "q"

		    b) pop q off the open list
		  
		    c) generate q's 8 successors and set their 
		       parents to q
		   
		    d) for each successor
		        i) if successor is the goal, stop search
		          successor.g = q.g + distance between 
		                              successor and q
		          successor.h = distance from goal to 
		          successor (This can be done using many 
		          ways, we will discuss three heuristics- 
		          Manhattan, Diagonal and Euclidean 
		          Heuristics)
		          
		          successor.f = successor.g + successor.h

		        ii) if a node with the same position as 
		            successor is in the OPEN list which has a 
		           lower f than successor, skip this successor

		        iii) if a node with the same position as 
		            successor  is in the CLOSED list which has
		            a lower f than successor, skip this successor
		            otherwise, add  the node to the open list
		     end (for loop)
		  
		    e) push q on the closed list
		    end (while loop) */
	}

	public Node getSolutionNode() {
		if(solutionNode.equals(null)) System.err.println("DFS solutionNode is null!!");
		return solutionNode;
	}

	class heuristicComparator implements Comparator<Node>
	{
		@Override
		public int compare(Node x, Node y)
		{
			return x.getHeuristicValue() - y.getHeuristicValue();
		}
	}

}
