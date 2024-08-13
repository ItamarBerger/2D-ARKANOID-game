package Game.GameEnvironment;

import Geometry.Point;
import java.util.Random;

/**
 * Velocity represents velocity of object. velocity consists of dx and dy.
 * @author Itamar Berger 322554403
 * @version 1.0
 * @since 2023-04-24
 */

public class Velocity {
    private double dx;
    private double dy;

    public static final int ZERO = 0, TWO = 2, ONE = 1, MAX_SIZE = 50;

    public static final int RAND_BOUND = 5;

    public static final int MIN_DX = 1, MIN_DY = 1;

    /**
     * Velocity is a constructor method
     * which initial members "dx" and "dy".
     * @param v .
     */

    public Velocity(Velocity v) {
    this.dx = v.getDx();
    this.dy = v.getDy();
    }


    /**
     * Velocity is a constructor method
     * which initial members "dx" and "dy".
     * @param dx represents dx component of velocity.
     * @param dy represents dy component of velocity.
     */

        public Velocity(double dx, double dy) {
            this.dx = dx;
            this.dy = dy;
        }

    /**
     * getDx is a get method which returns dx of current velocity.
     * @return dx (double) represents dx component of velocity.
     */

    public double getDx() {
       return this.dx;
    }

    /**
     * getDy is a get method which returns dy of current velocity.
     *
     * @return dy represents dy component of velocity.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * applyToPoint Takes a point with position (x,y) and return a new point
     * with position (x+dx, y+dy).
     *
     * @param p represents a point.
     * @return newP represents new point.
     */
        public Point applyToPoint(Point p) {
            Point newP = new Point(p.getX() + this.dx, p.getY()
                    + this.dy);
            return newP;
        }


    /**
     * fromAngleAndSpeed sets velocity by using speed and angle of object.
     *
     * @param angle (double) represents angle of motion.
     * @param speed (double) represents speed of object.
     * @return velocity represents calculated velocity of object.
     */

    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * (Math.sin(Math.toRadians(angle)));
        double dy = -speed * (Math.cos(Math.toRadians(angle)));
        Velocity v = new Velocity(dx, dy);
        return v;
    }

    /**
     *  setVelocitiesBySize designed for setting velocities of balls by
     *  their size. function is given a sorted balls array (by size) and
     *  sets velocities as detailed above.
     *
     * @param balls (Ball[]) represents sorted array of balls.
     */


    public static void setVelocitiesBySize(Ball[] balls) {
        double minDx = MIN_DX;
        double minDy = MIN_DY;
        Random rand = new Random();
        // Given sorted balls array, set random velocities in descending order.
        for (int j = balls.length - ONE; j >= ZERO; j--) {
            // If size is over 50 - set velocity as 1,1.
            if (balls[j].getSize() >= MAX_SIZE) {
                balls[j].setVelocity(MIN_DX, MIN_DY);
            } else {
                // Set random velocity of current ball.
                minDx = minDx + rand.nextInt(RAND_BOUND);
                minDy = minDy + rand.nextInt(RAND_BOUND);
                balls[j].setVelocity(minDx, minDy);
            }
        }
    }
}


