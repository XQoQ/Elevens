import java.lang.reflect.Array;
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

    public boolean replaceSingleCard(ArrayList<Integer> cardToReplace) {
        String jqk = "JQK";
        if (cardToReplace.size() == 3) {
            if (jqk.contains(hand.get(cardToReplace.get(0)).getValue()) && jqk.contains(hand.get(cardToReplace.get(1)).getValue()) && jqk.contains(hand.get(cardToReplace.get(2)).getValue())
            && (!hand.get(cardToReplace.get(0)).getValue().equals(hand.get(cardToReplace.get(1)).getValue())) && (!hand.get(cardToReplace.get(0)).getValue().equals(hand.get(cardToReplace.get(1)).getValue())) && (!(hand.get(cardToReplace.get(1)).getValue().equals(hand.get(cardToReplace.get(2)).getValue())))) {
                shuffle(cardToReplace);
                return true;
            }
        } else if (cardToReplace.size() == 2) {
            if ((hand.get(cardToReplace.get(0)).getValue().equals("A") && hand.get(cardToReplace.get(1)).getValue().equals("10")) || (hand.get(cardToReplace.get(0)).getValue().equals("10") && hand.get(cardToReplace.get(1)).getValue().equals("A"))
                    || (Integer.parseInt(hand.get(cardToReplace.get(0)).getValue()) + Integer.parseInt(hand.get(cardToReplace.get(1)).getValue()) == 11)) {
                shuffle(cardToReplace);
                return true;
            }
        }
        return false;
    }

    public void shuffle(ArrayList<Integer> cardToReplace) {
        for (int i = 0; i < hand.size(); i++) {
            int r = (int)(Math.random() * getDeck().size());
            removeCard(hand.set(cardToReplace.get(0), getDeck().remove(r)));
            cardToReplace.remove(0);
        }
    }

    public boolean checkIfGameOver() {
        boolean j = false;
        boolean q = false;
        boolean k = false;
        boolean eleven = false;
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getValue().equals("J")) {
                j = true;
            } else if (hand.get(i).getValue().equals("Q")) {
                q = true;
            } else if (hand.get(i).getValue().equals("K")) {
                k = true;
            }
            for (int l = i + 1; l < hand.size(); l++) {
                if ((hand.get(i).getValue().equals("A") && hand.get(l).getValue().equals("10")) || (hand.get(i).getValue().equals("10") && hand.get(l).getValue().equals("A"))) {
                    eleven = true;
                } else {
                    try {
                        if (Integer.parseInt(hand.get(i).getValue()) + Integer.parseInt(hand.get(l).getValue()) == 11) {
                            eleven = true;
                        }
                    } catch (NumberFormatException e) {
                        eleven = eleven;
                    }
                }
            }
        }
        return (j && q && k) || eleven;
    }
}
