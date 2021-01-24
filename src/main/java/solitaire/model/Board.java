package solitaire.model;

import solitaire.model.cards.Card;
import solitaire.model.cards.FoundationCard;
import solitaire.model.cards.Suit;
import solitaire.model.cards.TableauCard;

import java.util.ArrayList;

public class Board {
    public final int tableauSize = 7;
    public final int foundationSize = 4;
    private FoundationCard[] foundation;
    private TableauCard[] tableau;
    private ArrayList<Card> stack;
    private int stackPtr;

    public Board() {
        stack = new ArrayList<>();
        createEmptyBoard();
    }

    public FoundationCard[] getFoundation() {
        return foundation;
    }

    public TableauCard[] getTableau() {
        return tableau;
    }

    public ArrayList<Card> getStack() {
        return stack;
    }

    public int getStackPtr() {
        return stackPtr;
    }

    public void createEmptyBoard() {
        stack.clear();
        stackPtr = -1;
        foundation = new FoundationCard[foundationSize];
        tableau = new TableauCard[tableauSize];

        for(int i = 0; i < foundationSize; i++)
            foundation[i] = new FoundationCard();
        for(int i = 0; i < tableauSize; i++)
            tableau[i] = new TableauCard();
    }

    public void createNewBoard() {
        createEmptyBoard();
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 1; i <= 52; i++)
            list.add(i);
        java.util.Collections.shuffle(list);

        Suit[] suit = new Suit[]{Suit.DIAMONDS, Suit.HEARTS, Suit.CLUBS, Suit.SPADES};
        int pileNumber = 0;
        int[] arr = {1, 3, 6, 10, 15, 21, 28};

        // making tableau
        for(int j = 0; j < 28; j++) {
            int i = list.get(j);
            Card card = new Card(suit[(i-1)/13], ((i-1)%13)+1, false);
            if(arr[pileNumber] == j+1)
                card.swapVisibility();

            card.setAbove(tableau[pileNumber].getLast());
            tableau[pileNumber].getLast().setBelow(card);
            if(arr[pileNumber] == j+1)
                pileNumber++;
        }

        // making stack
        for(int j = 28; j < 52; j++) {
            int i = list.get(j);
            Card card = new Card(suit[(i-1)/13], ((i-1)%13)+1);
            stack.add(card);
        }

        System.out.println("New game created. GLHF!");
    }

    private ArrayList<Position> checkFoundation(Card card) {
        ArrayList<Position> list = new ArrayList<>();
        if(!card.isLast())
            return list;

        for(int i = 0; i < foundationSize; i++) {
            Card last = foundation[i].getLast();
            if(last.getSuit().isSame(card.getSuit()) && last.getRank()+1 == card.getRank())
                list.add(new Position(0, i, 0));
        }

        return list;
    }

    private ArrayList<Position> checkTableau(Card card) {
        ArrayList<Position> list = new ArrayList<>();
        if(!card.isVisible())
            return list;

        for(int i = 0; i < tableauSize; i++) {
            Card last = tableau[i].getLast();
            if(last.getSuit().isOpposite(card.getSuit()) && last.getRank()-1 == card.getRank())
                list.add(new Position(1, i, tableau[i].getSize()));
        }

        return list;
    }

    public ArrayList<Position> getPositions(Card card) {
        ArrayList<Position> list = new ArrayList<>();
        if(card instanceof FoundationCard || card instanceof TableauCard)
            return list;

        list.addAll(checkFoundation(card));
        list.addAll(checkTableau(card));
        return list;
    }

    public boolean checkEndgame() {
        for(int i = 0; i < foundationSize; i++) {
            if(foundation[i].getSize() != 13)
                return false;
        }
        return true;
    }

    public void performMove(Card card, Position position) {
        System.out.println("Move: " + Card.getName(card.getNumber()));
        if(card.inStack()) {
            stack.remove(stackPtr);
            stackPtr--;
        }
        if(card.getAbove() != null) {
            if(!card.getAbove().isVisible())
                card.getAbove().swapVisibility();
            card.getAbove().setBelow(null);
        }

        card.setAbove(null);

        Card to;
        if(position.isFoundation())
            to = foundation[position.getPile()].getLast();
        else
            to = tableau[position.getPile()].getLast();

        to.setBelow(card);
        card.setAbove(to);
    }

    // move pointer on stack
    public void performMove() {
        System.out.println("Move: stack clicked");
        if(stackEnded())
            stackPtr = -1;
        else
            stackPtr++;
    }

    public boolean stackEnded() {
        return stack.size() == 0 || stackPtr == stack.size()-1;
    }

    public boolean isCardPointed(Card card) {
        if(stackPtr == -1)
            return false;
        return stack.get(stackPtr) == card;
    }
}
