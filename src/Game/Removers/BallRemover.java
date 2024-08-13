package Game.Removers;
import Game.GameEnvironment.Ball;
import Game.GameEnvironment.Block;
import Game.Counters.Counter;
import Game.Levels.GameLevel;
import Game.GameEnvironment.HitListener;

/**
 *  BallRemover is a Class responsible for removing balls once hit deathZone.
 *
 * @author Itamar Berger 322554403
 * @version 1.0
 * @since 2023-05-24
 */
public class BallRemover implements HitListener {
  private GameLevel gameLevel;
  private Counter remainingBalls;


    /**
     * A constructor, assigning game to BallRemover's game member.
     * @param g (Game) assigned game.
     */
    public BallRemover(GameLevel g) {
        this.gameLevel = g;
        this.remainingBalls = new Counter(0);
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.gameLevel.removeSprite(hitter);
        this.remainingBalls.decrease(1);
    }

    /**
     * Returns a counter of remainingBalls in BallRemover's game.
     *
     * @return remainingBalls (Counter).
     */
    public Counter getRemainingBalls() {
        return this.remainingBalls;
    }

    /**
     * Updates number of remaining balls in remover's game.
     * @param numOfBalls (int) updated number of remaining balls.
     */
    public void setRemainingBalls(int numOfBalls) {
        if (this.remainingBalls == null) {
            this.remainingBalls = new Counter(numOfBalls);
        } else {
          this.remainingBalls.setValue(numOfBalls);
      }
    }
    /**
     * Updates number of remaining balls in remover's game.
     * @param numOfBalls (Counter) updated number of remaining balls.
     */
    public void setRemainingBalls(Counter numOfBalls) {
       this.remainingBalls = numOfBalls;
    }
}
