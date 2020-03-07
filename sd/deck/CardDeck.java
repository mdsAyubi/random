import java.util.*;

enum Rank {
    ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
}

enum Suit {
    SPADE, DIAMOND, CLUBS, HEART
}

class Card {
    Suit suit;
    Rank rank;

    Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }
}

public class CardDeck {

    List<Card> cards = new ArrayList<>();

    public static CardDeck newInstance() {
        return new CardDeck();
    }

    private CardDeck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    public void print() {
        System.out.println(cards.toString());
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public static void main(String[] args) {

    }

}
