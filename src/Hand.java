import java.util.ArrayList;

public class Hand extends Deck{
    private ArrayList<Card> hand;

    public Hand() {
        super();

        this.hand = new ArrayList<Card>();
        for (int i = 0; i < 9; i++) {
            int r = (int)(Math.random() * getDeck().size());
            Card c = getDeck().remove(r);
            hand.add(c);
        }
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void replaceSingleCard(ArrayList<Integer> cardToReplace) {
        String[] jKQ = {hand.get(cardToReplace.get(0)).getValue(), hand.get(cardToReplace.get(1)).getValue(), hand.get(cardToReplace.get(2)).getValue()};

        for (int i = 0; i < hand.size(); i++) {
            int r = (int)(Math.random() * getDeck().size());
            removeCard(hand.set(cardToReplace.get(0), getDeck().remove(r)));
            cardToReplace.remove(0);
        }
    }
}
