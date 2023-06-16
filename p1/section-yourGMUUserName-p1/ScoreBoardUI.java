import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/**
 *  A textual UI to help you to interact with the score board.
 *  Use with the command:
 * 		java ScoreBoardUI Input_File_Name
 *  
 *  @author Y. Zhong
 */	
public class ScoreBoardUI {

	/**
	 * String for better formatting.
	 */
	private static String divider = "----------------------------------------\n";

	/**
	 * ScoreBoard we maintain to demo.
	 */	
	private static ScoreBoard board = null; 
	
	/**
	 * Scanner to get input from keyboard or file.
	 */
	private static Scanner stdIn = null;

	 
	/**
	 *  The main method that presents the UI.
	 *  
	 *  @param args command line args: first arg can specify an input file 
	 */
	public static void main(String[] args) {
		//open and read from input file to initialize score board
		if(args.length != 1){
			System.out.println("Usage: java ScoreBoardUI Input_File_Name");
			return;
		}
		else {
			board = boardFromFile(args[0]);
			if (board==null || board.gameCount()<=0){
				System.out.println("File " + args[0] + " initialization error.");
				return;
			}
		}
				
		int option;
		stdIn = new Scanner(System.in);
		while(true){
			displayMenu();
			
			option = stdIn.nextInt(); //get the next menu choice
			stdIn.nextLine();
			switch(option){
				case 1: //display
					System.out.print(divider);
					System.out.println(board.toString());
					break;
				case 2: //display one player
					processPlayerDetails();
					break;
				case 3: //display one Game
					processGameDetails();
					break;
				case 4: //combine/update from file
					processCombineFile();
					break;
				case 5: //remove a player
					processRemovePlayer();
					break;
				case 6: //change one score of one player
					processChangeScore();
					break;
				case 7: //show top total score and player(s)
					processTopRecords();
					break;
				case 8: //exit
					System.out.println("Good-bye!");
					return;
				default:
					System.out.println("Option not supported!");
			
			}			
		}

	
	}
	
	/**
	 *  The method that create a score board from the given file.
	 *  
	 *  @param fileName name of the input file 
	 *  @return the created score board
	 */
	private static ScoreBoard boardFromFile(String fileName){
		Scanner scanner = null;
		try{
			// open file for input
			scanner = new Scanner(new File(fileName));				
		}catch(IOException e) {
			e.printStackTrace();
			return null;
		}
		
		//comma as delimiter
		scanner.useDelimiter(",");
		
		//first line of input file must be game count
		int gameCount = scanner.nextInt();
		scanner.nextLine();
		
		if (gameCount<1){
			System.out.println("Incorrect game count!");
			return null;
		}
		
		ScoreBoard board = new ScoreBoard(gameCount);
		
		//add player record
		while (scanner.hasNext()){
			String name = scanner.next();
			PlayerRec player = new PlayerRec(name);
			for (int i=0; i<gameCount; i++){
				int score = Integer.valueOf(scanner.next());
				player.addScore(score);
			}
			scanner.nextLine();
			//System.out.println(player);
			
			board.addPlayer(player);
		}		
		scanner.close();
		
		//System.out.println(board);
		return board;
	}
	
	/**
	 *  The method that displays the menu.
	 *  
	 */
	private static void displayMenu(){
		System.out.println("\nPlease select from the following options:");
		System.out.println("1 - Show current score board");
		System.out.println("2 - Show details of a player");
		System.out.println("3 - Show details of a game");
		System.out.println("4 - Combine/update records from file");
		System.out.println("5 - Remove a player");
		System.out.println("6 - Change one score of one player");
		System.out.println("7 - Show top total score & top player(s)");
		System.out.println("8 - Exit");
		System.out.print(divider);
		System.out.print("Your choice (1-8): ");	
	}
	

	/**
	 *  The method that removes a player by name.
	 *  
	 */
	private static void processRemovePlayer(){
		//asking for a player name
		System.out.print("Please enter the name of player to remove: ");
		String name = stdIn.nextLine();
		
		//verify player
		PlayerRec toRemove = board.findPlayer(name);
		if (toRemove == null){
			System.out.println("No such player!");
			return;
		}
		
		//remove player
		if (board.removePlayer(name)){
			System.out.println("Player removed!");
			System.out.println("Removed player details: " +toRemove.toString());
		}
		else{
			System.out.format("Player %s CANNOT be removed!\n", name);
		
		}
	
	}

	/**
	 *  The method that change one score of one player.
	 *  
	 */
	private static void processChangeScore(){
		//asking for a player name
		System.out.print("Please enter the name of player to change: ");
		String name = stdIn.nextLine();
				
		//ask which game to change
		System.out.format("Please enter the game number to change [0-%d]: ", board.gameCount()-1);
		int game = stdIn.nextInt();
		stdIn.nextLine();
					
		//get new score
		System.out.print("Please enter the new score (>=0): ");
		int score = stdIn.nextInt();
		stdIn.nextLine();

		//update
		if (board.changeScore(name, game, score)){
			System.out.format("Score updated for %s, game %d. New score = %d.\n", name, game, score);
		}
		else{
			System.out.format("Score CANNOT be updated to %d for %s, game %d. \n", score, name, game);
		}
		

	}
		

	/**
	 *  The method that reports the max total score and the list of players of that total.
	 *  
	 */
	private static void processTopRecords(){
		//top score
		System.out.println("Top Total Score: " + board.topTotalScore());
		
		//top players
		ThreeTenDynArray<String> topPlayers = board.topPlayers();
		System.out.println("Top Player(s): " + topPlayers.toString()); 
	
	}

	/**
	 *  The method that displays the details of one player.
	 *  
	 */
	private static void processPlayerDetails(){
		//asking for a player name
		System.out.print("Please enter the name of player to show: ");
		String name = stdIn.nextLine();
		
		//verify player
		PlayerRec player = board.findPlayer(name);
		if (player == null){
			System.out.println("No such player!");
			return;
		}
		
		//display details 
		System.out.println(player.toNiceString());
	
	}

	/**
	 *  The method that displays the details of one game.
	 *  
	 */
	private static void processGameDetails(){
	
		//ask for one game index
		System.out.format("Please enter the game number to show [0-%d]: ", board.gameCount()-1);
		int game = stdIn.nextInt();
		stdIn.nextLine();
		
		//display game details
		System.out.println(board.gameToString(game));
	
	}

	/**
	 *  The method that combine records from a file to the current score board.
	 *  
	 */	
	private static void processCombineFile(){
	
		//choose how to combine
		int choice = -1;
		while (choice!=0 && choice !=1){
			System.out.print("Append(0) or Prepend(1)? ");
			choice = stdIn.nextInt();
			stdIn.nextLine();
		}
		boolean append = (choice==0)? true : false;
		
		//get the file name
		if (append)
			System.out.print("Please enter the name of file to append: ");
		else
			System.out.print("Please enter the name of file to prepend: ");
			
		String fileName = stdIn.nextLine();
		
		//create another scoreboard using the file
		ScoreBoard newBoard = boardFromFile(fileName);
		//System.out.println(newBoard);
		
		//combine based on user choice
		if (newBoard!=null && board.combine(newBoard, append)){
			System.out.println("New records added / applied. ");
		}
		else{
			//null new board or other errors
			System.out.println("New records cannot be added / applied. ");		
		}
	}

}
