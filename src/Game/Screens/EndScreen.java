package Game.Screens;

import Game.Animation.Animation;
import Game.GameEnvironment.Block;
import Game.Counters.Counter;
import Game.GameEnvironment.SpriteCollection;
import Geometry.Point;
import Geometry.Rectangle;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import java.awt.Color;


/**
 * @author Itamar Berger 322554403
 * @version 1.0
 * @since 2023-04-24
 */
public class EndScreen implements Animation {
    private boolean isWin;
    private Counter overallScore;
    private KeyboardSensor keyboard;
    private boolean stop;
    private SpriteCollection spriteCollection;

    /**
     * Create EndScreen with given details of game's status.
     * @param isWin (boolean).
     * @param overallScore (Counter).
     * @param keyboard (KeyboardSensor)
     */
    public EndScreen(boolean isWin, Counter overallScore,
                     KeyboardSensor keyboard) {
        this.isWin = isWin;
        this.overallScore = overallScore;
        this.stop = false;
        this.keyboard = keyboard;
        spriteCollection = new SpriteCollection();
        setSpecialEndsScreen();
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        Sleeper s = new Sleeper();
        if (this.spriteCollection != null) {
            this.spriteCollection.drawAllOn(d);
        }

        if (this.isWin) {
            d.setColor(Color.BLACK);
            d.drawText(100, d.getHeight() / 2,
                    "You Win! Your score is : "
                            + overallScore.getValue(), 50);
        } else {
            d.setColor(Color.RED);
            d.drawText(85, d.getHeight() / 2,
                    "Game Over. Your score is: "
                            + overallScore.getValue(), 50);
        }
    }

    /**
     * Create rows of white and red squares.
     * then update current's sprite collection.
     */
    public void setSpecialEndsScreen() {
        boolean isColor1 = false;
        Color color1;
        Color color2;
        if (isWin) {
            color1 = new Color(255, 102, 102);
            color2 = new Color(255, 255, 255);
        } else {
            color1 = Color.black;
            color2 = Color.WHITE;
        }
        for (int i = -8; i < 800; i += 50) {
            for (int j = -6; j < 600; j += 50) {
                Block b = new
                        Block(new Rectangle(new Point(i + 8,
                        j + 6), 50,
                        50));
                if (isColor1) {
                  b.setColor(color1);
                } else {
                    b.setColor(color2);
                }
                this.spriteCollection.addSprite(b);
                isColor1 = !isColor1;
            }

        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
