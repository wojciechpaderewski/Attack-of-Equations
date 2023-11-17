package ui.popups;

import Handlers.MouseHandler;
import States.State;
import ui.Score;

public class GameWon extends GameFinished {
    public GameWon(int windowWidth, int windowHeight, Score score, MouseHandler mouseHandler, State state) {
        super(windowWidth, windowHeight, score, mouseHandler, state, "Game won!");
    }
}