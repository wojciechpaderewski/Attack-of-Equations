package States;


public class State {
    GameStates currentState;

    public State(GameStates currentState) {
        this.currentState = currentState;
    }

    public GameStates getCurrentState() {
        return currentState;
    }

    public void setCurrentState(GameStates currentState) {
        this.currentState = currentState;
    }
}
