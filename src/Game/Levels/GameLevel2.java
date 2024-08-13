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
import java.util.Random;
/**
 * Level of arknoid game (Wide easy).
 * @author Itamar Berger 322554403
 * @version 1.0
 * @since 2023-04-24
 */
public class GameLevel2 implements LevelInformation {
    static final Color[]
            LIST_OF_ROW1_COLORS = {new Color(255, 204, 204),
                    new Color(255, 153, 153),
                    new Color(255, 122, 122),
                    new Color(255, 100, 100),
                    new Color(255, 50, 50),
                    new Color(204, 10, 10),
                    new Color(153, 0, 0),
            new Color(132, 30, 30),
                    new Color(130, 30, 60),
                    new Color(170, 0, 80),
                    new Color(200, 0, 110),
                    new Color(230, 30, 130),
                    new Color(255, 102, 178),
                    new Color(255, 153, 204),
                    new Color(255, 204, 229)},
            LIST_OF_ROW2_COLORS = {new Color(255, 250, 250),
            new Color(175, 238, 238),
            new Color(64, 224, 208),
            new Color(72, 209, 204),
                    new Color(0, 206, 209),
                    new Color(32, 178, 170),
                    new Color(0, 158, 150),
                    new Color(0, 139, 139),
                    new Color(0, 128, 128),
                    new Color(0, 139, 139),
                    new Color(32, 158, 160),
                    new Color(32, 178, 170),
                    new Color(0, 206, 209),
                    new Color(72, 209, 204),
                    new Color(64, 224, 208),
                    new Color(175, 238, 238),
                    new Color(255, 250, 250)
            };
    static final int NUM_OF_BALLS = 10;


    static final int NUM_OF_BLOCKS = 30;
    private Velocity velocityOfBall = new Velocity(5, 5);
    private int paddleSpeed = 5;

    @Override
    public int numberOfBalls() {
        return this.createBallList().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<Velocity>();
        for (int i = 0; i < numberOfBalls(); i++) {
            velocityList.add(this.velocityOfBall);
        }
        return velocityList;
    }

    /**
     * Create  and return list of balls arranged in arrow shape.
     * @return balls (List of Ball).
     */
    public List<Ball> createBallList() {
        Point startLocationOfBalls = new Point(400, 500);
        List<Ball> balls = new ArrayList<>();
        // Create new balls in curve structure.
        for (int i = 0; i < NUM_OF_BALLS; i++) {
            Ball b = new Ball(startLocationOfBalls,
                    5, Color.BLACK);
            b.setVelocity(Velocity.fromAngleAndSpeed(18 * i - 80,
                    6));
            balls.add(b);
        }
        return balls;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return 600;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        // Creating game's background.
        Geometry.Point p20 = new Geometry.Point(0, 600);
        Geometry.Rectangle backGround =
                new Rectangle(p20, 800, 600);
        Block backGroundBlock = new Block(backGround);
        backGroundBlock.setColor(Color.BLUE);
        return backGroundBlock;
    }

    @Override
    public List<Sprite> getComplicatedBackground() {
        List<Sprite> spriteList = new ArrayList<>();
        int i = 600;
        Random random = new Random();
        // Create balls in descending size in middle of screen.
        while (i != 0) {
            Ball head = new Ball(new Point(400,
                    300), i, new Color(random.nextInt(150, 230),
                    random.nextInt(150, 230),
                    random.nextInt(150, 230)));
            head.setVelocity(0, 0);
            spriteList.add(head);
            i = i - 10;
        }
        return spriteList;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();
        Geometry.Point p = new Geometry.Point(735, 250);
        Geometry.Rectangle r = new Geometry.Rectangle(p, 51, 25);
        // Add red blocks to game board.
        for (int i = 0; i < NUM_OF_BLOCKS / 2.0; i++) {
            Block bRow1 = new Block(
                    new Geometry.Rectangle(new Geometry.Point(p.getX()
                            - i * r.getWidth(), p.getY()), r.getWidth(),
                            r.getHeight()));
            Block bRow2 =  new Block(
                    new Geometry.Rectangle(new Geometry.Point(p.getX()
                            - i * r.getWidth(), p.getY() + r.getHeight()),
                            r.getWidth(),
                            r.getHeight()));
            if (i == 0) {
                bRow1 = new Block(
                        new Geometry.Rectangle(new Geometry.Point(p.getX(),
                                p.getY()), r.getWidth() - 6,
                                r.getHeight()));
            }
            bRow2.setColor(LIST_OF_ROW1_COLORS[i]);
            bRow1.setColor(LIST_OF_ROW2_COLORS[i]);
            blockList.add(bRow1);
            blockList.add(bRow2);
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUM_OF_BLOCKS;
    }
}
