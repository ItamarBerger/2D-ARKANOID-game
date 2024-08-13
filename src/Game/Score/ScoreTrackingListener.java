package Game.Score;
import Game.GameEnvironment.Ball;
import Game.GameEnvironment.Block;
import Game.Counters.Counter;
import Game.GameEnvironment.HitListener;

/**
 *  ScoreTrackingListener is a HitListener,
 *  updating its score counter when hitEvent occurs.
 *
 * @author Itamar Berger 322554403
 * @version 1.0
 * @since 2023-05-24
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * A constructor of updating its scoreCounter.
     * @param scoreCounter (Counter).
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * hitEvent increases currentScore of current ScoreTrackingListener by 5.
     * @param beingHit (Block)
     * @param hitter (Ball)
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (this.currentScore == null) {
            this.currentScore = new Counter(5);
        } else {
            this.currentScore.increase(5);
        }
    }

    /**
     * Returns ScoreTrackingListener's currentScore.
     * @return currentScore (Counter).
     */
    public Counter getCurrentScore() {
        return this.currentScore;
        }
    }

