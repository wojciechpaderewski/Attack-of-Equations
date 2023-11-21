package ui.popups;

import java.awt.Color;
import java.awt.Graphics;
import java.util.function.Function;

import Handlers.MouseHandler;
import States.State;
import ui.Button;
import ui.Score;

public class Instruction extends Popup {
    private Button goBackButton;
    private State state;
    private MouseHandler mouseHandler;
    private Score score;

    public Instruction(int windowWidth, int windowHeight, State state, MouseHandler mouseHandler, Score score) {
        super(windowWidth, windowHeight);
        this.state = state;
        this.mouseHandler = mouseHandler;
        this.score = score;
        this.width = 680;
        this.height = 600;

        initGoBackButton();
    }

    private void initGoBackButton() {
        goBackButton = new Button(mouseHandler);
        goBackButton.text = "Go back";
        goBackButton.x = this.windowWidth / 2 - goBackButton.width / 2;
        goBackButton.y = 580;

        goBackButton.onClick = (Void) -> {
            if (state.get() == States.GameStates.INSTRUCTION) {
                state.set(state.getPrevious());
            }
            return null;
        };
    }

    public void render(Graphics graphics) {
        super.render(graphics);
        Color textColor = Color.white;
        graphics.setColor(textColor);

        int titleHeight = 130;

        //set font size
        graphics.setFont(graphics.getFont().deriveFont(30.0f));
        int stringWidth = graphics.getFontMetrics().stringWidth("Instruction");
        graphics.drawString("Instruction", this.windowWidth / 2 - stringWidth / 2, titleHeight);
        
        int paddingLeft = 20;
        int gap = 55;
        graphics.setFont(graphics.getFont().deriveFont(15.0f));
        graphics.drawString("1. Use arrow keys to move", this.windowWidth / 2 - width / 2 + paddingLeft, titleHeight + gap);
        graphics.drawString("2. Hit blocks with equations that have result lower or equl to your score", this.windowWidth / 2 - width / 2 + paddingLeft, titleHeight + 2 * gap);
        graphics.drawString("3. If you hit block with equation that has result higher than your score, you lose one life", this.windowWidth / 2 - width / 2 + paddingLeft, titleHeight + 3 * gap);
        graphics.drawString("4. When block with equation is hit, result of that equation is added to your score", this.windowWidth / 2 - width / 2 + paddingLeft, titleHeight + 4 * gap);
        graphics.drawString("5. Game is over when you lose all of your lifes", this.windowWidth / 2 - width / 2 + paddingLeft, titleHeight + 5 * gap);
        graphics.drawString("6. Game is won when you reach score of "+ score.scoreLimit, this.windowWidth / 2 - width / 2 + paddingLeft, titleHeight + 6 * gap);

        goBackButton.render(graphics);
    }

    public Function<Void, Void> onGoBack;
}
