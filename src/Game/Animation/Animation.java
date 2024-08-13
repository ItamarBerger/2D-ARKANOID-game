package Game.Animation;

import biuoop.DrawSurface;

/**
 * Interface includes basic methods of animation.
 * @author Itamar Berger 322554403
 * @version 1.0
 * @since 2023-04-24
 */
public interface Animation {
    /**
     * Run single frame of animation.
     * @param d (Drawsurface).
     */
    void doOneFrame(DrawSurface d);

    /**
     * Return true if animation should stop. false otherwise.
     * @return true/false (boolean).
     */
    boolean shouldStop();

}