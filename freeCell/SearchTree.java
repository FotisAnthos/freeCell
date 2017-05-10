package freeCell;

import java.util.ArrayList;

public class SearchTree {
	private Node root;
	private ArrayList<Node> children;
	private boolean visited;
	private String action;

	// helper node data type
	
	private class Node {
		Node NW, NE, SE, SW;   // four subtrees
		int heur;           // associated data

		Node(int heur) {
			this.heur = heur;
			
		}
	}

	


}

