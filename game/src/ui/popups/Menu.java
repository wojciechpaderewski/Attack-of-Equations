package ui.popups;

import java.awt.Color;
import java.awt.Graphics;
import java.util.function.Function;

import Handlers.MouseHandler;
import States.State;
import ui.Button;

public class Menu extends Popup {
    MouseHandler mouseHandler;
    State state;

    private Button quitButton;
    private Button resumeButton;

    public Menu(int windowWidth, int windowHeight, MouseHandler mouseHandler, State state) {
        super(windowWidth, windowHeight);
        this.mouseHandler = mouseHandler;
        this.state = state;

        this.width = 300;
        this.height = 500;

        initQuitButton();
        initResumeButton();
    }

    private void initQuitButton() {
        quitButton = new Button(mouseHandler);
        quitButton.text = "Quit";
        quitButton.x = this.windowWidth / 2 - quitButton.width / 2;
        quitButton.y = 300;
        quitButton.onClick = (Void) -> {
            onQuitGame.apply(null);
            return null;
        };
    }

    private void initResumeButton() {
        resumeButton = new Button(mouseHandler);
        resumeButton.text = "Resume";
        resumeButton.x = this.windowWidth / 2 - resumeButton.width / 2;
        resumeButton.y = 450;

        resumeButton.onClick = (Void) -> {
            onResumeGame.apply(null);
            return null;
        };
    }

    public Function<Void, Void> onQuitGame;
    public Function<Void, Void> onResumeGame;


    public void render(Graphics graphics) {
        super.render(graphics);
        // draw menu string
        graphics.setColor(Color.white);
        graphics.setFont(graphics.getFont().deriveFont(40f));
        int stringWidth = graphics.getFontMetrics().stringWidth("Menu");
        graphics.drawString("Menu", this.windowWidth / 2 - stringWidth / 2, 220);

        quitButton.render(graphics);
        resumeButton.render(graphics);
    }
}
