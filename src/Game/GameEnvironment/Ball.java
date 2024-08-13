package Game.GameEnvironment;

import java.awt.Color;
import Game.Levels.GameLevel;
import Geometry.Line;
import Geometry.Point;
import biuoop.DrawSurface;

/**
 * Ball is a class consists of radius, color,
 * center and velocity.
 *
 * @author Itamar Berger 322554403
 * @version 1.0
 * @since 2023-04-24
 */

public class Ball implements Sprite {
    public static final int ZERO = 0;
    public static final double MIN_Y = 0, EPSILON = 1;
    public static final double MIN_X = 0, COLLISION_EPSILON = 0.001;
    public static final double MAX_X = 200;
    public static final double MAX_Y = 200;
    public static final int SLEEP_TIME = 50;

    private Point center;
    private int radius;
    private Color color;
    private int size;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;
    private Paddle paddle;

    /**
     * Ball is a constructor function.
     * Ball
     * creates a Ball
     * object with center,
     * radius and color.
     *
     * @param center represents center point of a circle (Geometry.Point).
     * @param r      represents radius of circle (int).
     * @param color  represents color of ball (java.awt.Color).
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }


    /**
     * Ball is a constructor function.
     * Ball creates a Ball object with center,
     * radius and color.
     *
     * @param x represents x coordinate of center point of a circle (double).
     * @param y represents y coordinate of center point of a circle (double).
     * @param r represents radius of circle (int).
     * @param color represents color of ball (java.awt.Color).
     */
    public Ball(double x, double y, int r, Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
    }

    /**
     * getCenter is a public method which returns center of ball.
     *
     * @return this.center (Geometry.Point).
     */

    public Point getCenter() {
        return this.center;
    }


    /**
     * getRadius is a public method which returns int radius.
     *
     * @return radius (int).
     */

    public int getRadius() {
        return this.radius;
    }

    /**
     * getRadius is a public method which returns int radius.
     *
     * @param rad radius (int).
     */

    public void setRadius(int rad) {
        this.radius = rad;
    }


    /**
     * getX is a public method which returns x value of the ball center.
     *
     * @return x (int). location of center upon axis x.
     */

    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * getY is a public method which returns y value of the ball center.
     *
     * @return y (int). location of center upon axis y.
     */


    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * getSize is a public method which returns size of ball.
     *
     * @return size (int). using 2PI*r formula.
     */

    public int getSize() {
        return (int) (this.radius);
    }

    /**
     * setSize is a public method which update size of ball.
     *
     * @param size (int). represents updated size of ball.
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * getColor is a public method which returns color of current ball.
     *
     * @return color (java.awt.Color).
     */
    public Color getColor() {
        return this.color;
    }


    /**
     * setPaddle is a public method which updates paddle of current ball.
     *
     * @param p (Game.GameEnvironment.Paddle).
     */
    public void setPaddle(Paddle p) {
        this.paddle = p;
    }

    /**
     * drawOn is a public method which draws current ball on given DrawSurface.
     * method would set color, then draw circle upon given surface.
     *
     * @param surface (DrawSurface).
     */

    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.radius);
    }


    /**
     * setVelocity is a public method which updates velocity of ball.
     *
     * @param v (Game.GameEnvironment.Velocity).
     * represents updated velocity of ball.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * setVelocity is a public method which updates velocity of ball.
     *
     * @param x (double). represents x coordinate of center.
     * @param y (double). represents x coordinate of center.
     */
    public void setCenter(double x, double y) {
        this.center.setX(x);
        this.center.setY(y);
    }


    /**
     * setVelocity is a public method which updates velocity of ball.
     * method would receive dx and dy and update velocity as mentioned.
     *
     * @param dx (double). represents dx component of velocity of ball.
     * @param dy (double). represents dy component of velocity of ball.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }


    /**
     * getVelocity is a public method which returns velocity of ball.
     *
     * @return velocity (Game.GameEnvironment.Velocity).
     * represents velocity of ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * getGameEnvironment is a get method which returns environment of ball.
     *
     * @return gameEnvironment (Game.GameEnvironment.GameEnvironment).
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * setGameEnvironment is a set method which updates ball's environment.
     *
     * @param g1 (Game.GameEnvironment.GameEnvironment).
     */
    public void setGameEnvironment(GameEnvironment g1) {
        this.gameEnvironment = g1;
    }

    /**
     * timePassed is a method designated to perform ball movement.
     * Function would check for collisions of ball,
     * and will update ball's location accordingly.
     */
    @Override
    public void timePassed() {
        Velocity newVel = null;
        double newDx = this.getVelocity().getDx();
        double newDy = this.getVelocity().getDy();
        double newX = (this.center.getX() + this.getVelocity().getDx());
        double newY = (this.center.getY() + this.getVelocity().getDy());
        Point endPoint1 = new Point(newX, newY);
        Line trajectory = null;
        trajectory = new Line(this.center, endPoint1);
        // Extend trajectory ahead of ball.
        trajectory.createTrajecy((int) GameLevel.MAX_RADIUS);

        /*
         * "Creating" line again in order to synchronize length of line
         * as distance between updated start and end points
         */

        trajectory = new Line(trajectory.start(), trajectory.end());

        // Detect closest collision of ball in current environment.
        CollisionInfo c1 = null;
        if (this.gameEnvironment != null) {
            c1 = this.gameEnvironment.getClosestCollision(trajectory);
        }
        Point closestColisionPoint = null;


        if (c1 == null || c1.collisionPoint() == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
            return;
        }

        closestColisionPoint = c1.collisionPoint();
        // Check if collision is closer then next ball's location.
        if (endPoint1.distance(c1.collisionPoint()) <= this.radius + EPSILON
                || this.center.distance(endPoint1)
                >= this.center.distance(c1.collisionPoint()) - EPSILON) {
            // Create and set new velocity using hit method.
            newVel = c1.collisionObject().hit(this, c1.collisionPoint(),
                    this.velocity);
            this.setVelocity(newVel);

            // Check how velocity has changed by hit, and update ball location.
            if (newVel.getDy() == -newDy && -newDy != ZERO) {
                if (this.center.getY() - c1.collisionPoint().getY()
                        >= -COLLISION_EPSILON) {
                    this.center.setX(closestColisionPoint.getX());
                    this.center.setY(closestColisionPoint.getY() + EPSILON);
                } else {
                    this.center.setX(closestColisionPoint.getX());
                    this.center.setY(closestColisionPoint.getY() - EPSILON);
                }
            }

            if (newVel.getDx() == -newDx && -newDx != ZERO) {
                if (this.center.getX() - c1.collisionPoint().getX()
                        >= -COLLISION_EPSILON) {
                    this.center.setX(closestColisionPoint.getX() + EPSILON);
                    this.center.setY(closestColisionPoint.getY());
                } else {
                    this.center.setX(closestColisionPoint.getX() - EPSILON);
                    this.center.setY(closestColisionPoint.getY());
                }
            }
            // If ball is stuck inside paddle - move ball upside.
            if (this.center.getY()
                    < this.paddle.getCollisionRectangle()
                    .getUpLine().getStart().getY() + COLLISION_EPSILON
                    && this.center.getY()
                    >= this.paddle.getCollisionRectangle().getDownLine()
                    .getStart().getY()
                    && this.center.getX()
                    <= this.paddle.getCollisionRectangle().getRightLine().
                    getStart().getX() + COLLISION_EPSILON
                    && this.center.getX() >= this.paddle.getCollisionRectangle()
                    .getLeftLine().getStart().getX()) {
                this.center.setY(this.center.getY() - this.radius);
            }
        } else {
            // If no collision isn't close to ball - move ball by velocity.
            this.center = this.getVelocity().applyToPoint(this.center);
        }
    }
}
