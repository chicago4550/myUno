/* Player.java
 * Name: Sample Code
 */
import java.util.*;

public class Player {
  private String playerName;
  private Card[] playerCardArray;
  
  public Player(String playerName) {
    this.playerName = playerName;
    playerCardArray = new Card[Uno.MAX_CARDS];
  }
  
  public String getName() {
    return this.playerName;
  }
  
  public Card[] getPlayerCardArray(){
    return playerCardArray;
  }
  
  public String toString() {
    return this.playerName + "(you)";
  }
  
  public void addCardToHand(Card card1) {
    if (card1 == null) {
      throw new IllegalArgumentException();
    }
    if (playerCardArray[Uno.MAX_CARDS - 1] != null) {
      throw new IllegalArgumentException();
    }
    for (int i = 0; i < playerCardArray.length; i++) {
      if (playerCardArray[i] == null) {
        playerCardArray[i] = card1;
        break;
      }
    }
  }
  public int getNumCards() {
    int count = 0;
    for (int i = 0; i < playerCardArray.length; i++) {
      if (playerCardArray[i] != null) {
        count++;
      } else {
        return count;
      }
    }
    return count;
  }
  
  public int getHandValue() {
    int sum = 0;
    for (int i = 0; i < playerCardArray.length; i++) {
      Card card = playerCardArray[i];
      if (card == null) {
        return sum;
      } else {
        int x = card.getValue();
        sum += x;
      }
    }
    sum += Uno.MAX_CARDS_PENALTY;
    return sum;
  }
  
  public int getPlay(Scanner console, Card card1) {//fixed card input so that it would not throw errors when putting in a string or decimal
    int i = 0;
    Card card2 = playerCardArray[i];
    do {
      System.out.println("What is the number of the card you would like to place? Enter -1 if you would like to draw a new card.");
      String testString = console.nextLine();
      try{
        i = Integer.parseInt(testString);
      }
      catch (Exception e){
        i = -2;
        System.out.println("you have not entered a valid integer");
      }
      if (i >= 0) {
        card2 = playerCardArray[i];
      }
    } while ((i < -1) || (i >= Uno.MAX_CARDS) || (card2 == null));
    return i;
  }   
  
  
  public Card getCardFromHand(int i) {
    Card card = playerCardArray[i];
    if (card == null) {
      throw new IllegalArgumentException();
    }
    return card;
  }
  
  public void printHand(){
    System.out.println(this.playerName + "\'s hand:");//added an apostraphe to show possetion of the hand
    for (int i = 0; i < playerCardArray.length; i++) {
      Card card = playerCardArray[i];
      if (card != null) {
        System.out.println(i + ": " + card.toString());
      } else {
        break;
      }
    }
  }
  
  public Card removeCardFromHand(int position) {
    Card card = playerCardArray[position];
    if (card == null) {
      throw new IndexOutOfBoundsException("Sorry, this position is out of bounds.");
    }
    if (position < Uno.MAX_CARDS) {
      int i = position;
      while (playerCardArray[i + 1] != null) {
        playerCardArray[i] = playerCardArray[i + 1];
        i++;
      }
    }
    return card;
  }
}