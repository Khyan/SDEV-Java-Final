import java.util.*;
public class Special extends Deck{
  
  //determines whether the turn order is ascending or descending
  private boolean turnOrder = true;
  private int playerTurn = 1;
  public void draw2(Vector<Integer> x){
    for (int i = 0; i<2; i++){
      x.add(topCard());
      nextCard();
    }
  }

  //adds a card to a hand and progresses the card count 4 times
  public void draw4(Vector<Integer> x){
    for (int i = 0; i<4; i++){
      x.add(topCard());
      nextCard();
    }
  }

  //adds a card to a hand and progresses the card count twice
  public void reverse(){
    if (turnOrder = true){
      turnOrder = false;
    }
    else{
      turnOrder = true;
    }
  }

  //progresses the turn by 1 extra, skipping a player
  public void skip(){
    if(turnOrder == true){
      if (playerTurn<4){
        playerTurn++;
      }
      else{
        playerTurn = 1;
      }
    }
    else{
      if(playerTurn>1){
        playerTurn--;
      }
      else{
        playerTurn = 4;
      }
    }
  }

  //returns the turn order direction
  public boolean getOrder(){
    return turnOrder;
  }

  //returns the player who's turn it is
  public int getTurn(){
    return playerTurn;
  }
}