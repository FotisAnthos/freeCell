package freeCell;

public class Card {

	private char type;
	private int val;
	private int colour; //1-> black while 0-> red

	public Card(char type, String temp) {
		this.type = type;
		this.val = Integer.parseInt(temp);

		if(type == 'C' || type == 'S') colour=1;
		else colour = 0;
	}

	public char getType() {
		return type;
	}

	public int getNo() {
		return val;
	}

	public int getColour() {
		return colour;
	}

	public void printCardDetails(){
		System.out.println(type +""+ val);

	}
	
	public String cardToString() {
		return type +""+ val;
	}

}
