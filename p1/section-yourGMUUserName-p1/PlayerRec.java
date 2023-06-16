// TO DO: add your implementation and JavaDocs.

public class PlayerRec {

	//name of player
	private String name;
	
	
	//score record - you MUST use this for credit!
	//Do NOT change the name or type
	//NOTE: you cannot use any arrays or JCF instances in your implementation.
	private ThreeTenDynArray<Integer> scores;

	// ADD MORE PRIVATE MEMBERS HERE IF NEEDED!
	

	public PlayerRec(String name){
		// Constructor
		// set the name of the player and initialize the scores to be an empty list
		// you can assume name is not null
		
		// if you have other private members, initialize those as well
		
		
	}

	public int count(){
		//report the number of scores
		// O(1)
		
		return -1; //default return, remove or update as needed
	}
	
	public String name(){
		//report the name of the player
		// O(1)
		
		return ""; //default return, remove or update as needed
	}
	
	public int totalScore(){
		//report the sum of all scores of the player
		//O(1)
		return -1; //default return, remove or update as needed
		
	}
	
	public boolean addScore(int score){
		// append a score at the end of scores record
		// return true if score appended successfully;
		// return false for any errors (e.g. a negative score)
		
		// amortized O(1) 
		return false; //default return, remove or update as needed

	}
	
	public boolean replaceScore(int game, int newScore){
		//replace the score of the specified game to be newScore
		//return false for any errors; return true otherwise
		
		//O(1)
		return false; //default return, remove or update as needed
		
	}
	
	public int getScore(int game){
		//return score of the specified game
		//return -1 for invalid game index
		
		//O(1)
		return -2; //default return, remove or update as needed
		
	}
	
	public ThreeTenDynArray<Integer> getTopGames(){
		//return the list of game indexes with the highest score of this player
		//if the player has no scores, return an empty list of size 0
		//if there is a tie, the list should include all indexes in the ascending order
		
		//O(n) where n is the number of scores in record _if_ appending to list is O(1)
		return null; //default return, remove/change as needed
		
		
	}
	
	@Override
	public boolean equals(Object o) {
		// Two PlayerRecs are equal if they have matching player names: return true
		// return false otherwise
		
		// remember to check whether the incoming object is an instance of this class
				
		return false; //default return, remove/change as needed
		
	}

	//******************************************************
	//*******     BELOW THIS LINE IS PROVIDED code   *******
	//*******             Do NOT edit code!          *******
	//*******		   Remember to add JavaDoc		 *******
	//******************************************************

	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder(name()+",");
		s.append(count() + ",");
		s.append(totalScore() + ",");
		s.append(scores.toString());
		return s.toString().trim();
		
	}

	public String toNiceString() {
		StringBuilder s = new StringBuilder("Player Name: "+ name() + "\n");
		s.append("  Game Count: " + count() + ", ");
		s.append("Total Score: " + totalScore() + "\n");
		s.append("  Scores: ");
		s.append(scores.toString());
		ThreeTenDynArray<Integer> bestGames = getTopGames();
		s.append("\n  Top Games: ");
		s.append(bestGames.toString());
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
	
		//create a player
		PlayerRec player1 = new PlayerRec("George");
		
		if (player1.name().equals("George") && player1.count()==0 
			&& player1.totalScore()==0){
			System.out.println("Yay 1");
		}
		
		//addScore
		if (!player1.addScore(-2) && player1.addScore(2) && player1.addScore(1) 
			&& player1.addScore(5) && player1.count()==3 && player1.totalScore()==8){
			System.out.println("Yay 2");
		}
		
		//uncomment to check details
		//System.out.println(player1);

		//getScore, replaceScore, getTopGames
		if (!player1.replaceScore(5,5)	&& player1.replaceScore(0,5)
			&& player1.getScore(6) == -1 && player1.getScore(0) == 5
			&& player1.getTopGames().toString().equals("[0, 2]")){
			System.out.println("Yay 3");			
		}

		//equals, toString
		PlayerRec player2 = new PlayerRec("Mason");
		PlayerRec player3 = new PlayerRec("George");
		if (!player1.equals(null) && !player1.equals(player2) && player1.equals(player3)
			&& player1.toString().equals("George,3,11,[5, 1, 5]")){
			System.out.println("Yay 4");						
		}
				
		
	}
		
}
