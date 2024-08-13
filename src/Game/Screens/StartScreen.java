package Game.Screens;
import Game.Animation.Animation;
import Game.GameEnvironment.Block;
import Game.GameEnvironment.SpriteCollection;
import Geometry.Point;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;


/**
 * Start screen of game.
 * @author Itamar Berger 322554403
 * @version 1.0
 * @since 2023-04-24

 */
public class StartScreen implements Animation {
    private boolean stop;
    private SpriteCollection spriteCollection;
    private KeyboardSensor keyboard;

    /**
     * Create StartScreen with given sensor of game.
     * @param keyboard (KeyboardSensor)
     */
    public StartScreen(
                     KeyboardSensor keyboard) {
        this.stop = false;
        this.keyboard = keyboard;
        spriteCollection = new SpriteCollection();
        setSpecialStartScreen();
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.spriteCollection != null) {
            this.spriteCollection.drawAllOn(d);
        }
        d.setColor(Color.WHITE);
            d.drawText(48, d.getHeight() / 2,
                    "Press the Space button to start the game",
                    40);
    }

    /**
     * Create rows of black and purple squares.
     * then update current's sprite collection.
     */
    public void setSpecialStartScreen() {
        boolean isBlack = false;
        Color color = null;
        for (int i = -8; i < 800; i += 50) {
            for (int j = -6; j < 600; j += 50) {
                if (isBlack) {
                    color = (Color.BLACK);
                } else {
                    color = new Color(120, 70, 108);
                }
                Block b = new
                        Block(new Geometry.Rectangle(new Point(i + 8,
                        j + 6), 50,
                        50));
                b.setColor(color);
                this.spriteCollection.addSprite(b);
                isBlack = !isBlack;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}


