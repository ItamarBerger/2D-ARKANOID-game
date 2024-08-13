package Geometry;

import Game.GameEnvironment.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Geometry.Line contains start and end points, length,
 * slope and intersection with axis y.
 *
 * @author Itamar Berger 322554403
 * @version 1.0
 * @since 2023-04-24
 */
public class Line implements Sprite {

    /*
     * In order to perform threshold and detect intersections,
     * we defined epsilon as 0.001.
     * this precision seems adequate in current scales and boundaries.
     * (may change in future)
     */

    static final double ZERO = 0, EPSILON = 0.001;
    static final int ONE = 1, TWO = 2;
    private Point start;
    private Point end;
    private Double length;

    private Double slope;
    private Double intersectWithY;
    private Color color;

    /**
     * getStart is a public get method.
     *
     * @return start, represents start point of line.
     */

    public Point getStart() {
        return this.start;
    }

    /**
     * getEnd is a public get method.
     *
     * @return end, represents end point of line.
     */

    public Point getEnd() {
        return this.end;
    }


    /**
     * Geometry.Line is a default constructor function.
     * Initials every field as zero or null.
     */

    public Line() {
        // Default constructor.
        this.start.initX();
        this.end.initY();
        // Define length as ths distance between start and end.
        this.length = (double) ZERO;
        // Define slope of current line.
        this.slope = null;
        this.intersectWithY = null;
    }


    /**
     * Geometry.Line is a constructor function.
     * Geometry.Line creates a Geometry.Line object with start and ene points.
     *
     * @param start represents start point.
     * @param end   represents an end point.
     */

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        if (start == null || end == null) {
            this.length = null;
            this.slope = null;
        } else {
            // Define length as ths distance between start and end.
            this.length = start.distance(end);

            // If line's slope isn't exist- define slope and intersectY as null.
            if (Math.abs(this.start.getX() - this.end.getX()) < EPSILON) {
                this.slope = null;
                this.intersectWithY = null;
                // If start and end have the same location - length = 0.
                if (this.start.getY() == this.end.getY()) {
                    this.length = (double) ZERO;
                }
                //If slope is exist , define line's slope using (y1-y2)/(x1-x2).
            } else {
                this.slope = ((this.start.getY() - this.end.getY())
                        / (this.start.getX() - this.end.getX()));
                // Calculate b of linear equation (y= ax + b).
                this.intersectWithY = this.slope * (-this.start.getX())
                        + this.start.getY();
            }
        }
    }

    /**
     * Geometry.Line is a constructor function.
     * Geometry.Line creates a Geometry.Line object using 4 double coordinates.
     *
     * @param x1 represents start location upon axis x.
     * @param y1 represents start location upon axis y.
     * @param x2 represents end location upon axis x.
     * @param y2 represents end location upon axis y.
     */

    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);

        // Define length as ths distance between start and end points.
        this.length = this.start.distance(end);

        // If line has no slope - define slope as null.
        if (Double.compare(x1, x2) == ZERO) {
            this.slope = null;
            this.intersectWithY = null;
            // If slope existS , define line's slope using (y1-y2)/(x1-x2).
        } else {
            this.slope = (this.start.getY() - this.end.getY())
                    / (this.start.getX() - this.end.getX());
            // Calculate b of linear equation (y= ax + b).
            this.intersectWithY = this.slope * (-this.start.getX())
                    + this.start.getY();
        }
    }


    /**
     * length is a method returning length of current line.
     *
     * @return length (double) of line.
     */

    public double length() {
        // Length of a line is the distance between end and start.
        return this.end.distance(start);
    }

    /**
     * middle is a method returning middle pooint of current line.
     *
     * @return middle (Geometry.Point) of line.
     */
    public Point middle() {
        // Calculate middle values of x and y.
        Double x1 = (this.start.getX() + this.end.getX()) / TWO;
        Double y1 = (this.start.getY() + this.end.getY()) / TWO;
        Point middle = new Point(x1, y1);
        return middle;
    }


    /**
     * start is a get method returning start point of current line.
     *
     * @return start (Geometry.Point) of line.
     */

    public Point start() {
        return this.start;
    }

    /**
     * end is a get method returning end point of current line.
     *
     * @return end (Geometry.Point) of line.
     */

    public Point end() {
        return this.end;
    }

    /**
     * isIntersectsForSameSlopes is a helper method returning true if intersect.
     * method is relevant for lines with identical (or none existing) slopes.
     *
     * @param other represents a line (type Geometry.Line).
     * @return true/false (boolean) if two lines intersect.
     */


    public boolean isIntersectsForSameSlopes(Line other) {

        /*
         * If both slopes exist - check if linear equations are identical.
         * else - lines have no intersection.
         * if both equations are equal, function would check if start/end of
         * current line equal to other line's. otherwise - return false.
         */

        if (this.slope != null && other.slope != null) {
            // Check if component b (of y= ax+b) is identical in two lines.
            if (Math.abs(this.intersectWithY - other.intersectWithY)
                    < EPSILON) {

                /*
                 * Since two line's equations are identical, we would check
                 * for intersection around edges of current and other line.
                 */

                if (this.start.distance(other.start) <= this.length
                        && this.end.distance(other.start) <= this.length) {
                    return true;
                }
                if (this.start.distance(other.end) <= this.length
                        && this.end.distance(other.end) <= this.length) {
                    return true;
                }
                if (this.end.distance(other.end) <= this.length
                        && this.start.distance(other.start) <= this.length) {
                    return true;
                }
                if (this.end.distance(other.start) <= this.length
                        && this.start.distance(other.end) <= this.length) {
                    return true;
                }

                /*
                 * If two lines have the same equation but have infinite,
                 * or zero common points - return false.
                 */

            } else {
                return false;
            }
        }

        /*
         * If both lines have no slope ( for example: x=x1),
         * function would check if lines intersect around edges.
         */

        if (this.start.distance(other.start) <= this.length
                && this.end.distance(other.start) <= this.length) {
            return true;
        }
        if (this.start.distance(other.end) <= this.length
                && this.end.distance(other.end) <= this.length) {
            return true;
        }
        if (this.end.distance(other.end) <= this.length
                && this.start.distance(other.start) <= this.length) {
            return true;
        }
        if (this.end.distance(other.start) <= this.length
                && this.start.distance(other.end) <= this.length) {
            return true;
        }


        // If lines have no common points - return false.
        return false;
    }


    /**
     * isIntersecting is a method returning true if intersect. else - false
     * method would divide into 3 cases and treat them separetley:
     * 1) both slopes don't exist.
     * 2) one of slopes isn't exist.
     * 3) both slopes exist.
     *
     * @param other represents a line (type Geometry.Line).
     * @return true/false (boolean) if two lines intersect.
     */

    public boolean isIntersecting(Line other) {
        boolean isIntersect = false;
        boolean areSlopesExist = true;
        // If both lines have no slope , call helper method for same slopes.
        if (this.slope == null && other.slope == null) {
            areSlopesExist = false;
            return isIntersectsForSameSlopes(other);
        }

        Double intersecX = (double) ZERO;
        Double intersecY = (double) ZERO;

        // If slope of current line isn't exist.
        if (this.slope == null) {
            areSlopesExist = false;
            // Use linear equation of other line to find possible intersection.
            intersecX = this.start.getX();
            intersecY = other.slope * intersecX + other.intersectWithY;
        } else {
            // If slope of other line isn't exist.
            if (other.slope == null) {
                areSlopesExist = false;
                // Use linear equation of this line to find possible intersec.
                intersecX = other.start.getX();
                intersecY = this.slope * intersecX + this.intersectWithY;
            } else {
                if (areSlopesExist) {
                    // Check if slopes aren't equal.
                    if (Double.compare(other.slope, this.slope) != ZERO) {

                        /*
                         * Find possible intersection point of lines
                         * (comparing equations), then substitute x value
                         *  in current line's equation and find y value.
                         */

                        intersecX = (this.intersectWithY
                                - other.intersectWithY)
                                / (other.slope - this.slope);
                        intersecY = this.slope
                                * intersecX + this.intersectWithY;
                    } else {
                        // If slopes are identical and exist , call helper.
                        return (isIntersectsForSameSlopes(other));
                    }
                }
            }
        }
        // Define intersec as possible intersec point of lines.
        Point intersec = new Point(intersecX, intersecY);

        /*
         * Check if intersection point reasons
         * with length of both lines.meaning, if distance
         *  between point and edges of lines suit length
         * of other and current line
         */

        // If one of lines is actually a point, then check if lines intersect.
        if (this.start.equals(this.end)) {
            if (Math.abs(this.start.getY() - intersecY) < EPSILON
                    && Math.abs(this.start.getX() - intersecX) < EPSILON) {
                return true;
            }
            return false;
        }

        if (other.start.equals(other.end)) {
            if (Math.abs(other.start.getY() - intersecY) < EPSILON
                    && Math.abs(other.start.getX() - intersecX) < EPSILON) {
                return true;
            }
            return false;
        }

        if (this.start.distance(intersec) <= this.length
                && this.end.distance(intersec) <= this.length
                && other.start.distance(intersec) <= other.length
                && other.end.distance(intersec) <= other.length) {
            return true;
        }
        // If possible intersection point doesn't reason with both lengths.
        return false;
    }

    /**
     * intersectionWith is a method returning intersection point of lines.
     * method would divide into 2 cases and treat them separetley:
     * 1) lines have no intersection - return null
     * 2) line intersect:
     * 2.1) both slopes don't exist.
     * 2.2) one of slopes isn't exist.
     * 2.3) both slopes exist.
     *
     * @param other represents a line (type Geometry.Line).
     * @return point (Geometry.Point) if two lines intersect. otherwise - null.
     */

    public Point intersectionWith(Line other) {
        boolean areSlopesExist = true;

        // If lines have no intersection, calculate intersection.
        if (!this.isIntersecting(other)) {
            return null;
        }

        /*
         * If lines intersect, calculate intersection:
         * 1) both slopes don't exist (example: x = 8)
         * 2) one of slopes don't exist (x = 8, y = x + 8)
         * 3) both slopes exist. (y = x + 8 , y = x + 9)
         */

        // If both lines are actually points.
        if (this.start.equals(this.end) && other.start.equals(other.end)) {
            return this.start;
        }
        // If only this line is actually a point.
        if (this.start.equals(this.end)) {
            return this.start;
        }
        // If only other line is actually a point.
        if (other.start.equals(other.end)) {
            return other.start;
        }


        // 1) both slopes don't exist (example: x = 8)
        if (this.slope == null && other.slope == null) {
            areSlopesExist = false;

            /*
             * Given the two lines intersect , search for single intersection
             *  around edges of lines.
             */

            Line includeLines = new Line(this.start, other.start);
            Line includeLines2 = new Line(this.start, other.end);
            Line includeLines3 = new Line(this.end, other.end);
            Line includeLines4 = new Line(this.end, other.start);

            if (Math.abs(includeLines2.length
                    - (this.length + other.length)) < EPSILON
                    || Math.abs(includeLines.length
                    - (this.length + other.length)) < EPSILON
                    || Math.abs(includeLines3.length
                    - (this.length + other.length)) < EPSILON
                    || Math.abs(includeLines4.length
                    - (this.length + other.length)) < EPSILON) {
                if (this.start.equals(other.start)) {
                    return this.start;
                }

                if (this.start.equals(other.end)) {
                    return this.start;
                }
                if (this.end.equals(other.start)) {
                    return this.end;
                }
                if (this.end.equals(other.end)) {
                    return this.end;
                }
            } else {
                // If lines have infinity intersection - return false.
                return null;
            }
        }

        Double intersecX = (double) ZERO;
        Double intersecY = (double) ZERO;

        // 2) one of slopes don't exist (x = 8, y = x + 8)
        if (this.slope == null) {
            areSlopesExist = false;
            intersecX = this.start.getX();
            intersecY = other.slope * intersecX + other.intersectWithY;
        } else {
            if (other.slope == null) {
                areSlopesExist = false;
                intersecX = other.start.getX();
                intersecY = this.slope * intersecX + this.intersectWithY;
            } else {
                // 3) both slopes exist. (y = x + 8 , y = x + 9)
                if (areSlopesExist) {
                    if (Double.compare(other.slope, this.slope) != ZERO) {
                        // If slopes aren't equal and but both slopes exist.
                        intersecX = (this.intersectWithY - other.intersectWithY)
                                / (other.slope - this.slope);
                        intersecY = this.slope * intersecX
                                + this.intersectWithY;
                    } else {

                        /*
                         * Given the two lines intersect and share the same
                         * slope,function would check if there is more than
                         *  singular intersection/ if so - return null.
                         *  else - return intersection.
                         */

                        Line includeLines = new Line(this.start, other.start);
                        Line includeLines2 = new Line(this.start, other.end);
                        Line includeLines3 = new Line(this.end, other.end);
                        Line includeLines4 = new Line(this.end, other.start);

                        if (Math.abs(includeLines2.length
                                - (this.length + other.length)) < EPSILON
                                || Math.abs(includeLines.length
                                - (this.length + other.length)) < EPSILON
                                || Math.abs(includeLines3.length
                                - (this.length + other.length)) < EPSILON
                                || Math.abs(includeLines4.length
                                - (this.length + other.length)) < EPSILON) {
                            if (this.start.equals(other.start)) {
                                return this.start;
                            }

                            if (this.start.equals(other.end)) {
                                return this.start;
                            }
                            if (this.end.equals(other.start)) {
                                return this.end;
                            }
                            if (this.end.equals(other.end)) {
                                return this.end;
                            }

                        } else {

                            /*
                             * If lines have infinity intersections -
                             * return false.
                             */
                            return null;
                        }
                    }
                }
            }
        }
        // Create intersection Geometry.Point and return.
        Point intersec = new Point(intersecX, intersecY);
        return intersec;
    }


    /**
     * equals is a method returning true/false if other line is equal
     * to current.
     *
     * @param other represents a line (type Geometry.Line).
     * @return true/false (boolean).
     */

    public boolean equals(Line other) {
        // If both lines identical.
        if ((this.start.equals(other.start) && this.end.equals(other.end)
                || (this.start.equals(other.end)
                && this.end.equals(other.start)))) {
            return true;
        }
        return false;
    }


    /**
     * getIntersectionWithY returns line's intersection with axis y (y value).
     *
     * @return intersecionWithY (double).
     */
    public double getIntersectionWithY() {
        return this.intersectWithY;
    }

    /**
     * getSlope returns line's slope.
     *
     * @return slope (double).
     */
    public double getSlope() {
        return this.slope;
    }

    /**
     * Given a rectangle, return closest intersection with given rectangle.
     *
     * @param rect represents a line (type Rect).
     * @return return the closest intersection point to start of the line.
     */

    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Point colisionPoint = null;
        // Create 4 lines of rect and check for intersection with current line.
        Point intersecWithDown = this.intersectionWith(rect.getDownLine());
        Point intersecWithUp = this.intersectionWith(rect.getUpLine());
        Point intersecWithRight = this.intersectionWith(rect.getRightLine());
        Point intersecWithLeft = this.intersectionWith(rect.getLeftLine());
        Line[] linesOfRect = {rect.getDownLine(),
                rect.getLeftLine(), rect.getUpLine(), rect.getRightLine()};

        Point closestIntersec = intersecWithDown;
        // Check what is the closest intersection with line.
        if (this.start.distance(intersecWithDown)
                > this.start.distance(intersecWithUp)) {
            closestIntersec = intersecWithUp;
        }
        if (this.start.distance(closestIntersec)
                > this.start.distance(intersecWithRight)) {
            closestIntersec = intersecWithRight;
        }
        if (this.start.distance(closestIntersec)
                > this.start.distance(intersecWithLeft)) {
            closestIntersec = intersecWithLeft;
        }
        // If there is no intersection - check if two lines fully collide.
        if (closestIntersec == null) {
            for (int i = (int) ZERO; i < linesOfRect.length; i++) {
                if (this.isIntersecting(linesOfRect[i])) {
                    if (this.distanceFromPoint(linesOfRect[i].start)
                            < EPSILON) {
                        return linesOfRect[i].start;
                    } else {
                        if (this.distanceFromPoint(linesOfRect[i].end)
                                < EPSILON) {
                            return linesOfRect[i].end;
                        }
                    }
                }
            }
            return null;
        }
        return closestIntersec;
    }

    /**
     * isPointInLine checks if given point is within current line.
     *
     * @param p1 (Geometry.Point).
     * @return true/false (boolean).
     */
    public boolean isPointInLine(Point p1) {
        if (p1 == null) {
            return false;
        }
        // If slope exists, use formula for distance of a straight graph.
        if (this.slope != null) {
            if (p1.getY() == this.slope * p1.getX()
                    + this.intersectWithY && p1.distance(this.start)
                    <= this.length && p1.distance(this.end) <= this.length) {
                return true;
            }
        } else {
            // If slope doesn't exist, check if point between line's edges.
            if (p1.distance(this.start)
                    <= this.length && p1.distance(this.end) <= this.length
                    && p1.getX() == this.start.getX()) {
                return true;
            }
        }
        return false;
    }

    /**
     * createTrajecy extends given line by given maxRadius.
     *
     * @param maxRadius (int). representing length of line's extension.
     */
    public void createTrajecy(int maxRadius) {
        if (this.slope != null) {
            /*
             * If slope exists, add maxRadius to x value of endPoint
             * and update y value of endPoint and line accordingly.
             */
            if (this.getEnd().getX() > this.getStart().getX()) {
                this.end = new Point(this.end.getX()
                        + maxRadius, this.slope
                        * (this.end.getX() + maxRadius)
                        + this.intersectWithY);
                return;
            }
            if (this.getEnd().getX() < this.getStart().getX()) {
                this.end = new Point(this.end.getX()
                        - maxRadius, this.slope
                        * (this.end.getX() - maxRadius) + this.intersectWithY);
                return;
            }
        }

        /*
         * If slope doesn't exist,simply add maxRadius to x value of endPoint
         * and add/subtract y by maxRadius.
         */

        if (this.getEnd().getY() < this.getStart().getY()) {
            this.end = new Point(this.end.getX(), this.end.getY()
                    - maxRadius);
        } else {
            this.end = new Point(this.end.getX(), this.end.getY()
                    + maxRadius);
        }
    }

    /**
     * distanceFromPoint returns distance between line and given point.
     *
     * @param p (Geometry.Point).
     * @return distance (double).
     */
    public double distanceFromPoint(Point p) {
        double distance = ZERO;
        // If slope exists, calculate distance using distance formula.
        if ((slope != null)) {
            distance = Math.abs((this.slope * p.getX()
                    - p.getY() + this.intersectWithY)
                    / (Math.sqrt(this.slope * this.slope + ONE)));
        } else {
            // If slope doesn't exist, calculate distance simple subtraction.
            distance = Math.abs(p.getX() - this.start.getX());
        }
        return distance;
    }

    @Override
    public void drawOn(DrawSurface d) {
        if (this.getColor() == null) {
            d.setColor(Color.BLACK);
        } else {
            d.setColor(this.getColor());
        }
        d.drawLine((int) this.getStart().getX(), (int) this.getStart().getY(),
                (int) this.getEnd().getX(), (int) this.getEnd().getY());
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    /**
     * Updates line's color.
     * @param c (Color).
     */
    public void setColor(Color c) {
        this.color = c;
    }

    @Override
    public void timePassed() {

    }
}
