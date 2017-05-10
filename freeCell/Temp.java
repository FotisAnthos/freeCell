package freeCell;

/*

public class Temp {


	public void bfs()
	{
		// BFS uses Queue data structure
		Queue queue = new LinkedList();
		queue.add(this.rootNode);
		printNode(this.rootNode);
		rootNode.visited = true;
		while(!queue.isEmpty()) {
			Node node = (Node)queue.remove();
			Node child=null;
			while((child=getUnvisitedChildNode(node))!=null) {
				child.visited=true;
				printNode(child);
				queue.add(child);
			}
		}
		// Clear visited property of nodes
		clearNodes();
	}

	public void dfs() {
		// DFS uses Stack data structure
		Stack stack = new Stack();
		stack.push(this.rootNode);
		rootNode.visited=true;
		printNode(rootNode);
		while(!stack.isEmpty()) {
			Node node = (Node)s.peek();
			Node child = getUnvisitedChildNode(n);
			if(child != null) {
				child.visited = true;
				printNode(child);
				s.push(child);
			}
			else {
				s.pop();
			}
		}
		// Clear visited property of nodes
		clearNodes();
	}
}


Class Node {
	Char data;
	Public Node(char c) {
		this.data=c;
	}


	private boolean isValid(Pile from, Pile to){
		if(to.isFoundation()){
			if((from.peekTop().getType() == from.peekTop().getType()) && (from.peekTop().getNo() == (to.peekTop().getNo()+1)))
				return true;
		}
		else if(from.peekTop().getColour() != to.peekTop().getColour() && (from.peekTop().getNo() == (to.peekTop().getNo()-1)))
			return true;
		return false;

	}

}
*/