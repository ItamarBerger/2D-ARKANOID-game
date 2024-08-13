package Game.Removers;

import Game.GameEnvironment.Ball;
import Game.GameEnvironment.Block;
import Game.Counters.Counter;
import Game.Levels.GameLevel;
import Game.GameEnvironment.HitListener;

/**
 * BlockRemover is a Class responsible for removing blocks once being hit.
 *
 * @author Itamar Berger 322554403
 * @version 1.0
 * @since 2023-05-24
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Create a BlockRemover with given gameLevel and removedBlocks counter.
     * @param gameLevel (GameLevel).
     * @param removedBlocks (Counter).
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.isBorder()) {
            return;
        }
        beingHit.removeFromGame(this.gameLevel);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
    }

    /**
     * Return remaining blocks.
     *
     * @return RemainingBlocks (Counter).
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }

    /**
     * Update number of remaining blocks.
     * @param number (int).
     */
    public void setRemainingBlocks(int number) {
        this.remainingBlocks.setValue(number);
    }

    /**
     * Update number of remaining blocks.
     * @param number (Counter).
     */
    public void setRemainingBlocks(Counter number) {
        this.remainingBlocks = number;
    }


}

