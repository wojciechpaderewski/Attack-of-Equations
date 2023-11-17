package States;


public class State {
    GameStates currentState;

    public State(GameStates currentState) {
        this.currentState = currentState;
    }

    public GameStates get() {
        return currentState;
    }

    public void set(GameStates currentState) {
        this.currentState = currentState;
    }
}
