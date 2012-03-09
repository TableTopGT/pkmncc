package org.tabletop.pkmncc;

public class Battle {
	public Player player1;
	public Player player2;
	public Player winner = null;
	public RFIDListener cardListener;
	
	
	public Battle(){
		//instantiate players
		player1 = new Player();
		player2 = new Player(player1);
		player1.setOpponent(player2);
	}
	
	public Player continueBattle(){
		player1turn1();
		while (winner == null){
			anyTurn(player1);
			//make sure player 1 didn't win before completing the loop
			if (winner == null){
			anyTurn(player2);
			}
		}
		return winner;
	}
	
	public void player1turn1(){
		
	}
	
	public void anyTurn(Player thisPlayer){
		
	}
	
}
