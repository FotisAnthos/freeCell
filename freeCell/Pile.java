package freeCell;

import java.util.Stack;

public class Pile {

	private Stack<Card> stack;
	private boolean isFoundation;

	public Pile(boolean isFoundation) {
		this.isFoundation = isFoundation; 
		this.stack = new Stack<Card>();
	}

	public void putCardInPile(Card aCard){ //only for the initialization of the trableaus
		stack.push(aCard);
	}

	public void AddCard(Card aCard){
		if(validMove(aCard)) stack.push(aCard);
	}

	public boolean validMove(Card aCard){//checks if aCard can be moved to the end of this stack-Pile
		if(stack.isEmpty()) return true;//Στη βάση μιας στοίβας, εφόσον η στοίβα είναι άδεια, χωρίς άλλο περιορισμό.
		if(isFoundation){//Στην κορυφή ενός foundation, εφόσον η προηγούμενη κορυφαία κάρτα του foundation είναι ίδιας "φυλής" και κατά ένα μικρότερης "αξίας" (αριθμό).
			if(stack.peek().getType() == aCard.getType() && stack.peek().getNo() == aCard.getNo()+1){ 
				return true;
			}
			if(stack.isEmpty() && aCard.getNo() == 0) return true;//Στη βάση ενός άδειου foundation, εφόσον η κάρτα είναι "άσσος".
		}
		else if(stack.peek().getColour() != aCard.getColour() && stack.peek().getNo() == aCard.getNo()-1){
			return true;//Στην κορυφή μιας στοίβας, εφόσον η προηγούμενη κορυφαία κάρτα της στοίβας είχε διαφορετικό χρώμα και κατά ένα μεγαλύτερη "αξία" (αριθμό).
		}
		return false;
	}

	public Card RemoveCard(){
		if(isFoundation){
			System.out.println("ERROR\nAttempting to remove from foundation!!");
		}
		if(isEmpty()) {
			System.out.println("ERROR\nAttempting to remove from empty stack!!");
		}
		return stack.pop();
	}

	public boolean isEmpty(){
		return stack.isEmpty();
	}

	public Card peekTop() {
		return stack.peek();
	}
	
	public int stackSize() {
		return stack.size();
	}

	public boolean isFoundation() {
		return isFoundation;
	}

	public void printPile() {
		for(int i=0; i<stack.size(); i++){
			stack.get(i).printCardDetails();
			System.out.print(" ");
		}
	}

}
