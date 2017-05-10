package freeCell;

import java.util.ArrayList;

public class Node {	
	private ArrayList<Node> children;
	private Node father;
	private boolean visited;
	private String action;
	private ArrayList<Card> freeCells;
	private ArrayList<Pile> trableau;
	private ArrayList<Pile> foundations;

	public Node(Node father, String action, ArrayList<Card> freeCells, ArrayList<Pile> trableau, ArrayList<Pile> foundations){
		this.children = null;
		this.visited = false;
		this.action = action;
		this.trableau = trableau;
		this.freeCells = freeCells;
		this.foundations = foundations;
		this.father = father;
	}
	
	public void addChild(Node aNode){
		children.add(aNode);
	}
	
	public int NoOfChildren(){
		return children.size();
	}




}
