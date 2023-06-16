// TO DO: add your implementation and JavaDocs.

public class ScoreBoard {
	
	//number of games
	private int gameCount;
	
	//player records - you MUST use this for credit!
	//Do NOT change the name or type
	//NOTE: you cannot use any arrays or JCF instances in your implementation.
	private ThreeTenDynArray<PlayerRec> records;
	
	// ADD MORE PRIVATE MEMBERS HERE IF NEEDED!
	
	public ScoreBoard(int gameCount){
		//Constructor
		// set the game count and initialize the records to be an empty list
		
		// - Throw IllegalArgumentException if gameCount is not positive 
		// - Use this _exact_ error message for the exception
		//   (quotes are not part of the message):
		//    "Must have at least one game!"
		
	}
	
	
	public int playerCount(){
		//report number of players 
		//O(1)

		return -1; //default return, remove or update as needed

	}
	
	public int gameCount(){
		//report number of games 
		//O(1)

		return -1; //default return, remove or update as needed
	}

	
	
	public void addPlayer(PlayerRec player){
		// append new player into record
		// if player is already present, updated the record 
		//   - assume each player has a unique name, i.e. matching names means same player
		
		// O(N) where N is the number of players present
		
	}
	
	public PlayerRec getPlayer(int index){
	
		//return player record corresponding to index
		//return null for invalid indexes
		
		//O(1)
		
		return null; //default return, remove or update as needed
	
	}

	public PlayerRec findPlayer(String name){
		//find and return player record with the matching name
		//return null if not present
		
		// O(N) where N is the number of players present
		
		return null; //default return, remove or update as needed
	}

	public boolean removePlayer(String name){
		//remove player with the matching name
		//return true if a record is removed successfully; false otherwise
		
		// O(N) where N is the number of players present
		
		return false; //default return, remove or update as needed
		
	}
	
	
	public boolean changeScore(String name, int game, int newScore){
		//set the score of the given game for the player with a matching name
		//return false if newScore cannot be set for any reason 
		// (e.g player /game not present);   return true otherwise
		
		// O(N) where N is the number of players present

		return false; //default return, remove or update as needed
		
	}
	
	public int topTotalScore(){
		//return largest total score among all players
		// return -1 if no player present
			
		// O(N) where N is the number of players present

		return -3; //default return, remove or update as needed
		
	}

	
	public ThreeTenDynArray<String> topPlayers(){
		//return the list of players (names only) with the top total score
		// if no player present, return an empty list
		// if there are multiple players, keep the names in the same order as 
		//   the players in the current record
		
		// O(N) where N is the number of players present 
		//   _if_ appending to the list to return is O(1)
		
		return null; //default return, remove or update as needed
			
	}
	
	public ThreeTenDynArray<Integer> getGame(int game){
		// return all scores for the given game index as a list
		// - if multiple players are present, keep scores in the same order as 
		//    order of players in records 
		// - return an empty list if no players present or game index is invalid
		
		// O(M) where M is the number of players 
		//   _if_ appending to the list to return is O(1)

		return null; //default return, remove or update as needed
	
	
	}

	public int getGameTotal(int game){
		// return the sum of all players' scores of the given game
		// - return -1 if no players present or game index is invalid
		
		// O(M) where M is the number of players 
	
		return -10; //default return, remove or update as needed
		
	}
	
	public int getGameMax(int game){	
		// return the max score of the given game
		// - return -1 if no players present or game index is invalid
		
		// O(M) where M is the number of players 
	
		return -10; //default return, remove or update as needed
		
		
	}

	public int getGameMin(int game){	
		// return the min score of the given game
		// - return -1 if no players present or game index is invalid
		
		// O(M) where M is the number of players 
	
		return -10; //default return, remove or update as needed

	
	}

	public boolean combine(ScoreBoard another, boolean append){
		// add records from another scoreboard into the current one
		//  - if a player is already present, update the record
		//  - otherwise, 
		//       -if append is false, records from another should be added 
		//        to the start of the current records but keep their original order
		//       -if append is true, add new records to the end of the list
		//  Check main() below and description document for examples.
		
		// return false if two score boards cannot be combined (game count mismatch etc.); 
		// return true otherwise
		
		// Although we do not have a big-O requirement for this method, you should 
		// practice code reuse and utilize existing operations as much as possible.
		 
		return false; //default return, remove or update as needed
				
	}
	

	//******************************************************
	//*******     BELOW THIS LINE IS PROVIDED code   *******
	//*******             Do NOT edit code!          *******
	//*******		   Remember to add JavaDoc		 *******
	//******************************************************

	
	public String gameToString(int game){
		if (game<0 || game>gameCount()-1)
			return ("Game index " + game + " invalid!");
			
		StringBuilder s = new StringBuilder("Game [" + game + "]: ");
		s.append("  "+ getGame(game).toString());
		s.append("\n  Game Total: "+ getGameTotal(game) + "\n");	
		s.append("  Game Max: "+ getGameMax(game) + "\n");	
		s.append("  Game Min: "+ getGameMin(game) + "\n");	
	
		return s.toString().trim();
	}
	
	@Override
	public String toString(){
		StringBuilder s = 
			new StringBuilder("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n");
		s.append("Game Count: " + gameCount() + "\t");
		s.append(" Player Count: " + playerCount() + "\n");
		s.append("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n");
		for (int i=0; i<playerCount(); i++){
			s.append("[" + i +"]");
			s.append(records.get(i).toString());
			if (i!=playerCount()-1)
				s.append("\n------------------------------------------------------------------\n");
		}
		s.append("\n%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n");
		return s.toString().trim();
		
	}



	//******************************************************
	//*******     BELOW THIS LINE IS TESTING CODE    *******
	//*******      Edit it as much as you'd like!    *******
	//*******		Remember to add JavaDoc			 *******
	//******************************************************

	public static void main(String args[]){
		//example tests
		//remember to change and/or add more of your test cases
		
		//create a board
		ScoreBoard board = new ScoreBoard(2);
		if (board.playerCount() == 0 && board.gameCount() == 2
			&& board.getGameMin(0) == -1 ){			
			System.out.println("Yay 1");		
		}

		//create a player
		PlayerRec player1 = new PlayerRec("George");
		//addScore
		player1.addScore(1);
		player1.addScore(2);
		//add player
		board.addPlayer(player1);
		if (board.playerCount() == 1 && board.gameCount() == 2  
			&& board.getGameMin(0) == 1 && board.topTotalScore()== 3){			
			System.out.println("Yay 2");		
		}
		
		//another board
		ScoreBoard another = new ScoreBoard(2);
		PlayerRec player2 = new PlayerRec("A");
		player2.addScore(2);
		player2.addScore(0);
		another.addPlayer(player2);
		
		PlayerRec player3 = new PlayerRec("B");
		player3.addScore(5);
		player3.addScore(10);
		another.addPlayer(player3);
		another.addPlayer(player3);
		
		//prepend, pay attention to the order of records after combination
		if (board.combine(another, false) && board.getPlayer(0).equals(player2)
			&& board.getPlayer(1).equals(player3) && board.getPlayer(2).equals(player1) ){
			System.out.println("Yay 3");		
		}
		
		//uncomment to see details
		//System.out.println(board);
		
		//game-related
		if (board.getGame(1).toString().equals("[0, 10, 2]") 
			&& board.getGameTotal(1) == 12 && board.getGameMax(0) == 5 
			&& board.getGameMin(0) == 1 && board.getGameMin(3) == -1){
			System.out.println("Yay 4");		
		}
		
		//player-related
		if (board.findPlayer("A").equals(player2) && !board.removePlayer("X")
			&& board.removePlayer("B") && board.playerCount() == 2	
			&& board.changeScore("A", 1, 1)
			&& board.topPlayers().toString().equals("[A, George]")){			
			System.out.println("Yay 5");		
			
		}
		
	}
	
}

