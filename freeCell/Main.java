package freeCell;

import java.io.IOException;

public class Main {

	public static int main(String[] args){
		int N=13;
		String NoOfCardsPerDeckType = "13";
		String path = "C:\\Users\\fotis\\workspace\\freeCell\\external\\generator.exe";
		String generatedPuzzlePath = "C:\\Users\\fotis\\workspace\\freeCell\\test1.txt";
		String method, input_path, output_path;

		method = "";
		if(args[0].contentEquals("breadth"))
			method = "breadth";
		else if(args[0].contentEquals("depth"))
			method = "depth";
		else if(args[0].contentEquals("best"))
			method = "best";
		else if(args[0].contentEquals("A*search"))
			method = "A*search";
		else syntax_message();

		if(args[1] != null)
			input_path = args[1];
		else input_path = "C:\\Users\\fotis\\workspace\\freeCell\\test1.txt";
		if(args[2] != null)
			output_path = args[2];
		else output_path = "C:\\Users\\fotis\\workspace\\freeCell\\solution.txt";


		NoOfCardsPerDeckType = Integer.toString(N);
		/*try { //Running generator.exe to create a new puzzle in file test with 13 cards per deck type
			Process p = new ProcessBuilder(path, output_path, "1", "1", NoOfCardsPerDeckType).start();
			try {
				p.waitFor();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 3;
		}*/

		new Game(generatedPuzzlePath, method);		
		return 0;

	}

	private static void syntax_message() {
		// TODO syntax description & termination of the program

		System.out.println("Use syntax:\n\n");
		System.out.println("\tfreeCell <method> <input_file> <output_file> \n\n");
		System.out.println("where:\n ");
		System.out.println("\t<method> = the method of AI solution to be used\n");
		System.out.println("\t<input_file> = String indicating the input file.\n");
		System.out.println("\t<output_file> = String indicating the output file.\n\n");
		//System.out.println("e.g. the call \n\n\t\n");
		System.out.println("Constraints: Possible methods breadth, depth, best, A*search.");


		System.exit(2);
	}

}
