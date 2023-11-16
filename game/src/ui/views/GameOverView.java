package ui.views;

import Handlers.MouseHandler;
import States.State;
import ui.GameMap;
import ui.Score;

public class GameOverView extends GameFinishedView {
    public GameOverView(GameMap gameMap, Score score, MouseHandler mouseHandler, State state) {
        super(gameMap, score, mouseHandler, state, "Game over!");
    }
}