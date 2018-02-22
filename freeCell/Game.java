package freeCell;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class Game {

	private static final int NoOfPiles = 8; // TODO remove this if possible TODO check with main.class NoOfCardsPerDeckType

	private static String inPath;
	private static String outPath;

	private ArrayList<Card> freeCells;
	private static ArrayList<Pile> trableau;
	private ArrayList<Pile> foundations;
	private String method;
	private Node root;


	public Game(String input_path, String output_path, String method) {
		Game.inPath = input_path;
		Game.outPath = output_path;
		this.method = method;
		initialize();
		
		for(int i=0;i<NoOfPiles;i++){//TODO make this dynamic (?)
			System.out.print("Stack" + i + ": ");
			trableau.get(i).printPile();
			System.out.println();
		}
		
		System.out.println("\nSolving free Cell puzzle with method: " + this.method + " ...\n");
		long tStart = System.currentTimeMillis();	//Marking the start of the attempt 

		//TODO Magic Area
		this.root = new Node(null, null, freeCells, trableau, foundations);
		startSearch();

		long tEnd = System.currentTimeMillis(); 	//Marking the end of the attempt 
		long tDelta = tEnd - tStart;
		double elapsedSeconds = tDelta / 1000.0;
		System.out.println("\nSolving free Cell puzzle with " + this.method + " took " + Double.toString(elapsedSeconds));
	}
	
	private void startSearch() {
		if(method.equals("breadth")) {
			BFS bfs = new BFS(root);
			printSolution(bfs.getSolutionNode());
		}
		else if(method.equals("breadth")) {
			DFS dfs = new DFS(root);
			printSolution(dfs.getSolutionNode());
		}
		else if(method.equals("best")) {
			//bestFirst();
		}
		else if(method.equals("A*search")) {
			//AStar();
		}
		else {
			System.out.println("Methods: chosenMethod not recognised");
		}
	}



	// This method prepares a new free cell game	
	private void initialize() {
		int i;
		//Creation of the initial table image
		trableau = new ArrayList<Pile>();
		for(i=0;i<8;i++){
			trableau.add(new Pile(false));
		}

		freeCells = new ArrayList<Card>();
		foundations = new ArrayList<Pile>();
		for(i=0;i<4;i++){
			foundations.add(new Pile(true));
		}

		//Insertion of randomized card piles
		if(! retrieve()) System.out.println("\nRetrieval Failed!\n");
		else System.out.println("\nNew game Created!\n");
	}

	// This method reads a file containing a free cell puzzle and stores the numbers
	// Input:
	//			nothing
	// Output:
	//			true --> Successful read.
	//			false --> Unsuccessful read
	private boolean retrieve() {

		String temp;
		char type;

		try
		{
			FileReader fileReader = new FileReader(inPath);

			int i, column;
			column =0;

			while((i =  fileReader.read())!=-1){
				char ch = (char)i;
				if(ch=='\n') column++;
				if(ch == 'S' || ch == 'H' || ch == 'D' || ch == 'C'){		//reads card type
					type = ch;

					temp = "";
					while((i = fileReader.read())!=-1 && (i>='0' && i<='9')){//reads card value
						ch = (char)i;
						temp += ch;
					}
					//System.out.println(type + temp);
					Card newCard = new Card(type, temp);
					trableau.get(column).putCardInPile(newCard);
				}
			}

			fileReader.close();

		}catch(IOException i)
		{
			System.out.println("Failed to read file");
			i.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void printSolution(Node solNode) {//TODO modify to write to solution.txt
		Stack<Node> solutionSteps = new Stack<Node>();
		Node tempNode = solNode;
		int stepCount=-1;
		
		while(tempNode.whoIsTheFather().equals(null)) {//only root has parent == null
			solutionSteps.push(tempNode);
			tempNode = tempNode.whoIsTheFather();
		}
		
		stepCount = solutionSteps.size()-1;
		System.out.println("***Solution Start***");
		System.out.println(stepCount);
		
		while(!solutionSteps.isEmpty()) {
			System.out.println(solutionSteps.pop().aMoveToString());
		}
		System.out.println("***Solution End***");
	}
}
