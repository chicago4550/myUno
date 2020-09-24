/* ComputerPlayer.java
 * Name: Sample Code
 */


import java.util.*;

public class ComputerPlayer extends Player {
  
  private Card[] computerCardArray;
  
  public ComputerPlayer(String playerName) {
    super(playerName);
    computerCardArray = new Card[Uno.MAX_CARDS];

  }

  public String toString() {//added an overriding toString that says not you
    return "Computer (not you)";
  }

  public int getPlay(Scanner console, Card card1) {
    System.out.println("comp getting play");
    int bestOption = -1;
    printHand();
    String requiredColor = card1.getColor();
    int requiredValue = card1.getValue();

    for (int i = 0; i < computerCardArray.length; i++) {
      System.out.println("function");
      if (computerCardArray[i] == null) {
        return bestOption;
      }
      Card card2 = computerCardArray[i];
      String currentColor = card2.getColor();
      int currentValue = card2.getValue();
      if (((requiredColor.equals(currentColor)) || (requiredValue == currentValue))) {//got the comp to not just draw
        //bestOption = i;
        System.out.println("foundMatchingCard");
        return i;

      }
      if ((requiredColor.equals(currentColor)) && (bestOption != -1)) {
        Card card3 = computerCardArray[bestOption];
        int chosenValue = card3.getValue();
        if (chosenValue > currentValue) {
          bestOption = i;
        }
      }      
    }
    return bestOption;
  }
  
  public void printHand() {
    int amount = super.getNumCards();
    if (amount == 1) {
      System.out.println(super.getName() + "'s hand: " + amount + " card");
    } else {
      System.out.println(super.getName() + "'s hand: " + amount + " cards");
    }
  }
}