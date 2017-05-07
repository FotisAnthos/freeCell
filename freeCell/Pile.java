package freeCell;

import java.util.Stack;

public class Pile {
	
	private Stack<Card> stack;
	private boolean isFoundation;

	public Pile(boolean isFoundation) {
		this.isFoundation = isFoundation; 
		this.stack = new Stack<Card>();
	}
	
	public void AddCard(Card aCard){
		if(isFoundation){
			if(stack.peek().getType() == aCard.getType() && stack.peek().getNo() == aCard.getNo()-1){  //TODO
				stack.push(aCard);
				return;
			}
		}
		if(stack.peek().getColour() != aCard.getColour() && stack.peek().getNo() == aCard.getNo()-1){
			stack.push(aCard);
			return;
		}
		System.out.println("ERROR\nClass -> Pile / Method -> AddCard");
	}
	
	public void RemoveCard(Card aCard){
		if(isFoundation){
			System.out.println("ERROR\nAttempting to remove from foundation!!");
			return;
		}
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
