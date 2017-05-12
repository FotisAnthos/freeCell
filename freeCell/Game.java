package freeCell;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Game {

	private static String input_path;

	private ArrayList<Card> freeCells;
	private static ArrayList<Pile> trableau;
	private ArrayList<Pile> foundations;
	private SearchTree tree;


	public Game(String input_path, String output_path, String method) {
		Game.input_path = input_path;
		initialize();
		/*for(int i=0;i<NoOfPiles;i++){
			System.out.println("Stack" + i + ":");
			trableau.get(i).printPile();
		}*/
		System.out.println("Solving free Cell puzzle with " + method + " ...");
		long tStart = System.currentTimeMillis();	//Marking the start of the attempt 

		//TODO Magic Area
		tree = new SearchTree(freeCells, trableau, foundations);//creates the search tree
		new Methods(method, tree.getRoot(), output_path);

		//TODO Some more Magic

		long tEnd = System.currentTimeMillis(); 	//Marking the end of the attempt 
		long tDelta = tEnd - tStart;
		double elapsedSeconds = tDelta / 1000.0;
		System.out.println("Solving free Cell puzzle with " + method + " took " + Double.toString(elapsedSeconds));
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
		if(! retrieve()) System.out.println("Retrieval Failed!");
		else System.out.println("New game Created!");
	}

	// This method reads a file containing a free cell puzzle and stores the numbers
	// Input:
	//			nothing
	// Output:
	//			true --> Successful read.
	//			false --> Unsuccessful read
	public static boolean retrieve() {

		String temp;
		char type;

		try
		{
			FileReader fileReader = new FileReader(input_path);

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



}
