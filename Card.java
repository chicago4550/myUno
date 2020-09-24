/* Card.java
 * Name: Sample Code
 */

public class Card {
    public static final int MIN_VALUE = 0;
    public static final int MAX_VALUE = 9;
    public static final String[] COLORS = {"blue", "green", "red", "yellow"};
    private String color;
    private int value;
    public Card(String color, int value) {
        setColor(color);
        setValue(value);
    }
    public String getColor() {
        return this.color;
    }
    public int getValue() {
        return this.value;
    }
    private static boolean isValidColor(String color) {        
        for (int i = 0; i < COLORS.length; i++) {
            if (color.equals(COLORS[i])) {
                return true;
            }
        }
        return false;
    }
    public void setColor(String color) {
        if (isValidColor(color)) {
            this.color = color;
        } else {
            throw new IllegalArgumentException();
        }
    }
    public void setValue(int value) {
        if ((value > MAX_VALUE) || (value < MIN_VALUE)) {
            throw new IllegalArgumentException();
        } else {
            this.value = value;
        }
    }
    
    public String toString() {
        return this.color + " " + this.value;
    }
    public boolean matches(Card card1) {
        if (card1 == null) {
            return false;
        }
        if ((card1.getValue() == this.getValue()) || (card1.getColor() == this.getColor())) {
            return true;
        }
        return false;
    }
    
    public boolean equals(Card card1) {
        if (card1 == null) {
            return false;
        }
        if ((card1.getValue() == this.getValue()) && (card1.getColor() == this.getColor())) {
            return true;
        }
        return false;
    }
}
    