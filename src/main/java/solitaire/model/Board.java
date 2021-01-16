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

    public void createEmptyBoard() {
        stack.clear();
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

        Suit[] suit = new Suit[]{Suit.DIAMONDS, Suit.CLUBS, Suit.HEARTS, Suit.SPADES};
        int pileNumber = 0;
        int[] arr = {1, 3, 6, 10, 15, 21, 28};

        // making tableau
        for(int j = 0; j < 28; j++) {
            int i = list.get(j);
            Card card = new Card(suit[(i-1)/13], ((i-1)%13)+1, false);
            if(arr[pileNumber] == j+1)
                card.swapVisibility();

            System.out.println(pileNumber);
            System.out.println(tableau[pileNumber]);
            System.out.println(tableau[pileNumber].getLast());
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
    }

    private ArrayList<Position> checkFoundation(Card card) {
        ArrayList<Position> list = new ArrayList<>();
        if(!card.isLast())
            return list;

        for(int i = 0; i < foundationSize; i++) {
            Card last = foundation[i].getLast();
            if(last.getSuit().isSame(card.getSuit()) && last.getRank()+1 == card.getRank())
                list.add(new Position(0, i, foundation[i].getSize()+1));
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
                list.add(new Position(1, i, tableau[i].getSize()+1));
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

}