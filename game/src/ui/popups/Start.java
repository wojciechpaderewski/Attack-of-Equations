package ui.popups;

import java.awt.Color;
import java.awt.Graphics;
import java.util.function.Function;

import Handlers.MouseHandler;
import States.State;
import ui.Button;

public class Start extends Popup {
    static Color backgroundColor = Color.gray;
    static String title = "Attack of Equations";
    static int buttonWidth = 200;

    MouseHandler mouseHandler;
    State state;

    private Button quitButton;
    private Button startButton;
    private Button instructionButton;

    public Start(int windowWidth, int windowHeight, MouseHandler mouseHandler, State state) {
        super(windowWidth, windowHeight);
        this.mouseHandler = mouseHandler;
        this.state = state;

        this.width = 700;

        initQuitButton();
        initStartButton();
        initInstructionButton();
    }

    private void initQuitButton() {
        quitButton = new Button(mouseHandler);
        quitButton.text = "Quit";
        quitButton.width = buttonWidth;
        quitButton.x = this.windowWidth / 2 - quitButton.width / 2;
        quitButton.y = 550;
        quitButton.onClick = (Void) -> {
            if (state.get() == States.GameStates.START) {
                System.exit(0);
                return null;
            } else {
                return null;
            }
        };
    }

    private void initStartButton() {
        startButton = new Button(mouseHandler);
        startButton.text = "Start game";
        startButton.width = buttonWidth;
        startButton.x = this.windowWidth / 2 - startButton.width / 2;
        startButton.y = 350;

        startButton.onClick = (Void) -> {
            if (state.get() == States.GameStates.START) {
                onStartGame.apply(null);
                return null;
            } else {
                return null;
            }
        };
    }

    private void initInstructionButton() {
        instructionButton = new Button(mouseHandler);
        instructionButton.text = "Instruction";
        instructionButton.width = buttonWidth;
        instructionButton.x = this.windowWidth / 2 - instructionButton.width / 2;
        instructionButton.y = 450;

        instructionButton.onClick = (Void) -> {
            if (state.get() == States.GameStates.START) {
                state.set(States.GameStates.INSTRUCTION);
                return null;
            } else {
                return null;
            }
        };
    }

    public Function<Void, Void> onStartGame;

    private void renderTitle(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.setFont(graphics.getFont().deriveFont(50f));
        int x = this.windowWidth / 2 - graphics.getFontMetrics().stringWidth(title) / 2;
        int y = 200;
        graphics.drawString(title, x, y);
    }

    public void render(Graphics graphics) {
        super.render(graphics);
        renderTitle(graphics);
        startButton.render(graphics);
        quitButton.render(graphics);
        instructionButton.render(graphics);
    }
}
