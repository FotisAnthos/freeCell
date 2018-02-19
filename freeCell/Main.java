package freeCell;

public class Main {

	public static void main(String[] args){
		int N=13;
		String NoOfCardsPerDeckType = "13";//TODO remove ?
		String baseDebugPath = "C:\\Users\\fotis\\Documents\\GitHub\\freeCell";
		String generatorPath = baseDebugPath + "\\external\\generator.exe";
		String defaultPuzzlePath = baseDebugPath + "\\test1.txt";
		String method, input_path, output_path;

		boolean debug = true;

		if(debug  == false) {
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
			else input_path = defaultPuzzlePath;
			if(args[2] != null)
				output_path = args[2];
			else output_path = "C:\\Users\\fotis\\workspace\\freeCell\\solution.txt";
		}

		else {//debug == true
			input_path = defaultPuzzlePath;
			output_path = baseDebugPath + "\\solution.txt";
			method = "breadth";
		}

		/* 
		NoOfCardsPerDeckType = Integer.toString(N);
		try { //Running generator.exe to create a new puzzle in file test with 13 cards per deck type
			Process p = new ProcessBuilder(generatorPath, output_path, "1", "1", NoOfCardsPerDeckType).start();
			try {
				p.waitFor();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		 */
		new Game(input_path, output_path, method);		
		return;

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
