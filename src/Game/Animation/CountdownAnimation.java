package Game.Animation;
import Game.GameEnvironment.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.Sleeper;

/**
 * @author Itamar Berger 322554403
 * @version 1.0
 * @since 2023-04-24
 */

public class CountdownAnimation implements Animation {
    private int countFrom;
    private SpriteCollection gameScreen;

    /**
     * Create new countdown animation with given countFrom and screen's sprites.
     * @param countFrom (int)
     * @param gameScreen (SpriteCollection) - required sprites
     * upon screen during countdown
     */
    public CountdownAnimation(
            int countFrom,
            SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;

    }

    /**
     * Do one frame of countdown animation upon given surface.
     * @param d (Drawsurface).
     */
    public void doOneFrame(DrawSurface d) {
        Sleeper s = new Sleeper();
        if (this.gameScreen != null) {
            this.gameScreen.drawAllOn(d);
        }
        s.sleepFor(450);
        if (!shouldStop()) {
            d.drawText(400, d.getHeight() - 550,
                    String.valueOf(countFrom),
                    40);
            countFrom--;
        }
        if (countFrom == 0) {
            s.sleepFor(300);
        } else {
            s.sleepFor(600);
        }
    }

    /**
     * Return if countdown animation should stop.
     * @return true/false (boolean).
     */
    @Override
    public boolean shouldStop() {
        if (this.countFrom >= 1) {
            return false;
        }
        return true;
    }
}

