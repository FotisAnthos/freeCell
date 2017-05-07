package freeCell;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Game {
	
	private static final int NoOfPiles = 8;

	private static String path;
	
	private ArrayList<Card> freeCells;
	private static ArrayList<Pile> trableau;
	private ArrayList<Pile> foundations;
	private String method;
	
	
	public Game(String generatedPuzzlePath, String method) {
		Game.path = generatedPuzzlePath;
		this.method = method;
		initialize();
		/*for(int i=0;i<NoOfPiles;i++){
			System.out.println("Stack" + i + ":");
			trableau.get(i).printPile();
		}*/
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
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

	public static boolean retrieve() {
		
		String temp;
		char type;
		
		try
	      {
			FileReader fileReader = new FileReader(path);
			 
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
				   
				   trableau.get(column).AddCard(newCard);
				   	   
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
