package Handlers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.function.Function;


public class MouseHandler extends MouseAdapter {
    LinkedList<Function<MouseEvent, Void>> onClick = new LinkedList<Function<MouseEvent, Void>>();

    public void onClick(Function<MouseEvent, Void> callback) {
        onClick.add(callback);
    }

    public void mouseClicked(MouseEvent e) {
        // if left button is clicked emit
        if (e.getButton() == MouseEvent.BUTTON1) {
            for (int i = 0; i < onClick.size(); i++) {
                onClick.get(i).apply(e);
            }
        }
    }
}
