import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import Handlers.KeyInputHandler;
import Handlers.MouseHandler;

public class Main extends Canvas implements Runnable  {
    static int width = 1024, height = 768;

    private KeyInputHandler keyInputHandler = new KeyInputHandler();
    private MouseHandler mouseHandler = new MouseHandler();
    private Panel panel;

    public Main() {
        new Window(width, height, "Attack of Equations", this);
        this.panel = new Panel(width, height, mouseHandler, keyInputHandler);
        
        this.addKeyListener(keyInputHandler);
        this.addMouseListener(mouseHandler);

        Thread thread = new Thread(this);
        thread.start();
    }

    public void run() {
        // write game loop limit to 60 fps
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks; // nanoseconds per tick
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns; // time passed divided by time per tick
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames); // print fps
                frames = 0;
            }
        }
    }

    public void tick() {
        // update game
        panel.tick();
    }

    public void render() {
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        if (bufferStrategy == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics graphics = bufferStrategy.getDrawGraphics();
        // render game
        panel.render(graphics);

        graphics.dispose();
        bufferStrategy.show();
        // fixes lags on linux systems
        Toolkit.getDefaultToolkit().sync();
    }

    public static void main(String[] args) throws Exception {
        new Main();
    }
}
