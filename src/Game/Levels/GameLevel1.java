package Game.Levels;

import Game.GameEnvironment.Ball;
import Game.GameEnvironment.Block;
import Game.GameEnvironment.Sprite;
import Game.GameEnvironment.Velocity;
import Geometry.Point;
import Geometry.Rectangle;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * Level of arknoid game (Direct Hit).
 * @author Itamar Berger 322554403
 * @version 1.0
 * @since 2023-04-24
 */
public class GameLevel1 implements LevelInformation {
private Velocity velocityOfBall = new Velocity(5, 5);
private int paddleSpeed = 8;
    @Override
    public int numberOfBalls() {
        return initialBallVelocities().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        velocityList.add(this.velocityOfBall);
        return velocityList;
    }
    @Override
    public List<Ball> createBallList() {
        Ball b = new Ball(new Point(400, 200), 4, Color.BLACK);
        List<Ball> balls = new ArrayList<>();
        b.setVelocity(this.velocityOfBall);
        balls.add(b);
        return balls;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return 80;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        // Creating game's background.
        Point p20 = new Point(0, 600);
        Rectangle backGround = new Rectangle(p20, 800, 600);
        Block backGroundBlock = new Block(backGround);
        backGroundBlock.setColor(Color.PINK);
       return backGroundBlock;
    }
    @Override
    public List<Sprite> getComplicatedBackground() {
        List<Sprite> blockList = new ArrayList<>();
        blockList.add(this.getBackground());
        Geometry.Point p = new Geometry.Point(0, 600);
        Geometry.Rectangle r = new Geometry.Rectangle(p, 800, 25);
        Geometry.Point p2 = new Geometry.Point(400, 600);

        Ball sunsetBall = new Ball(p2, 100, Color.ORANGE);
        sunsetBall.setVelocity(0, 0.1);
        blockList.add(sunsetBall);
            // Add red blocks to game board.
            Block newSprite = new Block(r);
            newSprite.setColor(Color.CYAN);
            blockList.add(newSprite);
            return blockList;
        }


    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();
        Point p = new Point(400, 100);
        Rectangle r = new Rectangle(p, 40, 20);
        Block b = new Block(r);
        b.setColor(Color.RED);
        blockList.add(b);
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
