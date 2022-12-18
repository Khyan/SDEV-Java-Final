import java.util.*;
public class GameState extends Special{
  
  //vectors for player hands 1-4
  private Vector<Integer> playerHand1 = new Vector<Integer>();
  private Vector<Integer> playerHand2 = new Vector<Integer>();
  private Vector<Integer> playerHand3 = new Vector<Integer>();
  private Vector<Integer> playerHand4 = new Vector<Integer>();
  private Vector<Vector<Integer>> hands = new Vector<Vector<Integer>>(4);
  
  //an integer displaying the card in play
  private int currentCard;
  
  //a variable allowing the card color to be changed by wild cards
  private String cardColor;

  //a list of possible colors for wild cards to select
  private String[] colors = {"Red", "Yellow", "Green", "Blue"};
  
  //checks if cards have been drawn by a card that demands it
  private boolean drawn = true;
  
  //checks if a player has been skipped yet
  private boolean skipped = true;
  
  //determines if the player has drawn instead of played
  private boolean playerDrawn = false;
  
  //sets the card color
  public void setColor(String x){
    cardColor = x;
  }
  
  //returns playerDrawn value
  public boolean drew() {
	  return playerDrawn;
  }
  
  //checks to see if skipped or drawn rules have been followed
  public void checkSpecial() {
	  if(skipped == false) {
		  skip();
		  skipped = true;
	  }
	  if(drawn == false) {
		  if(getNumber(currentCard) == 12) {
			  draw2(hands.get(getTurn()-1));
			  drawn = true;
		  }
		  if(getNumber(currentCard) == 14) {
			  draw4(hands.get(getTurn()-1));
			  drawn = true;
		  }
	  }
  }
  //populates hands with player hands and populates player hands
  public void gameStart() {
	  hands.add(playerHand1);
	  hands.add(playerHand2);
	  hands.add(playerHand3);
	  hands.add(playerHand4);
	  createDeck();
	  for (int i = 0; i < 4; i++) {
		  for (int j = 0; j < 7; j++) {
			  draw(hands.get(i));
		  }
	  }
	  currentCard = topCard();
      cardColor = getColor(topCard());
      Random rand = new Random();
      if(cardColor == "Null") {
    	  cardColor = colors[rand.nextInt(4)];
      }
      nextCard();
  }
  //checks if a player's hand is empty to determine a winner
  public boolean checkWinner(){
    if ((hands.get(getTurn()-1)).size() == 0){
      return true;
    }
    else{
      return false;
    }
  }

  //sets the current card
  public void setCard(int x){
    currentCard = x;
  }
  
  //returns skipped value
  public boolean getSkipped() {
	  return skipped;
  }
  
  //returns drawn value
  public boolean getDrawn() {
	  return drawn;
  }
  //gets current card
  public int getCard(){
	  return currentCard;
  }
  
  //gets the current player
  public Vector<Integer> getPlayer() {
	  return hands.get(getTurn() - 1);
  }
  
  //checks through a player's hand to find the first playable card and has the player draw if there are no playable cards
  public void playableCard(Vector<Integer> x){
    playerDrawn = false;
	int count = 0;
    Random rand = new Random();
    while(true){
      if(count<x.size()){
        if (getNumber(x.get(count))>12){
          if(getNumber(x.get(count))==14){
            drawn = false;
          }
          currentCard = x.get(count);
          discard.add(x.get(count));
          x.remove(count);
          cardColor = colors[rand.nextInt(4)];
          break;
        }
        else if(getNumber(currentCard) == getNumber(x.get(count))||cardColor == getColor(x.get(count))){
          if(getNumber(x.get(count)) == 11){
            reverse();
          }
          if(getNumber(x.get(count)) == 10){
            skip();
          }
          currentCard = x.get(count);
          cardColor = getColor(x.get(count));
          discard.add(x.get(count));
          x.remove(count);
          break;
        }
        else{
          count++;
        }
      }
      else{
        draw(x);
        playerDrawn = true;
        break;
      }
    }
  }
}