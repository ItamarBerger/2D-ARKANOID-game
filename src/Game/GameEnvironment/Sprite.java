package Game.GameEnvironment;

import  biuoop.DrawSurface;
import java.awt.Color;

/**
 * Game.GameEnvironment.Sprite includes methods drawOn,timePassed and getColor.
 * @author Itamar Berger 322554403
 * @version 1.0
 * @since 2023-04-24
 */
public interface Sprite {
    /**
     * method for drawing sprite upon screen.
     * @param  d (DrawSurface).
     */
    void drawOn(DrawSurface d);

    /**
     * @return color (Color) - color of current sprite.
     */
    Color getColor();

    /**
     * Notify object time has passed.
     */
    void timePassed();

}
