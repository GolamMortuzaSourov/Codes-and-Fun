import java.util.ArrayList;
import java.util.List;
/**

ScoreBoard class is a representation of a player's ScoreBoard in a game.

@author Golam Mortuza Sourov
*/
public class ScoreBoard {
	/**
	* game count.
	*/
	private int gameCount;
	/**
	* a dynamic array to keep record.
	*/
	private ThreeTenDynArray<PlayerRec> records;
	/**
	* summation of scores.
	*/
	private int sum = 0;
	/**
	* finds player index.
	*/
	private int findplayerindex=0;
	/**
	* Constructor.
	* @param gameCount number of games
	*/
	public ScoreBoard(int gameCount) {
		if (gameCount <= 0) {
			throw new IllegalArgumentException("Must have at least one game!");
		}
		this.gameCount = gameCount;
		this.records = new ThreeTenDynArray<>();
	}
	/**
	* name of player.
	* @return  player count as an integer
	*/
	public int playerCount() {
		System.out.println(records.size());
		return records.size();
	}
	/**
	* name of game count.
	* @return  game count as an integer
	*/
	public int gameCount() {
		return gameCount;
	}
	/**
	* name of player.
	* @param player something
	*/
	public void addPlayer(PlayerRec player) {
		int index = records.firstIndexOf(player);
		if (index >= 0) {
			records.insert(index, player);

		}
	}
	/**
	* name of player.
	* @param index index of the player
	* @return  PlayerRec object
	*/
	public PlayerRec getPlayer(int index) {
		if (index >= 0 && index < records.size()) {
			return records.get(index);
		}
		return null;
	}
	/**
	* name of player.
	* @param name name of the player
	* @return  player count as an PlayerRec
	*/
	public PlayerRec findPlayer(String name) {

		for(int counter=0; counter<records.size();counter++) {
			PlayerRec player=records.get(counter);
			if (player.name().equals(name)) {
				findplayerindex=counter;
				return player;
			}
		}
		return null;
	}
	/**
	* name of player.
	* @param name name of the player
	* @return  player count as an boolean
	*/
	public boolean removePlayer(String name) {
		PlayerRec player = findPlayer(name);
		if (player != null) {
			records.remove(findplayerindex);
			return true;
		}
		return false;
	}
	/**
	* name of player.
	* @param name name of the player
	* @param game game index
	* @param newScore new Score
	* @return  player count as an boolean
	*/
	public boolean changeScore(String name, int game, int newScore) {
		PlayerRec player = findPlayer(name);
		if (player != null && game >= 0 && game < gameCount) {
			player.replaceScore(game, newScore);
			return true;
		}
		return false;
	}
	/**
	* name of player.
	* @return  player count as an integer
	*/
	public int topTotalScore() {
		int maxScore = -100;
		int sum=0;
		for(int counter=0; counter<records.size();counter++) {
			PlayerRec player=records.get(counter);
			maxScore = Math.max(maxScore, player.totalScore());
			//sum=sum+maxScore;
		}
		return maxScore;
	}
	/**
	* name of player.
	* @return  player count as an ThreeTenDynArray
	*/
	public ThreeTenDynArray<String> topPlayers() {
		int maxScore = topTotalScore();
		int count=0;
		ThreeTenDynArray<String> topPlayers = new ThreeTenDynArray();
		if (maxScore >= 0) {
			for(int counter=0; counter<records.size();counter++) {
				PlayerRec player=records.get(counter);
				if (player.totalScore() == maxScore) {
					topPlayers.insert(counter,player.name());
				}
			}
		}
		return topPlayers;
	}
	/**
	* name of player.
	* @param game game index
	* @return  player count as an ThreeTenDynArray
	*/
	public ThreeTenDynArray<String> getGame(int game) {
		if (game >= 0 && game < gameCount) {
			ThreeTenDynArray<String> scores = new ThreeTenDynArray();
			for(int counter=0; counter<records.size();counter++) {
				PlayerRec player=records.get(counter);
				scores.insert(counter,Integer.toString(player.getScore(game)));
			}
			return scores;
		}
		return null;
	}
	/**
	* name of player.
	* @param game game index
	* @return  player count as an integer
	*/
	public int getGameTotal(int game) {
		if (records.size()==0 || game < 0 || game >= gameCount) {
			return -1;
		}
		if (game >= 0 && game < gameCount) {

			for(int counter=0; counter<records.size();counter++) {
				PlayerRec player=records.get(counter);
				sum += player.getScore(game);
			}

		}
		return sum;
	}
	/**
	* name of player.
	* @param game game index
	* @return  player count as an integer
	*/
	public int getGameMax(int game){
		int max = Integer.MIN_VALUE;
		for(int counter=0; counter<records.size();counter++) {
			PlayerRec player=records.get(counter);
			if (player.getScore(game) > max)
			    max = player.getScore(game);
		}
		return max;
	}
	/**
	* name of player.
	* @param game game index
	* @return  player count as an integer
	*/
	public int getGameMin(int game) {
		System.out.println("check");
		if (records.size()==0 || game < 0 || game >= gameCount) {
			return -1;
		}
		System.out.println("check");

		int min = Integer.MAX_VALUE;
		PlayerRec player=records.get(game);
		int playerSize= player.count();
		for(int counter=0; counter<playerSize;counter++) {
			//PlayerRec player=records.get(counter);
			int score = player.getScore(game);
			if (score < min) {
				min = score;
			}
		}

		return min;
	}
	/**
	* name of player.
	* @param another a scoreboard to be added
	* @param append decides whether to append or prepend
	* @return  player count as an boolean
	*/
	public boolean combine(ScoreBoard another, boolean append) {
		if (this.gameCount != another.gameCount) {
			return false;
		}
		for (int i = 0; i < another.records.size(); i++) {
			PlayerRec player = another.records.get(i);
			int existingPlayerIndex = i;
			if (existingPlayerIndex != -1) {
				for (int j = 0; j < this.gameCount; j++) {
					int newScore = player.getScore(j);
					int currentScore = this.records.get(existingPlayerIndex).getScore(j);
					this.records.get(existingPlayerIndex).addScore(Math.max(newScore, currentScore));
				}
			} else {
				if (append) {
					this.records.append(player);
				} else {
					this.records.insert(0, player);
				}
			}
		}
		return true;
	}




	//******************************************************
	//*******     BELOW THIS LINE IS PROVIDED code   *******
	//*******             Do NOT edit code!          *******
	//*******		   Remember to add JavaDoc		 *******
	//******************************************************

	/**
	* name of player.
	* @param game game index
	* @return  player count as an String
	*/
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
	/**
	* name of player.
	* @param args argument
	*/
	public static void main(String args[]){
		//example tests
		//remember to change and/or add more of your test cases

		//create a board
		ScoreBoard board = new ScoreBoard(2);
		if (board.playerCount() == 0 && board.gameCount() == 2
		    && board.getGameMin(0) == -1 ){
			System.out.println("Yay 1");
		}


		//name of player.
		PlayerRec player1 = new PlayerRec("George");
		//add Score
		player1.addScore(1);
		//add Score
		player1.addScore(2);
		//add player
		board.addPlayer(player1);

		if (board.playerCount() == 1  && board.topTotalScore()== 3  && board.gameCount() == 2
		    && board.getGameMin(0) == 1 ){
			System.out.println("Yay 2");
		}


		//another scoreboard.
		ScoreBoard another = new ScoreBoard(2);

		//another player.
		PlayerRec player2 = new PlayerRec("Gorge");
		player2.addScore(2);
		player2.addScore(1);
		another.addPlayer(player2);

		//name of player.
		PlayerRec player3 = new PlayerRec("Georg");
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
