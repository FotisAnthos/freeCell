package freeCell;

import java.util.ArrayList;
//TODO class for deletion
public class SearchTree {
	private Node root;
	private ArrayList<Card> freeCells;
	private ArrayList<Pile> trableau;
	private ArrayList<Pile> foundations;

	private ArrayList<Move> moves;//temporary log of possible moves for a given Node

	public SearchTree(ArrayList<Card> freeCells, ArrayList<Pile> trableau, ArrayList<Pile> foundations){
		this.trableau = trableau;
		this.freeCells = freeCells;
		this.foundations = foundations;

		//this.root = new Node(null, "root", freeCells, trableau, foundations);
	}
/*
	private void build() {
		//TODO check the entire table for possible moves
		Node currNode = root;
		
		this.moves = new ArrayList<Move>();

		int i = 0;
		while(possibleMoves(currNode)){
			i++;
			if(i>10) break;
		}




	}

	private boolean possibleMoves(Node aNode){
		int i, j;
		this.moves = new ArrayList<Move>();
		
		if(!freeCells.isEmpty()){ //if there are cards in the freecells check for valid moves
			for(i=0; i < freeCells.size(); i++){ //for any freecell card:
				for(j=0; j<4; j++){//check if it can be moved to a foundation pile
					if(foundations.get(j).validMove(freeCells.get(i))){
						moves.add(new Move(aNode, "foundation", -1, j, freeCells.get(i)));
					}
				}
				for(j=0; j < freeCells.size(); j++){//check if it can be moved to a trableau pile
					if(trableau.get(j).validMove(trableau.get(i).peekTop())){//check if a card from the trableau.get(i) pile can be moved to trableau.get(j) Pile
						if(trableau.get(j).isEmpty())
							moves.add(new Move(aNode, "newstack", -1, j, freeCells.get(i)));
						else
							moves.add(new Move(aNode, "stack", -1, j, freeCells.get(i)));
					}					
				}
			}
		}

		for(i=0; i< trableau.get(i).trableauSize();i++){//check for valid moves from the trableaus
			for(j=0; j< trableau.get(j).trableauSize(); j++){//moves from i-Pile to j-Pile
				if(trableau.get(j).validMove(trableau.get(i).peekTop())){//check if a card from the trableau.get(i) pile can be moved to trableau.get(j) Pile
					if(trableau.get(j).isEmpty())
						moves.add(new Move(aNode, "newstack", i, j, null));
					else					
						moves.add(new Move(aNode, "stack", i, j, null));
				}
				if(freeCells.size()<4){ //if available free cell there is a possible move
					moves.add(new Move(aNode, "freecell", i, -1, null));
				}
			}
			for(j=0; j< foundations.get(j).trableauSize(); j++){//moves from i-Pile to foundation
				if(foundations.get(j).validMove(trableau.get(i).peekTop())){
					moves.add(new Move(aNode, "foundation", i, j, null));
				}
			}
		}
		return false;
	}

	class Move{//a "log" of a possible move found at SearchTree.possibleMoves
		private Node parent;
		private String moveType;
		private int destinationPile;
		private Card aCell;
		private int sourcePile;

		public Move(Node parent, String moveType, int sourcePile, int destinationPile, Card aCell) {
			this.moveType = moveType;
			this.sourcePile = sourcePile;
			this.destinationPile = destinationPile;
			this.aCell = aCell;
		}

		public Node makeAMove(){
			Node node;

			ArrayList<Card> freecells;
			ArrayList<Pile> trableau;
			ArrayList<Pile> foundations;

			freecells = SearchTree.this.freeCells;
			trableau = SearchTree.this.trableau;
			foundations = SearchTree.this.foundations;

			node = new Node(null, moveType, freecells, foundations, foundations);//initialization to get rid of the relative error.
			if(moveType.equals("freecell")){
				freecells.add(trableau.get(sourcePile).RemoveCard());
				node = new Node(parent, moveType, freecells, trableau, foundations);
			}
			else if(moveType.equals("newstack") || (moveType.equals("stack"))){
				if(aCell.equals(null)){
					trableau.get(destinationPile).AddCard(trableau.get(sourcePile).RemoveCard());
					node = new Node(parent, moveType, freecells, trableau, foundations);
				}
				else{
					trableau.get(destinationPile).AddCard(aCell);
					freecells.remove(aCell);
					node = new Node(parent, moveType, freecells, trableau, foundations);
				}
			}
			else if(moveType.equals("foundation")){
				if(aCell.equals(null)){
					foundations.get(destinationPile).AddCard(trableau.get(sourcePile).RemoveCard());
					node = new Node(parent, moveType, freecells, trableau, foundations);
				}
				else{
					foundations.get(destinationPile).AddCard(aCell);
					freecells.remove(aCell);
					node = new Node(parent, moveType, freecells, trableau, foundations);
				}
			}
			else
				System.err.println("ERROR SearchTree. class Move method makeMove()");
			return node;
		}




	}	
*/
}
