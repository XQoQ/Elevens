import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.Font;

class DrawPanel extends JPanel implements MouseListener {
    private Hand hand;
    private Rectangle newGameButton;
    private Rectangle replaceButton;

    public DrawPanel() {
        newGameButton = new Rectangle(157, 300, 160, 26);
        replaceButton = new Rectangle(157, 340, 160, 26);
        this.addMouseListener(this);
        hand = new Hand();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 130;
        int y = 20;
        int cardPerRowCount = 0;
        int rowCount = 1;
        for (int i = 0; i < hand.getHand().size(); i++) {
            Card c = hand.getHand().get(i);
            if (cardPerRowCount == 3) {
                x = 130;
                y = 20 + c.getImage().getHeight() * rowCount + 10 * rowCount;
                cardPerRowCount = 0;
                rowCount++;
            }

            if (c.getHighlight()) {
                g.drawRect(x, y, c.getImage().getWidth(), c.getImage().getHeight());
            }
            c.setRectangleLocation(x, y);
            g.drawImage(c.getImage(), x, y, null);
            x = x + c.getImage().getWidth() + 10;
            cardPerRowCount++;
        }
        g.setFont(new Font("Courier New", Font.BOLD, 20));
        g.drawString("GET NEW CARDS", 160, 320);
        g.drawString("REPLACE CARDS", 160, 360);
        g.drawString("CARD LEFT: " + hand.getDeck().size(), 10, 450);
        g.drawRect((int)newGameButton.getX(), (int)newGameButton.getY(), (int)newGameButton.getWidth(), (int)newGameButton.getHeight());
        g.drawRect((int)replaceButton.getX(), (int)replaceButton.getY(), (int)replaceButton.getWidth(), (int)replaceButton.getHeight());

        if (!hand.checkIfGameOver()) {
            g.drawString("GAME OVER", 180, 400);
        }

        if (hand.getDeck().isEmpty()) {
            g.drawString("YOU WIN", 190, 270);
        }
    }

    public void mousePressed(MouseEvent e) {

        Point clicked = e.getPoint();

        if (e.getButton() == 1) {
            if (newGameButton.contains(clicked)) {
                hand = new Hand();
            }

            if (replaceButton.contains(clicked)) {
                ArrayList<Integer> cardsToReplace = new ArrayList<Integer>();
                for (int i = 0; i < hand.getHand().size(); i++) {
                    if (hand.getHand().get(i).getHighlight()) {
                        cardsToReplace.add(i);
                    }
                }
                hand.replaceSingleCard(cardsToReplace);
            }

            for (int i = 0; i < hand.getHand().size(); i++) {
                Rectangle box = hand.getHand().get(i).getCardBox();
                if (box.contains(clicked)) {
                    hand.getHand().get(i).flipHighlight();
                }
            }


        }
    }

    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }
}