package freeCell;

import java.util.Stack;

public class Pile {

	private Stack<Card> stack;
	private boolean isFoundation;
	private int sumOfCards;

	public Pile(boolean isFoundation) {
		this.isFoundation = isFoundation; 
		this.stack = new Stack<Card>();
		this.sumOfCards=0;
	}

	public void putCardInPile(Card aCard){ //only for the initialization of the trableaus
		stack.push(aCard);
		sumOfCards++;
	}

	public void AddCard(Card aCard){
		if(validMove(aCard)) stack.push(aCard);
		sumOfCards++;
	}

	public boolean validMove(Card aCard){
		if(stack.isEmpty()) return true;
		if(isFoundation){
			if(stack.peek().getType() == aCard.getType() && stack.peek().getNo() == aCard.getNo()-1){  //TODO check again
				return true;
			}
			if(stack.isEmpty() && aCard.getNo() == 0) return true;
		}
		if(stack.peek().getColour() != aCard.getColour() && stack.peek().getNo() == aCard.getNo()-1){
			return true;
		}
		return false;
	}

	public Card RemoveCard(){
		if(isFoundation){
			System.out.println("ERROR\nAttempting to remove from foundation!!");
		}
		sumOfCards--;
		return stack.pop();
	}

	public boolean isEmpty(){
		return stack.isEmpty();
	}

	public Card peekTop() {
		return stack.peek();
	}

	public boolean isFoundation() {
		return isFoundation;
	}

	public void printPile() {
		int i;

		for(i=0; i<stack.size(); i++){
			stack.get(i).printCardDetails();
		}
		System.out.println("Top:");
		stack.peek().printCardDetails();
	}

	public int getSumOfCards() {
		return sumOfCards;
	}


}
