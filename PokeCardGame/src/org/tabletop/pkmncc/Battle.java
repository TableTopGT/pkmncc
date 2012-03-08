package org.tabletop.pkmncc;

public class Battle {
	
	public Player winner = null;
	public boolean battleOver = false;
	
	//create players
	public Player player1 = new Player();
	public Player player2 = new Player();
	//public RFIDListener newCard = null;
	public Card playedCard = null;
	
	//create buttons

	
	//create turn tracker
	public boolean player1turn = true;
	
	
	
	

	/**
	 * @param args
	 */
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		while (winner == null){
			//if its player 1's turn
			if (player1turn==true){
				player1.setMyTurn(true);
				//check for newly scanned cards
				if (newCard != null){
					//get the card and add it to the player
					playedCard = newCard.getCard();
					player1.addCard(playedCard);
					
					//reset card trackers
					newCard = null;
					playedCard = null;
				}
				
				
				
			}
			//if its player 2's turn
			else {
				
			}
			
			battleOver = true;
		
	}
	}
	*/
	public boolean getBattleOver(){
		if (winner != null && battleOver == true)	{
			return true;
			}
		else {
			return false;
		}
		}
	public Player getWinner(){
		if (winner == null){
			return null;
		}
		else {
			return winner;
		}
	}
}
