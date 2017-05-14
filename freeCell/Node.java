package freeCell;

import java.util.ArrayList;

public class Node {	
	private ArrayList<Node> children;
	private Node father;
	private boolean isVisited;
	private String action;
	private ArrayList<Card> freeCells;
	private ArrayList<Pile> trableau;
	private ArrayList<Pile> foundations;
	private int heur;

	public Node(Node father, String action, ArrayList<Card> freeCells, ArrayList<Pile> trableau, ArrayList<Pile> foundations){
		this.children = null;
		this.isVisited = false;
		this.action = action;
		this.trableau = trableau;
		this.freeCells = freeCells;
		this.foundations = foundations;
		this.father = father;
		heur = calcHeur();
	}

	public void addChild(Node aNode){
		children.add(aNode);
	}

	public int NoOfChildren(){
		return children.size();
	}

	public void visit(boolean flag){
		this.isVisited = flag;
	}

	public Node getChild(int i) {//return the i-th child in the list
		return children.get(i);
	}

	public Node getFather() {
		return father;
	}

	public boolean isVisited() {
		return isVisited;
	}

	public String getAction() {
		return action;
	}

	public ArrayList<Card> getFreeCells() {
		return freeCells;
	}

	public ArrayList<Pile> getTrableau() {
		return trableau;
	}

	public ArrayList<Pile> getFoundations() {
		return foundations;
	}
	
	public int calcHeur(){
		int i, sum=0;

		for(i=0; i< foundations.size(); i++){
			sum = sum + foundations.get(i).getSumOfCards();
		}
		return sum;
	}
	
	public int heur(){
		return heur;
	}

	public boolean goal(){//checks if the node is a solution-goal
		int i;

		for(i=0; i< trableau.size(); i++){
			if(!trableau.get(i).isEmpty())
				return false;
		}
		for(i=0; i< freeCells.size(); i++){
			if(!freeCells.isEmpty())
				return false;
		}
		return true;	
	}
}
