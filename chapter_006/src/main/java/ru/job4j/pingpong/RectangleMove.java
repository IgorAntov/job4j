package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class RectangleMove implements Runnable {
    private final Rectangle rect;
    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        byte direction = 1;
        while (true) {
            this.rect.setX(this.rect.getX() + direction);
            if (rect.getX() == 290) {
                direction = -1;
            }
            if (rect.getX() == 0) {
                direction = 1;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}