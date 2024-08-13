package Game.Screens;

import Game.Animation.Animation;
import Game.GameEnvironment.BlockWithoutBoundaries;
import Game.GameEnvironment.SpriteCollection;
import Geometry.Point;
import Geometry.Rectangle;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * @author Itamar Berger 322554403
 * @version 1.0
 * @since 2023-04-24
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private boolean running;
    private SpriteCollection spriteCollection;

    /**
     * Create new PauseScreen with given sensor and isRunning flag.
     * @param k (KeyboardSensor)
     * @param running (boolean)
     */
    public PauseScreen(KeyboardSensor k, boolean running) {
        this.keyboard = k;
        this.stop = false;
        this.running = running;
        this.spriteCollection = new SpriteCollection();
        createSpecialPauseScreen();
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.spriteCollection != null) {
            this.spriteCollection.drawAllOn(d);
        }
        d.setColor(Color.BLACK);
        d.drawText(250, d.getHeight() / 2,
                "Game Paused", 50);
        d.setColor(Color.gray);
        d.drawText(250, d.getHeight() / 2 + 100,
                "press space to continue", 32);


    }

    /**
     * Crete purple pauseScreen using blocks.
     */
    public void createSpecialPauseScreen() {
        SpriteCollection spriteCollection1 = new SpriteCollection();
        Point point = new Point(0, 0);
        for (int i = 0; i < 800; i += 10) {
            BlockWithoutBoundaries b =
                    new BlockWithoutBoundaries(new Rectangle(
                            new Point(point.getX(),
                                    point.getY() + i), 800, 10));
            // Create blocks in fading order.
            b.setColor(new Color(255, 255 - i / 5, 255));
            spriteCollection1.addSprite(b);
        }

        this.spriteCollection = spriteCollection1;
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}

