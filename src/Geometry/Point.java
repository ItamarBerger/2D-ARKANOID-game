package Geometry;

/**
 * Geometry.Point defined by x and y values (coordinates in 2D dimension).
 *
 * @author Itamar Berger 322554403
 * @version 1.0
 * @since 2023-04-24
 */
public class Point {

    static final double EPSILON = Math.pow(5, -10);
    static final int ZERO = 0;
    private double x;
    private double y;


    /**
     * Geometry.Point is a constructor function.
     *
     * @param x represents location as relation to axis x.
     * @param y represents location as relation to axis y.
     */

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance is a measures distance between two points.
     *
     * @param other represents a point in 2D space.
     * @return a double number, represents distance.
     */

    public double distance(Point other) {
        if (other == null || this == null) {
            return Integer.MAX_VALUE;
        }
        // Calculating distance between two points in space.
        if (this.equals(other)) {
            return ZERO;
        }
        return Math.sqrt((this.x - other.x)
                * (this.x - other.x)
                + (this.y - other.y) * (this.y - other.y));
    }

    /**
     * equals designated to check if two points are identical.
     *
     * @param other represents a point in 2D space.
     * @return true if points are identical. false otherwise.
     */
    public boolean equals(Point other) {
        // Using threshold for comparing double (epsilon = 0.01).
        if (Math.abs(this.x - other.x) < EPSILON
                && Math.abs(this.y - other.y) < EPSILON) {
            return true;
        }
        return false;
    }

    /**
     * getX is a public method which return member "x" of current point.
     *
     * @return x (double), which is a location of a point upon axis x.
     */
    public double getX() {
        return this.x;
    }

    /**
     * getY is a public method which return member "y" of current point.
     *
     * @return y (double), which is a location of a point upon axis y.
     */

    public double getY() {
        return this.y;
    }

    /**
     * setX is a public method which update member "x" of current point.
     *
     * @param x represents a location upon axis x.
     */

    public void setX(double x) {
        this.x = x;
    }

    /**
     * setY is a public method which update member "y" of current point.
     *
     * @param y represents a location upon axis y.
     */

    public void setY(double y) {
        this.y = y;
    }

    /**
     * initX is a public method which initial member "x" of current point.
     */
    public void initX() {
        this.x = ZERO;
    }

    /**
     * initY is a public method which initial member "x" of current point.
     */

    public void initY() {
        this.y = ZERO;
    }
}
