package Game.GameEnvironment;

import Game.GameEnvironment.Ball;
import Game.GameEnvironment.Block;

/**
 * Interface including method HitEvent designed to react once an object is hit.
 * @author Itamar Berger 322554403
 * @version 1.0
 * @since 2023-04-24
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Game.GameEnvironment.Ball.Game.GameEnvironment.Ball that's doing the hitting.
     * @param beingHit (Block)
     * @param hitter (Ball)
     */
    void hitEvent(Block beingHit, Ball hitter);
}

