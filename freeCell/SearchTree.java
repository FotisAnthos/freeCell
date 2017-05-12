package freeCell;

import java.util.ArrayList;
import java.util.Queue;

public class SearchTree {
	private Node root;
	private ArrayList<Card> freeCells;
	private ArrayList<Pile> trableau;
	private ArrayList<Pile> foundations;

	private Queue<Move> moves;//temporary log of possible moves for a given Node

	public SearchTree(ArrayList<Card> freeCells, ArrayList<Pile> trableau, ArrayList<Pile> foundations){
		this.trableau = trableau;
		this.freeCells = freeCells;
		this.foundations = foundations;

		this.root = new Node(null, "root", freeCells, trableau, foundations);

		build(root);
	}


	public Node getRoot() {
		return root;
	}


	private void build(Node node) {
		//TODO check the table for possible moves

		while(true){

			possibleMoves(node);
			while(!moves.isEmpty()){
				Node newnode = makeAMove(moves.remove());
				node.addChild(newnode);	
				build(newnode);
			}
		}
	}

	private boolean possibleMoves(Node aNode){
		int i, j;

		if(!freeCells.isEmpty()){ //if there are cards in the freecells check for valid moves
			for(i=0; i < freeCells.size(); i++){ //for any freecell card:
				for(j=0; j<4; j++){//check if it can be moved to the foundation pile
					if(foundations.get(j).validMove(freeCells.get(i))){
						moves.add(new Move(aNode, "foundation", -1, j, freeCells.get(i)));
					}	
				}
				for(j=0; j < trableau.size(); j++){
					if(trableau.get(j).validMove(freeCells.get(i))){//check if a card from the freeCard can be moved to trableau.get(j) Pile
						if(trableau.get(j).isEmpty())
							moves.add(new Move(aNode, "newstack", -1, j, freeCells.get(i)));
						else
							moves.add(new Move(aNode, "stack", -1, j, freeCells.get(i)));
					}					
				}
			}
		}

		for(i=0; i< trableau.size();i++){//check for valid moves from the trableaus
			for(j=0; j< trableau.size(); j++){
				if(trableau.get(j).validMove(trableau.get(i).peekTop())){//check if a card from the trableau.get(i) pile can be moved to trableau.get(j) Pile
					if(trableau.get(j).isEmpty())
						moves.add(new Move(aNode, "newstack", i, j, null));
					else					
						moves.add(new Move(aNode, "stack", i, j, null));
				}
				if(freeCells.size()<4){ //if available free cell there is a possible move
					Move move1 = new Move(aNode, "freecell", i, -1, null);
					moves.add(move1);
				}
				if(foundations.get(j).validMove(trableau.get(i).peekTop())){
					moves.add(new Move(aNode, "foundation", i, j, null));
				}
			}
		}

		return false;
	}

	public Node makeAMove(Move move){
		Node node =new Node(null, null, freeCells, foundations, foundations);

		ArrayList<Card> freecells;
		ArrayList<Pile> trableau;
		ArrayList<Pile> foundations;

		freecells = this.freeCells;
		trableau = this.trableau;
		foundations = this.foundations;

		if(move.getType().equals("freecell")){
			freecells.add(trableau.get(move.getSourcePile()).RemoveCard());
			node = new Node(move.getParent(), move.getType(), freecells, trableau, foundations);
		}
		else if(move.getType().equals("newstack") || (move.getType().equals("stack"))){
			if(move.getFreeCell().equals(null)){
				trableau.get(move.getDestinationPile()).AddCard(trableau.get(move.getSourcePile()).RemoveCard());
				node = new Node(move.getParent(), move.getType(), freecells, trableau, foundations);
			}
			else{
				trableau.get(move.getDestinationPile()).AddCard(move.getFreeCell());
				freecells.remove(move.getFreeCell());
				node = new Node(move.getParent(), move.getType(), freecells, trableau, foundations);
			}
		}
		else if(move.getType().equals("foundation")){
			if(move.getFreeCell().equals(null)){
				foundations.get(move.getDestinationPile()).AddCard(trableau.get(move.getSourcePile()).RemoveCard());
				node = new Node(move.getParent(), move.getType(), freecells, trableau, foundations);
			}
			else{
				foundations.get(move.getDestinationPile()).AddCard(move.getFreeCell());
				freecells.remove(move.getFreeCell());
				node = new Node(move.getParent(), move.getType(), freecells, trableau, foundations);
			}
		}
		else
			System.err.println("ERROR SearchTree. class Move method makeMove()");
		return node;
	}
}

class Move{	//a "log" of a possible move found at SearchTree.possibleMoves
	// TODO Auto-generated method stub
	private Node parent;
	private String type;
	private int destinationPile;
	private Card freeCell;
	private int sourcePile;

	public Move(Node parent, String type, int sourcePile, int destinationPile, Card freeCell) {
		this.type = type;
		this.sourcePile = sourcePile;
		this.destinationPile = destinationPile;
		this.freeCell = freeCell;
	}


	public Node getParent() {
		return parent;
	}



	public String getType() {
		return type;
	}



	public int getDestinationPile() {
		return destinationPile;
	}



	public Card getFreeCell() {
		return freeCell;
	}



	public int getSourcePile() {
		return sourcePile;
	}





}
