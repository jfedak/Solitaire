package solitaire.model.cards;

public class Card {
    protected Suit suit;
    protected int rank;
    protected boolean visible;
    protected Card below;
    protected Card above;

    public Card(Suit suit, int rank, boolean visible) {
        this.suit = suit;
        this.rank = rank;
        this.visible = visible;
        below = null;
        above = null;
    }

    public Card(Suit suit, int rank) {
        this(suit, rank, true);
    }


    public Suit getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }

    public boolean isVisible() {
        return visible;
    }

    public void swapVisibility() {
        visible = !visible;
    }

    public void setBelow(Card card) {
        this.below = card;
    }

    public void setAbove(Card card) {
        this.above = card;
    }

    public Card getBelow() {
        return this.below;
    }

    public Card getAbove() {
        return this.above;
    }

    public Card getLast() {
        Card card = this;
        while(card.below != null)
            card = card.below;
        return card;
    }

    public Card getFirst() {
        Card card = this;
        while(card.above != null)
            card = card.above;
        return card;
    }

    public int getSize() {
        int size = 0;
        Card card = getFirst();
        while(card.below != null) {
            card = card.below;
            size++;
        }
        return size;
    }

    public boolean inTableau() {
        Card card = this;
        while(card != null) {
            if(card instanceof TableauCard)
                return true;
            card = card.above;
        }
        return false;
    }

    public boolean inFoundation() {
        Card card = this;
        while(card != null) {
            if(card instanceof FoundationCard)
                return true;
            card = card.above;
        }
        return false;
    }

    public boolean inStack() {
        return !inTableau() && !inFoundation();
    }

    public boolean isLast() {
        return this.below == null;
    }

    public int getNumber() {
        int ans = 0;
        if(suit == Suit.DIAMONDS) ans += 0;
        else if(suit == Suit.HEARTS) ans += 13;
        else if(suit == Suit.CLUBS) ans += 26;
        else if(suit == Suit.SPADES) ans += 39;

        ans += (rank-1);
        return ans;
    }

    public static String getName(int num) {
        String name = "";
        switch((num%13)+1) {
            case 1:
                name += "A";
                break;
            case 11:
                name += "J";
                break;
            case 12:
                name += "Q";
                break;
            case 13:
                name += "K";
                break;
            default:
                name += ((num%13)+1);
        }

        switch(num/13) {
            case 0:
                name += "D";
                break;
            case 1:
                name += "H";
                break;
            case 2:
                name += "C";
                break;
            case 3:
                name += "S";
                break;
        }

        return name;
    }
}
