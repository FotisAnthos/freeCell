package freeCell;

import java.util.ArrayList;

public class Node {	
	private ArrayList<Node> children;
	private Node parent;
	private Move move;
	private ArrayList<Card> freeCells;
	private ArrayList<Pile> trableau;
	private ArrayList<Pile> foundations;
	private ArrayList<Move> moves;
	private int heuristicValue;

	public Node(Node parent, Move move, ArrayList<Card> freeCells, ArrayList<Pile> trableau, ArrayList<Pile> foundations){
		this.parent = parent;
		this.children = null;
		this.move = move;
		this.freeCells = freeCells;
		this.trableau = trableau;
		this.foundations = foundations;
	}

	public void addChild(Node aNode){
		children.add(aNode);
	}

	public void addChildren(ArrayList<Node> chilrn) {
		for(Node n : chilrn) {
			children.add(n);
		}
	}

	public int getNoCardsInFoundations() {
		int sum=0;
		for(Pile p : foundations) {
			sum+= p.stackSize();
		}
		return sum;
	}

	public int getHeuristicValue() {
		return heuristicValue;
	}

	public void setHeuristicValue(int heuristicValue) {
		this.heuristicValue = heuristicValue;
	}

	public int NoOfChildren(){
		return children.size();
	}

	public Node whoIsTheFather(){//Anakin is
		return parent;
	}

	public boolean isTarget() {
		if(freeCells.isEmpty()) {//if freeCells not empty then not target
			for(Pile p : trableau) {
				if(!p.isEmpty()) {
					return false;//because at least one trableau has cards in it  
				}
			}
			return true;
		}
		return false;//because there are cards in the freeCells
	}

	public ArrayList<Node> expandNode() {
		ArrayList<Node> newNodes = new ArrayList<Node>();
		possibleMoves();
		for(Move mov : moves) {
			if(!mov.getaCell().equals(move.getaCell())) {//TODO check these 2 ifs again
				if((mov.getSourcePile() != move.getDestinationPile()) && (mov.getDestinationPile() != move.getSourcePile())) {
					newNodes.add(makeAMove(mov));
				}
			}
		}
		return newNodes;
	}

	private ArrayList<Move> possibleMoves(){
		int i, j;
		moves = new ArrayList<Move>();
		//moves from freeCells
		if(!freeCells.isEmpty()){ //if there are cards in the freecells check for valid moves
			for(i=0; i < freeCells.size(); i++){ //for any freecell card:
				for(j=0; j<4; j++){//check if it can be moved to a foundation pile
					if(foundations.get(j).validMove(freeCells.get(i))){
						moves.add(new Move(parent, "foundation", -1, j, freeCells.get(i)));
					}
				}
				for(j=0; j < freeCells.size(); j++){//check if it can be moved to a trableau pile
					if(trableau.get(j).validMove(trableau.get(i).peekTop())){//check if a card from the trableau.get(i) pile can be moved to trableau.get(j) Pile
						if(trableau.get(j).isEmpty())
							moves.add(new Move(parent, "newstack", -1, j, freeCells.get(i)));
						else
							moves.add(new Move(parent, "stack", -1, j, freeCells.get(i)));
					}					
				}
			}
		}
		//moves from trableaus
		for(i=0; i< trableau.get(i).stackSize();i++){//check for valid moves from the trableaus
			for(j=0; j< trableau.get(j).stackSize(); j++){//moves from i-Pile to j-Pile
				if(trableau.get(j).validMove(trableau.get(i).peekTop())){//check if a card from the trableau.get(i) pile can be moved to trableau.get(j) Pile
					if(trableau.get(j).isEmpty())
						moves.add(new Move(parent, "newstack", i, j, null));
					else					
						moves.add(new Move(parent, "stack", i, j, null));
				}
				if(freeCells.size()<4){ //if available free cell there is a possible move
					moves.add(new Move(parent, "freecell", i, -1, null));
				}
			}
			for(j=0; j< foundations.get(j).stackSize(); j++){//moves from i-Pile to foundation
				if(foundations.get(j).validMove(trableau.get(i).peekTop())){
					moves.add(new Move(parent, "foundation", i, j, null));
				}
			}
		}
		return moves;
	}

	private Node makeAMove(Move move){
		Node node = null;

		ArrayList<Card> freecells;
		ArrayList<Pile> trableau;
		ArrayList<Pile> foundations;

		freecells = this.freeCells;
		trableau = this.trableau;
		foundations = this.foundations;

		if(move.getMoveType().equals("freecell")){
			freecells.add(trableau.get(move.getSourcePile()).RemoveCard());
			node = new Node(parent, move, freecells, trableau, foundations);
		}
		else if(move.getMoveType().equals("newstack") || (move.getMoveType().equals("stack"))){
			if(move.getaCell().equals(null)){
				trableau.get(move.getDestinationPile()).AddCard(trableau.get(move.getSourcePile()).RemoveCard());
				node = new Node(parent, move, freecells, trableau, foundations);
			}
			else{
				trableau.get(move.getDestinationPile()).AddCard(move.getaCell());
				freecells.remove(move.getaCell());
				node = new Node(parent, move, freecells, trableau, foundations);
			}
		}
		else if(move.getMoveType().equals("foundation")){
			if(move.getaCell().equals(null)){
				foundations.get(move.getDestinationPile()).AddCard(trableau.get(move.getSourcePile()).RemoveCard());
				node = new Node(parent, move, freecells, trableau, foundations);
			}
			else{
				foundations.get(move.getDestinationPile()).AddCard(move.getaCell());
				freecells.remove(move.getaCell());
				node = new Node(parent, move, freecells, trableau, foundations);
			}
		}
		else
			System.err.println("ERROR M. class Move method makeMove()");
		if(node.equals(null)) System.err.println("A node is null!!- Node.class-makeAMove");
		return node;
	}

	public String aMoveToString() {
		String res = "";

		res = move.getMoveType() + " " + move.getaCell().getType() + "" + move.getaCell().getNo();
		if(move.getMoveType().equals("stack")) {
			res += " " + trableau.get(move.getDestinationPile()).peekTop().cardToString();
		}
		return res;
	}
}
