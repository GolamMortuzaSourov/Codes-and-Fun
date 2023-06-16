/**

PlayerRec class is a representation of a player's score record in a game.

It contains the name of the player and a ThreeTenDynArray to store the scores of the player.

@author Golam Mortuza Sourov
*/
public class PlayerRec {

	/**
	* name of player.
	*/
	private String name;

	/**
	* a dynamic array scores to keep track of score.
	*/
	private ThreeTenDynArray<Integer> scores = new ThreeTenDynArray();//score record - you MUST use this for credit!
	/**
	* name of pointer.
	*/
	private int pointer=0;
	// ADD MORE PRIVATE MEMBERS HERE IF NEEDED!

	/**
	* constructor.
	* @param name name of the player
	*/
	public PlayerRec(String name){
		this.name = name;
		//this.scores = new ThreeTenDynArray<>();
	}
	/**
	* a method to get count of games.
	* @return  number of scores recorded
	*/
	public int count(){
		return scores.size();
	}
	/**
	* name of the player.
	* @return name
	*/
	public String name(){
		return name;
	}
	/**
	* a method to get total score.
	* @return total score of the player
	*/
	public int totalScore(){
		int sum = 0;
		for (int score=0; score<scores.size();score++) {
			sum += scores.get(score);
		}
		return sum;
	}
	/**
	* adds score to the players record.
	* @param score score to be added
	* @return      a boolean value
	*/
	public boolean addScore(int score){
		if (score < 0) {
			return false;
		}
		scores.insert(pointer,score);
		pointer++;
		return true;
	}
	/**
	* returns true or false after replacing score.
	* @param game     serial number of a game
	* @param newScore score to be added
	* @return         a boolean value
	*/
	public boolean replaceScore(int game, int newScore){
		if (game < 0 || game >= scores.size()) {
			return false;
		}
		scores.set(game, newScore);
		return true;
	}
	/**
	*This method returns the score of a specified game.
	*@param game Game number
	*@return Score of the specified game or -1 for invalid game index
	*/
	public int getScore(int game){
		if (game < 0 || game >= scores.size()) {
			return -1;
		}
		return scores.get(game);
	}


	/**
	* a method to get top games.
	* @return  a ThreeTenDynArray object
	*/
	public ThreeTenDynArray<Integer> getTopGames() {

		if (scores.size() == 0) return  new ThreeTenDynArray();


		//keeps track of maximum score.
		int maxScore = Integer.MIN_VALUE;

		//keeps track of maximum index.
		int maxIndex=0;

		//keeps track of maximum score.
		int numberOfmaxScore=0;
		for (int i = 0; i < scores.size(); i++) {
			if (scores.get(i) >= maxScore) {
				maxScore = scores.get(i);
				numberOfmaxScore++;
				//maxIndex=i;
			}
		}
		// a dynamic array to store all the max scores
		ThreeTenDynArray<Integer> topGames = new ThreeTenDynArray<>(numberOfmaxScore);
		for (int i = 0; i < scores.size(); i++) {
			if (scores.get(i) == maxScore) {
				topGames.insert(maxIndex,i);
				maxIndex++;
			}
		}
		return topGames;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof PlayerRec)) return false;
		PlayerRec other = (PlayerRec) o;
		return name().equals(other.name());
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

		if (player1.name().equals("George") && player1.count()==0 && player1.totalScore()==0){
			System.out.println("Yay 1");
		}

		//addScore
		if (!player1.addScore(-2) && player1.addScore(2) && player1.addScore(1) && player1.addScore(5) && player1.count()==3 && player1.totalScore()==8){
			System.out.println("Yay 2");
		}

		//uncomment to check details
		System.out.println(player1);

		//getScore, replaceScore, getTopGames
		if (!player1.replaceScore(5,5)	&& player1.replaceScore(0,5) && player1.getScore(6) == -1 && player1.getScore(0) == 5 && player1.getTopGames().toString().equals("[0, 2]")){
			System.out.println("Yay 3");
		}

		//equals, toString
		PlayerRec player2 = new PlayerRec("Mason");
		PlayerRec player3 = new PlayerRec("George");
		if (!player1.equals(null) && !player1.equals(player2) && player1.equals(player3) && player1.toString().equals("George,3,11,[5, 1, 5]")){
			System.out.println("Yay 4");
		}


	}

}
