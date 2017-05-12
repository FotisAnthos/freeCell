package freeCell;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Methods {
	private String output;

	public Methods(String method, Node root, String output) {
		this.output = output;

		if(method.contentEquals("breadth"))
			bfs(root);
		if(method.contentEquals("depth"))
			dfs(root);
		if(method.contentEquals("best"))
			best(root);
		if(method.contentEquals("astar"))
			astar(root);
	}

	private void astar(Node root) {
		// TODO Auto-generated method stub

	}

	private void best(Node root) {
		// TODO Auto-generated method stub

	}

	private void dfs(Node root) {
		// DFS uses Stack data structure
		Stack<Node> stack = new Stack<Node>();
		stack.push(root);
		root.visit(true);

		while(!stack.isEmpty()) {
			Node node = (Node)stack.peek();
			Node child = getUnvisitedChildNode(node);
			if(node.goal()){
				solutionFound(node);
				return;
			}
			if(child != null) {
				child.visit(true);
				stack.push(child);
			}
			else {
				stack.pop();
			}
		}
		// Clear visited property of nodes
		clearNodes(root);

	}

	public void bfs(Node root)
	{
		Node rootNode = root;

		// BFS uses Queue data structure
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(rootNode);
		rootNode.visit(false);
		while(!queue.isEmpty()) {
			Node node = (Node)queue.remove();
			Node child=null;
			if(node.goal()){
				solutionFound(node);
				return;
			}
			else{
				while((child=getUnvisitedChildNode(node))!=null) {
					child.visit(true);
					queue.add(child);
				}
			}
		}
		// Clear visited property of nodes
		clearNodes(root);
	}

	private void solutionFound(Node node){
		try(FileWriter fw = new FileWriter(output, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw))
		{
			while(node!=null){
				out.println(node.getAction());
				node = node.getFather();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}

	public Node getUnvisitedChildNode(Node node){
		int i;

		for(i=0; i<node.NoOfChildren(); i++){
			if(!node.getChild(i).isVisited())
				return node.getChild(i);
		}
		return null;
	}

	public void clearNodes(Node node){
		int i;
		node.visit(false);
		while(node.NoOfChildren() > 0){
			for(i=0; i<node.NoOfChildren(); i++){
				clearNodes(node.getChild(i));
			}
		}
	}

}
