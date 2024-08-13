package Game.Levels;

import Game.Animation.Animation;
import Game.Animation.AnimationRunner;
import Game.Animation.CountdownAnimation;
import Game.Counters.Counter;
import Game.GameEnvironment.Ball;
import Game.GameEnvironment.Block;
import Game.GameEnvironment.Collidable;
import Game.GameEnvironment.GameEnvironment;
import Game.GameEnvironment.Paddle;
import Game.GameEnvironment.Sprite;
import Game.GameEnvironment.SpriteCollection;
import Game.Screens.KeyPressStoppableAnimation;
import Game.Screens.PauseScreen;
import Geometry.Point;
import Geometry.Rectangle;
import Game.Removers.BallRemover;
import Game.Removers.BlockRemover;
import Game.Score.ScoreIndicator;
import Game.Score.ScoreTrackingListener;
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * Game.Game includes basic methods for initializing and running arknoid game.
 *
 * @author Itamar Berger 322554403
 * @version 1.0
 * @since 2023-04-24
 */
public class GameLevel implements Animation {
    public static final double MIN_Y = 0, EPSILON = 1, MAX_RADIUS = 100;
    static final int ZERO = 0, WIDTH_OF_BORDERS = 20,
            HEIGHT_OF_VERTICAL_BORDERS = 590,
            HEIGHT_OF_HORIZONTAL_BORDERS = 800;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter counterBlocks;
    private Counter counterBalls;
    private Counter score;
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;
    private boolean running;

    /**
     * Create Game level with given information.
     * @param info (LevelInformation ).
     */
    public GameLevel(LevelInformation info) {
        this.levelInformation = info;
    }

    /**
     * Create default Game level.
     */
    public GameLevel() {
        this.levelInformation = null;
    }

    /**
     * Create Game level with given information, key sensor and a runner.
     * @param levelInfo (LevelInformation ).
     * @param animationRunner  (AnimationRunner).
     * @param score (Counter).
     * @param keyboardSensor  (KeyboardSensor)
     */
    public GameLevel(LevelInformation levelInfo,
                     KeyboardSensor keyboardSensor,
                     AnimationRunner animationRunner, Counter score) {
        this.levelInformation = levelInfo;
        this.keyboard = keyboardSensor;
        this.runner = animationRunner;
        this.score = score;
    }

    /**
     * addCollidable adds object as sprite and collidable to game environment.
     *
     * @param c
     */
    public void addCollidable(Collidable c) {
        this.sprites.addSprite((Sprite) c);
        this.environment.addCollidable(c);
    }

    /**
     * getEnvironment returns current game's environment.
     *
     * @return environment (Game.GameEnvironment.GameEnvironment).
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * getSprite returns current game's sprites collection.
     *
     * @return sprites (Game.GameEnvironment.SpriteCollection).
     */
    public SpriteCollection getSprites() {
        return this.sprites;
    }

    /**
     * setEnvironment updates current game's environment.
     *
     * @param environment (Game.GameEnvironment.GameEnvironment)
     */

    public void setEnvironment(GameEnvironment environment) {
        this.environment = environment;
    }

    /**
     * setGui updates current game's GUI.
     *
     * @param g (GUI)
     */

    public void setGui(GUI g) {
        this.runner = new AnimationRunner(g, this.running);
    }

    /**
     * setSprites updates current game's sprite collection.
     *
     * @param s (Game.GameEnvironment.SpriteCollection)
     */

    public void setSprites(SpriteCollection s) {
        this.sprites = s;
    }


    /**
     * addSprites adds sprite to current game's sprite collection.
     *
     * @param s (Game.GameEnvironment.Sprite)
     */

    public void addSprite(Sprite s) {
        if (this.sprites == null) {
            List<Sprite> spriteList = new ArrayList<>();
            spriteList.add(s);
            this.sprites.setSpriteList(spriteList);
        } else {
            List<Sprite> spriteList = this.sprites.getSpriteList();
            spriteList.add(s);
            this.sprites.setSpriteList(spriteList);
        }

    }

    /**
     * Create and add to game level borders blocks of screen.
     */
    public void addBordersToGameLevel() {
        Geometry.Point p10 = new Geometry.Point(780, 600);
        Geometry.Point p12 = new Geometry.Point(0, 600);
        Geometry.Point p13 = new Geometry.Point(0, 50);

        // Add borders to game's board.
        Geometry.Rectangle rightBorder = new Geometry.Rectangle(p10,
                WIDTH_OF_BORDERS, HEIGHT_OF_VERTICAL_BORDERS);
        Geometry.Rectangle leftBorder = new Geometry.Rectangle(p12,
                WIDTH_OF_BORDERS, HEIGHT_OF_VERTICAL_BORDERS);
        Geometry.Rectangle upBorder = new Geometry.Rectangle(p13,
                HEIGHT_OF_HORIZONTAL_BORDERS, WIDTH_OF_BORDERS);

        Block rightBlock = new Block(rightBorder, true);
        Block leftBlock = new Block(leftBorder, true);
        Block upsideBlock = new Block(upBorder, true);
        rightBlock.setColor(Color.GRAY);
        leftBlock.setColor(Color.GRAY);
        upsideBlock.setColor(Color.GRAY);

        // Add all sprites to sprites collection.
        this.sprites.addSprite(rightBlock);
        this.sprites.addSprite(leftBlock);
        this.sprites.addSprite(upsideBlock);

        // Add all collidables to environment.
        this.environment.addCollidable(rightBlock);
        this.environment.addCollidable(leftBlock);
        this.environment.addCollidable(upsideBlock);
    }

    /**
     * Add levels blocks to game level by assigning listeners and removers
     * to each one of them, and add as colidables object to game's environment.
     * @param remover (BlockRemover).
     * @param scoreTracker (ScoreTrackingListener).
     */
    public void addLevelsBlocksToGame(BlockRemover remover,
                                      ScoreTrackingListener
            scoreTracker) {
        for (Block c : this.levelInformation.blocks()) {
            c.addHitListener(remover);
            c.addHitListener(scoreTracker);
            this.sprites.addSprite(c);
            this.environment.addCollidable(c);
        }
    }

    /**
     * getGui returns current game's GUI.
     *
     * @return gui (GUI)
     */


    public GUI getGui() {
        return this.runner.getGui();
    }

    /**
     * initialize  a new game:
     * create Blocks and add them to the game.
     */

    public void initialize() {
        //  initialize game's members
        this.setGui(this.runner.getGui());
        this.setSprites(null);
        this.setEnvironment(new GameEnvironment(new ArrayList<>()));
        this.sprites = new SpriteCollection();
        this.running = true;
        this.counterBlocks =
                new Counter(
                        this.levelInformation.numberOfBlocksToRemove()
                                - 1);


        Geometry.Point p14 = new Geometry.Point(0, 610);
        Geometry.Point p15 = new Geometry.Point(400, 580);

        this.setCounterBlocks(new Counter(ZERO));
        BlockRemover blockRemover = new BlockRemover(this,
                this.getCounterBlocks());
        ScoreTrackingListener scoreTrack =
                new ScoreTrackingListener(this.score);
        BallRemover ballRemover = new BallRemover(this);


        // Subtract blocks which can't be removed.
        int countBlocks = -1;
        blockRemover.setRemainingBlocks(countBlocks);
        this.setCounterBlocks(blockRemover.getRemainingBlocks());

        // DeathZone block.
        Geometry.Rectangle downBorder = new Geometry.Rectangle(p14,
                HEIGHT_OF_HORIZONTAL_BORDERS, EPSILON);
        Block deathZone = new Block(downBorder, true);
        deathZone.setColor(null);
        deathZone.addHitListener(ballRemover);

        this.setRemainingBalls(ballRemover.getRemainingBalls());
        // Setting score indicator
        this.setScore(scoreTrack.getCurrentScore());
        ScoreIndicator scoreIndicator =
                new ScoreIndicator(this.levelInformation.levelName(),
                        scoreTrack.getCurrentScore());
        scoreIndicator.setScore(this.getScore());

        // Initialize paddle by level's information.
        Rectangle paddleShape =
                new Rectangle(new Point(p15.getX()
                        - this.levelInformation.paddleWidth() / 2.0, p15.getY()),
                        this.levelInformation.paddleWidth(), 8);
        Paddle paddle = new Paddle(paddleShape,
                this, this.runner.getGui().getKeyboardSensor(),
                this.levelInformation.paddleSpeed());
        paddle.setColor(Color.YELLOW);

        // Add all level's blocks to current game.
        for (Sprite s : this.levelInformation.getComplicatedBackground()) {
            this.sprites.addSprite(s);
        }
        addLevelsBlocksToGame(blockRemover, scoreTrack);

        // Add Balls to game.

        for (Ball ball : this.levelInformation.createBallList()) {
            ball.setGameEnvironment(this.environment);
            this.sprites.addSprite(ball);
            ball.setPaddle(paddle);
        }
        this.counterBalls = new Counter(this.levelInformation.numberOfBalls());
        this.setCounterBlocks(
                new Counter(this.levelInformation.numberOfBlocksToRemove()));
        blockRemover.setRemainingBlocks(this.counterBlocks);
        ballRemover.setRemainingBalls(this.counterBalls);
        this.setRemainingBalls(this.counterBalls);


        this.sprites.addSprite(paddle);
        this.environment.addCollidable(paddle);
        this.environment.addCollidable(deathZone);
        this.addBordersToGameLevel();
        this.addSprite(scoreIndicator);
        this.keyboard = this.runner.getGui().getKeyboardSensor();
    }


    /**
     * Remove a collidable from Collidables  list of game.
     *
     * @param c (Game.GameEnvironment.Collidable)
     */
    public void removeCollidable(Collidable c) {
        List<Collidable> col = this.environment.getCollidables();
        col.remove(c);
        this.environment.setCollidables(col);
    }

    /**
     * Remove a sprite from Game.GameEnvironment.SpriteCollection of game.
     *
     * @param s (Game.GameEnvironment.Sprite)
     */
    public void removeSprite(Sprite s) {
        List<Sprite> spriteCollection = this.sprites.getSpriteList();
        spriteCollection.remove(s);
        this.sprites.setSpriteList(spriteCollection);
    }

    /**
     * Returns Counter of remaining blocks in game.
     *
     * @return counterBlocks (Counter).
     */
    public Counter getCounterBlocks() {
        return this.counterBlocks;
    }

    /**
     * Updates Counter of remaining blocks in game.
     *
     * @param counter (Counter).
     */
    public void setCounterBlocks(Counter counter) {
        this.counterBlocks = counter;
    }

    /**
     * Updates Counter of remaining ball in game.
     *
     * @param numOfBalls (Counter).
     */
    public void setRemainingBalls(Counter numOfBalls) {
        this.counterBalls = numOfBalls;
    }

    /**
     * Updates current game's score counter.
     *
     * @param score (Cuonter).
     */
    public void setScore(Counter score) {
        this.score = score;
    }

    /**
     * Returns game's score counter.
     *
     * @return score (Cuonter).
     */
    public Counter getScore() {
        return this.score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.running = true;
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.shouldStop()) {
            this.runner.setIsRunning(false);
        }
        // Pause screen if "p" is pressed.
        if (this.keyboard.isPressed("p")) {
            KeyPressStoppableAnimation pauseScreen =
                    new KeyPressStoppableAnimation(
                    this.keyboard, KeyboardSensor.SPACE_KEY,
                    new PauseScreen(this.keyboard,
                            this.running), null);
            this.runner.run(pauseScreen);
        }
    }

    /**
     * Run current Game level using its animation runner.
     */
    public void run() {
        this.initialize(); // or a similar method
        this.runner.run(new CountdownAnimation(3,
                this.sprites));
        this.running = true;
        // use runner to run the current animation.
        this.runner.run(this);
    }

    @Override
    public boolean shouldStop() {
        if (this.counterBlocks.getValue() <= ZERO
                || this.counterBalls.getValue()
                <= ZERO) {
            return true;
        }
        return false;
    }

    /**
     * Return current's counter of remaining balls in game.
     * @return counterBalls (Counter).
     */
    public Counter getCounterBalls() {
        return this.counterBalls;
    }
}
