/*
 * Uno.java
 * Name: Sample Code
 */

import java.util.*;

public class Uno {
    /* the number of players in the game */
    public static final int NUM_PLAYERS = 2;
    
    /* cards per player at start of game */
    public static final int NUM_INIT_CARDS = 5;
    
    /* the game ends if a player ends up with this many cards */
    public static final int MAX_CARDS = 10; 
    
    /* the penalty for ending up with MAX_CARDS cards */
    public static final int MAX_CARDS_PENALTY = 25;
    
    /* fields of a Uno object */
    private Scanner console;    // used to read from the console
    private Deck deck;          // the deck of cards used for the game
    private Player[] players;   // the players of the game
    private Card topDiscard;    // card at the top of the discard pile
    
    /*
     * constructor - takes the Scanner to be used to read from the
     * console, a randomSeed for the Deck's random-number generator,
     * and the name of the player.
     */
    public Uno(Scanner console, int randomSeed, String playerName) {
        this.console = console;

        // Create and shuffle the deck.
        deck = new Deck(randomSeed);
        deck.shuffle();
        
        // Create the players.
        players = new Player[NUM_PLAYERS];
        players[0] = new Player(playerName);
        players[1] = new ComputerPlayer("the computer");
        
        // Deal the cards.
        dealHands();
        topDiscard = deck.drawCard();   
    }
    
  
    public void dealHands() {
        for (int i = 0; i < NUM_INIT_CARDS; i++) {
            for (int j = 0; j < NUM_PLAYERS; j++) {
                players[j].addCardToHand(deck.drawCard());
            }
        }
    }
    
    /*
     * play - plays an entire game of Uno
     */
    public void play() {
        printGameState();
        
        while (true) {
            // Each player makes one play.
            for (int i = 0; i < players.length; i++) {
                executeOnePlay(players[i]);
            }
            
            printGameState();
            if (gameOver()) {
                return;
            }
        }
    }
    
 
    public void printGameState() {
        System.out.println();
        for (int i = 0; i < players.length; i++) {
            players[i].printHand();
        }
        System.out.println("discard pile: ");
        System.out.println("  " + topDiscard);

    }
    
    /*
     * executeOnePlay - carries out a single play by the specified player
     */
    public void executeOnePlay(Player player) {
        while (true) {
            int cardNum = player.getPlay(console, topDiscard);
            
            if (cardNum == -1) {
                System.out.println(player + " draws a card.");
                player.addCardToHand(deck.drawCard());
                return;
            }
            else {
                Card cardToPlay = player.getCardFromHand(cardNum);

                if (cardToPlay.matches(topDiscard)) {
                    System.out.println(player + " discards " + cardToPlay + ".");
                    player.removeCardFromHand(cardNum);
    
                    // The card played is now at the top of the discard pile.
                    topDiscard = cardToPlay;
                    return;
                } else {
                    System.out.println("invalid play -- " + cardToPlay + 
                                       " doesn't match top of discard pile");
                }
            }
        }
    }
    
  
    public boolean gameOver() {
        for (int i = 0; i < players.length; i++) {
            if (players[i].getNumCards() == 0
              || players[i].getNumCards() == MAX_CARDS) {
                    return true;
            }
        }
        
        return false;
    }
    
    public void reportResults() {
        String winningPLayerName = "";
        for(int i = 0; i < players.length;i++){
            if(players[i].getNumCards() == 0){
                winningPLayerName = players[i].getName();
            }
        }
        System.out.println(winningPLayerName + "won by getting rid of all of their cards");//changed the winning situation to match uno
        /*
        int totalValue = 0;
     
        int winnerIndex = 0;
        int winnerValue = players[0].getHandValue();
        totalValue += winnerValue;
        boolean isTie = false;
        
        for (int i = 1; i < players.length; i++) {
            int playerValue = players[i].getHandValue();
            totalValue += playerValue;
            
            if (playerValue < winnerValue) {
                winnerValue = playerValue;
                winnerIndex = i;
                isTie = false;
            } else if (playerValue == winnerValue) {
                isTie = true;
            }
        }
        
        if (isTie) {
            System.out.println("The game is a tie; no one earns any points.");
        } else {
            System.out.print("The winner is " + players[winnerIndex]);
            System.out.print(", who earns " + (totalValue - winnerValue));
            System.out.println(" points.");
        }*/

    }
    
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);


        int seed = -1;
        if (args.length > 0) {
            seed = Integer.parseInt(args[0]);
        }
        
        System.out.println("Welcome to the game of Uno!");
    
        System.out.print("What's your first name? ");
        String name = console.next();
               
        Uno game = new Uno(console, seed, name);
        game.play();
        game.reportResults();
    }
}