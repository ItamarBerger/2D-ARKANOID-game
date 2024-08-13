package Game.Levels;
import Game.GameEnvironment.Ball;
import Game.GameEnvironment.Block;
import Game.GameEnvironment.Sprite;
import Game.GameEnvironment.Velocity;
import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Third level of arknoid game (Green3).
 * @author Itamar Berger 322554403
 * @version 1.0
 * @since 2023-04-24
 */
public class GameLevel3 implements LevelInformation {
    static final int NUM_OF_BALLS = 2, PADDLE_SPEED = 8;
    static final int ZERO = 0, NUMBER_OF_BLOCKS_IN_FIRST_ROW = 10,
            NUMBER_OF_BLOCKS_IN_SECOND_ROW = 9,
            NUMBER_OF_BLOCKS_IN_THIRD_ROW = 8,
            NUMBER_OF_BLOCKS_IN_FOURTH_ROW = 7,
            NUMBER_OF_BLOCKS_IN_FIFTH_ROW = 6,
            FIRST_ROW = 1, SECOND_ROW = 2, THIRD_ROW = 2, FOURTH_ROW = 3,
            FIFTH_ROW = 4, SIXTH_ROW = 5;

    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        for (int i = ZERO; i < NUM_OF_BALLS; i++) {
            Random random = new Random();
            velocityList.add(new Velocity(5,
                    -5));
        }
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return 70;
    }

    @Override
    public String levelName() {
        return "Green3";
    }

    @Override
    public Sprite getBackground() {
        // Creating game's background.
        Geometry.Point p20 = new Geometry.Point(0, 600);
        Geometry.Rectangle backGround = new Rectangle(p20, 800,
                600);
        Block backGroundBlock = new Block(backGround);
        backGroundBlock.setColor(new Color(0, 200, 200));
        return backGroundBlock;
    }

    @Override
    public List<Sprite> getComplicatedBackground() {
        List<Sprite> spriteList = new ArrayList<>();
        spriteList.add(this.getBackground());
        Ball sun = new Ball(50, 70, 60, Color.YELLOW);
        sun.setVelocity(0, 0);
        Ball frameOfSun = new Ball(50,
                70, 74, Color.ORANGE);
        Ball innerFrameOfSun = new Ball(50,
                70, 66, new Color(255, 255, 51));
        innerFrameOfSun.setVelocity(0, 0);
        frameOfSun.setVelocity(0, 0);
        spriteList.add(frameOfSun);
        spriteList.add(innerFrameOfSun);
        spriteList.add(sun);

        Point p = new Point(1, 625);
        for (int i = 0; i < 800; i += 30) {
            Ball b = new Ball(new Point(i, p.getY()), 50, Color.BLUE);
            b.setVelocity(0, 0);
            spriteList.add(b);
        }
        for (int i = 0; i < 800 - sun.getX(); i += 15) {
            Line l = new Line(sun.getCenter(), new Point(sun.getX() + i,
                    sun.getY() + 505));
            l.setColor(new Color(255, 255, 153));
            spriteList.add(l);
        }
        Point p2 = new Point(1, 50);
        for (int i = 0; i < 900; i += 100) {
            Ball b = new Ball(new Point(i, p2.getY()), 50, Color.WHITE);
            Ball b2 = new Ball(new Point(i + 20, p2.getY()),
                    50, Color.WHITE);
            Ball b3 = new Ball(new Point(i + 40, p2.getY()),
                    50, Color.WHITE);
            b2.setVelocity(0, 0);
            b.setVelocity(0, 0);
            b3.setVelocity(0, 0);
            spriteList.add(b);
            spriteList.add(b2);
            spriteList.add(b3);
        }
        return spriteList;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();
        Geometry.Point p = new Geometry.Point(780, 100);
        Geometry.Rectangle r = new Geometry.Rectangle(p, 70, 30);

        // Add red blocks to game board.
        for (int i = ZERO; i < NUMBER_OF_BLOCKS_IN_FIRST_ROW; i++) {
            Block newSprite = new Block(
                    new Geometry.Rectangle(new Geometry.Point(p.getX()
                            - i * r.getWidth(), p.getY()), r.getWidth(),
                            r.getHeight()));
            newSprite.setColor(Color.RED);
            blockList.add(newSprite);

        }
        // Add pink blocks to game board.
        for (int i = ZERO; i < NUMBER_OF_BLOCKS_IN_SECOND_ROW; i++) {
            Block newSprite = new Block(
                    new Geometry.Rectangle(new Geometry.Point(p.getX()
                            - i * r.getWidth(), p.getY()
                            + r.getHeight()), r.getWidth(), r.getHeight()));
            newSprite.setColor(Color.PINK);
            blockList.add(newSprite);
        }

        // Add yellow blocks to game board.
        for (int i = ZERO; i < NUMBER_OF_BLOCKS_IN_THIRD_ROW; i++) {
            Block newSprite = new Block(
                    new Geometry.Rectangle(new Geometry.Point(p.getX()
                            - i * r.getWidth(), p.getY()
                            + THIRD_ROW * r.getHeight()), r.getWidth(),
                            r.getHeight()));
            newSprite.setColor(Color.YELLOW);
            blockList.add(newSprite);
        }

        // Add cyan blocks to game board.
        for (int i = ZERO; i < NUMBER_OF_BLOCKS_IN_FOURTH_ROW; i++) {
            Block newSprite =
                    new Block(new Geometry.Rectangle(new
                            Geometry.Point(p.getX()
                            - i * r.getWidth(), p.getY()
                            + FOURTH_ROW
                            * r.getHeight()), r.getWidth(), r.getHeight()));
            newSprite.setColor(Color.cyan);
            blockList.add(newSprite);
        }
        // Add purpel blocks to game board.
        for (int i = ZERO; i < NUMBER_OF_BLOCKS_IN_FIFTH_ROW; i++) {
            Block newSprite =
                    new Block(
                            new Geometry.Rectangle(
                                    new Geometry.Point(p.getX()
                                            - i * r.getWidth(), p.getY()
                                            + FIFTH_ROW
                                            * r.getHeight()), r.getWidth(),
                                    r.getHeight()));
            newSprite.setColor(Color.MAGENTA);
            blockList.add(newSprite);
        }

        // Add white blocks to game board.
        for (int i = ZERO; i < SIXTH_ROW; i++) {
            Block newSprite =
                    new Block(new Geometry.Rectangle(
                            new Geometry.Point(p.getX()
                                    - i * r.getWidth(), p.getY()
                                    + SIXTH_ROW
                                    * r.getHeight()), r.getWidth(),
                            r.getHeight()));
            newSprite.setColor(Color.WHITE);
            blockList.add(newSprite);
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }

    @Override
    public List<Ball> createBallList() {
        List<Ball> balls = new ArrayList<>();
        for (int i = ZERO; i < NUM_OF_BALLS; i++) {
            Random random = new Random();
            Geometry.Point startPointOfBall =
                    new Geometry.Point(random.nextInt(100, 700),
                            random.nextInt(300, 500));
          Ball ball = new Ball(startPointOfBall,
                   4, Color.BLACK);
            ball.setVelocity(this.initialBallVelocities().get(i));
            balls.add(ball);
        }
        return balls;
    }
}
