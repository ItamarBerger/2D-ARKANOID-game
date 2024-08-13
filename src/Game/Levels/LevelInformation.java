package Game.Levels;
import Game.GameEnvironment.Ball;
import Game.GameEnvironment.Block;
import Game.GameEnvironment.Sprite;
import Game.GameEnvironment.Velocity;

import java.util.List;
/**
 * @author Itamar Berger 322554403
 * @version 1.0
 * @since 2023-04-24
 */
public interface LevelInformation {
    /**
     * Return number of balls (int).
     * @return numberOfBalls (int).
     */
    int numberOfBalls();


    /**
     * The initial velocity of each ball.
     *
     * @return (List of Velocity).
     */
    List<Velocity> initialBallVelocities();

    /**
     * Return paddle's speed.
     * @return speed (int).
     */
    int paddleSpeed();
    /**
     * Return paddle's width.
     * @return width (int).
     */
    int paddleWidth();


    /**
     * Return the level name displayed at the top of the screen.
     *
     * @return levelName (String)
     */
    String levelName();


    /**
     * Returns a sprite with the background of the level.
     *
     * @return background (Sprite).
     */
    Sprite getBackground();

    /**
     * Returns list of sprites composing a complicated background.
     * @return sprites (List of sprite).
     */
    List<Sprite> getComplicatedBackground();



    /**
     * Return the Blocks that make up this level.
     * each block contains its size, color and location.
     * @return blocks (List of blocks).
     */
    List<Block> blocks();


    /**
     * Number of blocks that should be removed
     * before the level is considered to be "cleared".
     *
     * @return numberOfBlocksToRemove (int).
     */
    int numberOfBlocksToRemove();

    /**
     * Create and return list of balls.
     * @return balls (List of Ball).
     */
    List<Ball> createBallList();
}

