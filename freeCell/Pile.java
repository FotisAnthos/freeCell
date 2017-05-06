package freeCell;

import java.util.Stack;

public class Pile {
	
	private Stack<Card> stack;

	public Pile() {
		this.stack = new Stack<Card>();
	}
	
	public void AddCard(Card aCard){
		stack.push(aCard);
	}
	
	public void RemoveCard(Card aCard){
		stack.pop();
	}

	public void printPile() {
		int i;
		
		for(i=0; i<stack.size(); i++){
			stack.get(i).printCardDetails();
		}
		System.out.println("Top:");
		stack.peek().printCardDetails();
	}
	
	
	
	
	

}
