package freeCell;

public class Move {
	//a "log" of a possible move found at SearchTree.possibleMoves
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

			public String getMoveType() {
				return moveType;
			}

			public int getDestinationPile() {
				return destinationPile;
			}

			public Card getaCell() {
				return aCell;
			}

			public int getSourcePile() {
				return sourcePile;
			}		
			
}
