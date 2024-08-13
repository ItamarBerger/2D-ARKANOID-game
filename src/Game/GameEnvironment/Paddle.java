package Game.GameEnvironment;

import Game.Levels.GameLevel;
import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;


/**
 * Game.GameEnvironment.Paddle represents a block with methods for moving right and left
 * by keyboard sensors.
 * @author Itamar Berger 322554403
 * @version 1.0
 * @since 2023-04-24
 */
public class Paddle implements Sprite, Collidable {
    static final int PADDLE_SPEED = 12;
    static final double EPSILON = 0.001;

    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private Block blockPaddle;
    private int dx;
    private int length;
    private GameLevel gameLevel;
    private Color color;


    /**
     * Game.GameEnvironment.Paddle defines paddle's shape,game and keyboard sensor..
     * @param r (Geometry.Rectangle) represents paddle's shape.
     * @param gameLevel (Game.Game).
     * @param keyboard (biuoop.KeyboardSensor).
     */

    public Paddle(Rectangle r, GameLevel gameLevel,
                  biuoop.KeyboardSensor keyboard) {
        this.rectangle = r;
        this.blockPaddle = new Block(r);
        this.length = (int) r.getWidth();
        this.gameLevel = gameLevel;
        this.keyboard = keyboard;
        this.blockPaddle = new Block(r);
        // Default value of dx movement.
        this.dx = PADDLE_SPEED;
    }

    /**
     * Game.GameEnvironment.Paddle defines paddle's shape,game and keyboard sensor..
     * @param r (Geometry.Rectangle) represents paddle's shape.
     * @param gameLevel (Game.Game).
     * @param keyboard (biuoop.KeyboardSensor).
     * @param speed  (int).
     */

    public Paddle(Rectangle r, GameLevel gameLevel,
                  biuoop.KeyboardSensor keyboard, int speed) {
        this.rectangle = r;
        this.blockPaddle = new Block(r);
        this.length = (int) r.getWidth();
        this.gameLevel = gameLevel;
        this.keyboard = keyboard;
        this.blockPaddle = new Block(r);
        // Default value of dx movement.
        this.dx = speed;
    }

    /**
     * getBlockPaddle returns paddle's block.
     * @return blockPaddle (Ass3Game.Block).
     */
    public Block getBlockPaddle() {
        return this.blockPaddle;
    }

    /**
     * getColor returns paddle's color.
     * @return color (Color).
     */

    public Color getColor() {
        return this.color;
    }

    /**
     * setColor updates paddle's color.
     * @param color (Color). represents new color of paddle.
     */

    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * detectLeftPaddleCollision returns true
     * if paddle collided with left border of paddle's game. otherwise - false.
     * @param upperLeft (Geometry.Point) -
     * current position of paddle's upperLeft.
     * @param newUpperLeft (Geometry.Point) new position of paddle's upperLeft.
     * @return true/false (boolean).
     */
    public boolean detectLeftPaddleCollision(Point upperLeft,
                                             Point newUpperLeft) {
        // Check deviation around left side of paddle.
        CollisionInfo cLeft;
        cLeft = collisionInfoOfLeftPaddle(upperLeft, newUpperLeft);

        if (cLeft != null) {
            /*
             *If left collsiion has been detected -
             * check for distance from paddle's left edge
             */
            if (newUpperLeft.distance(cLeft.collisionPoint())
                    <= EPSILON
                    || upperLeft.distance(newUpperLeft)
                    >= upperLeft.distance(cLeft.collisionPoint())
                    - EPSILON) {
                return true;
            }
        }
        // If no close collision was detected - return false.
        return false;
    }

    /**
     * collisionInfoOfLeftPaddle returns Game.GameEnvironment.CollisionInfo
     * of paddle's closest collision with left side (if not exist return null).
     * @param upperLeft (Geometry.Point)
     * current position of paddle's upperLeft.
     * @param newUpperLeft (Geometry.Point) new position of paddle's upperLeft.
     * @return c (Game.GameEnvironment.CollisionInfo)
     */

    public CollisionInfo collisionInfoOfLeftPaddle(Point upperLeft,
                                                   Point newUpperLeft) {
        Line trajectory = new Line(upperLeft, newUpperLeft);
        trajectory.createTrajecy(this.length * 5);
        trajectory = new Line(trajectory.start(), trajectory.end());
        CollisionInfo c;
        c = this.gameLevel.getEnvironment().getClosestCollision(trajectory);
        if (c != null) {
            if (c.collisionObject() == this) {
                return null;
            }
        }
        return c;
    }

    /**
     * collisionInfoOfRight Game.GameEnvironment.Paddle returns Game.GameEnvironment.CollisionInfo
     * of paddle's closest collision with right side (if not exist return null).
     * @param upperRight (Geometry.Point)
     * current position of paddle's upperRight.
     * @param newUpperRight (Geometry.Point)
     * new position of paddle's newUpperRight.
     * @return c (Game.GameEnvironment.CollisionInfo)
     */

    public CollisionInfo collisionInfoOfRightPaddle(Point upperRight,
                                                    Point newUpperRight) {
        Line trajectory = new Line(upperRight, newUpperRight);

        trajectory.createTrajecy(this.length);
        trajectory = new Line(trajectory.start(), trajectory.end());
        CollisionInfo c;
        c = this.gameLevel.getEnvironment().getClosestCollision(trajectory);
        if (c != null) {
            if (c.collisionObject() == this) {
                return null;
            }
        }

        return c;
    }

    /**
     * detectRightPaddleCollision returns true
     * if paddle collided with right border of paddle's game.
     * otherwise - false.
     * @param upperRight (Geometry.Point)
     * current position of paddle's upperRight.
     * @param  newUpperRight (Geometry.Point)
     * new position of paddle's  newUpperRight.
     * @return true/false (boolean).
     */

    public boolean detectRightPaddleCollision(Point upperRight,
                                              Point newUpperRight) {
        CollisionInfo cRight;
        cRight = collisionInfoOfRightPaddle(upperRight, newUpperRight);
        if (cRight != null) {
            if (upperRight.distance(newUpperRight)
                    >= upperRight.distance(cRight.collisionPoint())
                    - EPSILON) {
                return true;
            }
        }
        return false;
    }


    /**
     *  moveLeft moves paddle to the left (if possible).
     */
    public void moveLeft() {
        // NewUpperLeft is the next position of upperLeft edge of paddle.
        Point newUpperLeft = new Point(this.rectangle.getUpperLeft().getX()
                - this.dx - GameLevel.EPSILON,
                this.rectangle.getUpperLeft().getY());
        Point upperLeft = new Point(this.rectangle.getUpperLeft().getX()
                - GameLevel.EPSILON, this.rectangle.getUpperLeft().getY());
        // Check if paddle collides in next movement to the left.
        boolean isCollide =
                this.detectLeftPaddleCollision(upperLeft,
                        newUpperLeft);
        // If paddle won't collide with blocks - move paddle left.
        if (!isCollide) {
            this.rectangle = new Rectangle(
                    newUpperLeft, this.rectangle.getWidth(),
                    this.rectangle.getHeight());
            this.blockPaddle = new Block(this.rectangle);
        } else {
            /*
             * If paddle would collide next move with blocks -
             * update its location as close as possible
             *  to right side of mentioned block.
             */

            int newX = (int) this.collisionInfoOfLeftPaddle(
                    upperLeft, newUpperLeft).collisionPoint().getX();
            newUpperLeft.setX(newX + GameLevel.EPSILON);
            this.rectangle = new Rectangle(newUpperLeft,
                    this.rectangle.getWidth(), this.rectangle.getHeight());
            this.blockPaddle = new Block(this.rectangle);
        }
    }

    /**
     *  moveLeft moves paddle to the right (if possible).
     */

    public void moveRight() {
        Point upperRight = new Point(this.rectangle.getUpperLeft().getX()
                + this.length + EPSILON,
                this.rectangle.getUpperLeft().getY());
        // NewUppeRight is the next position of upperRight edge of paddle.
        Point newUpperRight = new Point(this.rectangle.getUpperLeft().getX()
                + this.length + this.dx, this.rectangle.getUpperLeft().getY());

        Point newUpperLeft = new Point(this.rectangle.getUpperLeft().getX()
                + this.dx, this.rectangle.getUpperLeft().getY());

        // Check if paddle collides in next movement to the right.
        boolean isCollide =
                this.detectRightPaddleCollision(upperRight,
                        newUpperRight);
        if (!isCollide) {
            this.rectangle = new Rectangle(newUpperLeft,
                    this.rectangle.getWidth(), this.rectangle.getHeight());
            this.blockPaddle = new Block(this.rectangle);
        } else {
            /*
             * If paddle would collide next move with blocks -
             * update its location as close as possible
             *  to left side of mentioned block.
             */
            int newX = (int) this.collisionInfoOfRightPaddle(
                    upperRight, newUpperRight).collisionPoint().getX();
            newUpperLeft.setX(newX - this.length - EPSILON);
            this.rectangle = new Rectangle(newUpperLeft,
                    this.rectangle.getWidth(), this.rectangle.getHeight());
            this.blockPaddle = new Block(this.rectangle);
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY()
                        - (int) this.rectangle.getHeight(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
    }


    /**
     * timePassed moves paddle to the left/right according to keyboard sensor.
     */
    @Override
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * getCollisionRectangle returns rectangle of paddle.
     * @return rectangle (Geometry.Rectangle).
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }



    /**
     * hit returns new Game.GameEnvironment.Velocity of object after hitting paddle.
     * if we hit region 1,
     * the ball should bounce back with an angle of 300 degrees (-60)
     * for region 2 ball should bounce back 330 degrees (a little to the left),
     * for region 4 it should bounce in 30 degrees,
     * and for region 5 in 60 degrees.
     * for region 3 - ball should keep its horizontal direction.
     *
     * @return newVel (Game.GameEnvironment.Velocity).
     */

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        Velocity newVelocity = currentVelocity;

        // Hit region 1
        if (collisionPoint.getX()
                <= this.rectangle.getLeftLine().getStart().getX()
                + this.length / 5.0 + EPSILON) {
            newVelocity = new Velocity(Velocity.fromAngleAndSpeed(300,
                    Math.sqrt(currentVelocity.getDx()
                            * currentVelocity.getDx()
                            + currentVelocity.getDy()
                            * currentVelocity.getDy())));
            return newVelocity;
        }

        // Hit region 2
        if (collisionPoint.getX()
                >= this.rectangle.getLeftLine().getStart().getX()
                + this.length / 5.0 - EPSILON
                && collisionPoint.getX()
                <= this.rectangle.getLeftLine().getStart().getX() + 2
                * this.length / 5.0 + EPSILON) {
            newVelocity = new Velocity(Velocity.fromAngleAndSpeed(330,
                    (Math.sqrt(currentVelocity.getDx()
                            * currentVelocity.getDx()
                            + currentVelocity.getDy()
                            * currentVelocity.getDy()))));
            return newVelocity;
        }

        // Hit region 5.
        if (collisionPoint.getX()
                >= this.rectangle.getRightLine().getStart().getX()
                - this.length / 5.0 - EPSILON) {
            newVelocity = new Velocity(Velocity.fromAngleAndSpeed(60,
                    (Math.sqrt(currentVelocity.getDx()
                            * currentVelocity.getDx()
                            + currentVelocity.getDy()
                            * currentVelocity.getDy()))));
            return newVelocity;
        }


        // Hit region 3
        if (collisionPoint.getX()
                >= this.rectangle.getLeftLine().getStart().getX()
                + 2 * this.length / 5.0 - EPSILON
                && collisionPoint.getX()
                <= this.rectangle.getLeftLine().getStart().getX()
                + 3 * this.length / 5.0 + EPSILON) {
            // Change only vertical direction of ball.
            newVelocity = new Velocity(currentVelocity.getDx(),
                    -currentVelocity.getDy());
            return newVelocity;
        }

        // Hit region 4
        if (collisionPoint.getX()
                >= this.rectangle.getLeftLine().getStart().getX() + 3
                * this.length / 5.0 - EPSILON
                && collisionPoint.getX()
                <= this.rectangle.getLeftLine().getStart().getX() + 4
                * (this.length / 5.0) + EPSILON) {
            newVelocity = new Velocity(Velocity.fromAngleAndSpeed(30,
                    (Math.sqrt(currentVelocity.getDx()
                            * currentVelocity.getDx()
                            + currentVelocity.getDy()
                            * currentVelocity.getDy()))));
            return newVelocity;
        }
        return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
    }

    @Override
    public void addHitListener(HitListener hl) {

    }

    @Override
    public boolean isBorder() {
        return false;
    }

    /**
     * addToGame adds current paddle to a given game.
     * @param g (Game.Game).
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}