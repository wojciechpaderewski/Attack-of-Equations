package Interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameMenu {
    static int width = 800;
    static int height = 600;
    private boolean isVisable = false;
    private JFrame frame;
    private Rectangle menuEnterButton = new Rectangle(width / 2 - 50, height / 2 - 25, 100, 50);

    public GameMenu(JFrame frame) {
        this.frame = frame;
        initMenuEnterButton();
    }

    public void exitGame() {}
    public void pauseGame() {}
    public void resumeGame() {}

    public void  initMenuEnterButton () {
        JButton enterButton = new JButton("Menu");  // create the button
        // set fixed size for the button
        enterButton.setBounds(100, 100, 100, 100);
        frame.add(enterButton, BorderLayout.PAGE_END);  // add panel to the frame
    }

    public void renderMenu(Graphics graphics) {
        this.renderMenuEnterButton(graphics);
        this.renderMenuPopup(graphics);
        this.renderPopupButtons(graphics);
    }

    private void renderMenuEnterButton(Graphics graphics) {

    }
    private void renderMenuPopup(Graphics graphics) {
        
    }

    private void renderPopupButtons(Graphics graphics) {

    }
}
