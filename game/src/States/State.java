package States;


public class State {
    GameStates previousState;
    GameStates currentState;

    public State(GameStates currentState) {
        this.currentState = currentState;
    }
    
    public GameStates get() {
        return currentState;
    }
    
    public GameStates getPrevious() {
        return previousState;
    }
    
    public void set(GameStates currentState) {
        this.previousState = this.currentState;
        this.currentState = currentState;
    }
}
