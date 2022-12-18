import java.util.*;
public class Deck{
  
  //arrays to track the color and number of a given card out of 108
  private int[] cardNumber = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14};
  private String[] cardColor = {"Red", "Red", "Red", "Red", "Red", "Red", "Red", "Red", "Red", "Red", "Red", "Red", "Red", "Null", "Yellow", "Yellow", "Yellow", "Yellow", "Yellow", "Yellow", "Yellow", "Yellow", "Yellow", "Yellow", "Yellow", "Yellow", "Yellow", "Null", "Green", "Green", "Green", "Green", "Green", "Green", "Green", "Green", "Green", "Green", "Green", "Green", "Green", "Null", "Blue", "Blue", "Blue", "Blue", "Blue", "Blue", "Blue", "Blue", "Blue", "Blue", "Blue", "Blue", "Blue", "Null", "Red", "Red", "Red", "Red", "Red", "Red", "Red", "Red", "Red", "Red", "Red", "Red", "Null", "Yellow", "Yellow", "Yellow", "Yellow", "Yellow", "Yellow", "Yellow", "Yellow", "Yellow", "Yellow", "Yellow", "Yellow", "Null", "Green", "Green", "Green", "Green", "Green", "Green", "Green", "Green", "Green", "Green", "Green", "Green", "Null", "Blue", "Blue", "Blue", "Blue", "Blue", "Blue", "Blue", "Blue", "Blue", "Blue", "Blue", "Blue", "Null"};
  
  //list of numbers 0-107 pertaining to cards
  private ArrayList<Integer> gameDeck = new ArrayList<Integer>();
  
  //tracks what card in the deck is on top
  private int cardCount = 0;
  
  //holds the discard pile for subsequent shuffles
  public Vector<Integer> discard = new Vector<Integer>();
  
  //creates a basic deck with numbers 0-107 and randomizes their order
  public void createDeck(){
    for (int i = 0; i<108; i++){
      gameDeck.add(i);
    }
    Collections.shuffle(gameDeck);
  }
  
  //adds a card to a hand and progresses the card count
  public void draw(Vector<Integer> x){
    x.add(gameDeck.get(cardCount));
    nextCard();
  }

  //re-shuffles the deck after it has been emptied
  public void reshuffle() {
	  gameDeck.clear();
	  for (int i = 0; i<discard.size(); i++){
	      gameDeck.add(discard.get(i));
	  }
	  Collections.shuffle(gameDeck);
  }
  //returns the top card
  public int topCard(){
    return gameDeck.get(cardCount);
  }

  //card count progression for use in other classes
  public void nextCard(){
	  if(cardCount < gameDeck.size()) {
		  cardCount++;
	  }
	  else {
		  cardCount = 0;
		  reshuffle();
	  }
  }

  //returns a given card's number
  public int getNumber(int x){
    return cardNumber[x];
  }

  //returns a given card's color
  public String getColor(int x){
    return cardColor[x];
  }
}