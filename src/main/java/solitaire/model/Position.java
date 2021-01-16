package solitaire.model;

public class Position {
    private int place;
    private int pile;
    private int number;

    public Position(int a, int b, int c) {
        this.place = a;
        this.pile = b;
        this.number = c;
    }


    public boolean isFoundation() {
        return place == 0;
    }

    public boolean isTableau() {
        return !isFoundation();
    }

    public int getPile() {
        return pile;
    }

    public int getNumber() {
        return number;
    }
}
