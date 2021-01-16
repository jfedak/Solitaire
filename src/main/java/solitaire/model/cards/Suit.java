package solitaire.model.cards;

public enum Suit {
    DIAMONDS, CLUBS, HEARTS, SPADES, NONE;

    public boolean isOpposite(Suit suit) {
        if(this == NONE)
            return true;
        else if(this == DIAMONDS || this == HEARTS)
            return suit == CLUBS || suit == SPADES;
        else
            return suit == DIAMONDS || suit == HEARTS;
    }

    public boolean isSame(Suit suit) {
        if(this == NONE)
            return true;
        else
            return this == suit;
    }
}
