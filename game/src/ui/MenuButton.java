package ui;

import Handlers.MouseHandler;

public class MenuButton extends Button {
    public MenuButton(MouseHandler mouseHandler) {
        super(mouseHandler);
        this.text = "";
        this.width = 90;
        this.height = 90;
        this.setImg("../assets/menu_button.png");
    }
}
