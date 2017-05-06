package freeCell;

import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws Exception{
		int N=13;
		String NoOfCardsPerDeckType = "13";
		String path = "C:\\Users\\fotis\\workspace\\freeCell\\external\\generator.exe";
		String generatedPuzzlePath = "C:\\Users\\fotis\\workspace\\freeCell\\test1.txt";
		
		
		NoOfCardsPerDeckType = Integer.toString(N);
		try { //Running generator.exe to create a new puzzle in file test with 13 cards per deck type
			Process p = new ProcessBuilder(path,"test", "1", "1", NoOfCardsPerDeckType).start();
			p.waitFor();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new Game(generatedPuzzlePath);		

	}

}
