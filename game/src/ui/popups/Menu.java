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
    private Button restartButton;
    private Button instructionButton;

    public Menu(int windowWidth, int windowHeight, MouseHandler mouseHandler, State state) {
        super(windowWidth, windowHeight);
        this.mouseHandler = mouseHandler;
        this.state = state;

        this.width = 300;
        this.height = 600;

        initQuitButton();
        initResumeButton();
        initRestartButton();
        initInstructionButton();
    }

    private void initQuitButton() {
        quitButton = new Button(mouseHandler);
        quitButton.text = "Quit";
        quitButton.x = this.windowWidth / 2 - quitButton.width / 2;
        quitButton.y = 580;
        quitButton.onClick = (Void) -> {
            if (state.get() == States.GameStates.MENU) {
                System.exit(0);
                return null;
            } else {
                return null;
            }
        };
    }
    

    private void initResumeButton() {
        resumeButton = new Button(mouseHandler);
        resumeButton.text = "Resume";
        resumeButton.x = this.windowWidth / 2 - resumeButton.width / 2;
        resumeButton.y = 280;

        resumeButton.onClick = (Void) -> {
            if (state.get() == States.GameStates.MENU) {
                onResumeGame.apply(null);
                return null;
            } else {
                return null;
            }
        };
    }

    private void initRestartButton() {
        restartButton = new Button(mouseHandler);
        restartButton.text = "Restart game";
        restartButton.x = this.windowWidth / 2 - restartButton.width / 2;
        restartButton.y = 380;

        restartButton.onClick = (Void) -> {
            if (state.get() == States.GameStates.MENU) {
                onRestartGame.apply(null);
                return null;
            } else {
                return null;
            }
        };
    }

    private void initInstructionButton() {
        instructionButton = new Button(mouseHandler);
        instructionButton.text = "Instruction";
        instructionButton.x = this.windowWidth / 2 - instructionButton.width / 2;
        instructionButton.y = 480;

        instructionButton.onClick = (Void) -> {
            if (state.get() == States.GameStates.MENU) {
                state.set(States.GameStates.INSTRUCTION);
                return null;
            } else {
                return null;
            }
        };
    }

    public Function<Void, Void> onResumeGame;
    public Function<Void, Void> onRestartGame;

    public void render(Graphics graphics) {
        super.render(graphics);
        // draw menu string
        graphics.setColor(Color.white);
        graphics.setFont(graphics.getFont().deriveFont(45f));
        int stringWidth = graphics.getFontMetrics().stringWidth("Menu");
        graphics.drawString("Menu", this.windowWidth / 2 - stringWidth / 2, 180);

        quitButton.render(graphics);
        resumeButton.render(graphics);
        restartButton.render(graphics);
        instructionButton.render(graphics);
    }
}
