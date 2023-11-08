package Handlers;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KeyInputHandler extends KeyAdapter {
    // create map of keys
    private boolean[] keys = new boolean[256];


    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        keys[key] = true;
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        keys[key] = false;
    }
    
    public boolean isKeyPressed(int key) {
        return keys[key];
    }
}
