package Geometry;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;

/**
 * Geometry.Rectangle contains upper left point, width and height.
 * @author Itamar Berger 322554403
 * @version 1.0
 * @since 2023-04-24
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Line upLine, downLine, rightLine, leftLine;

    /**
     * Geometry.Rectangle initializes rectangle with upperLeft point,width and height.
     * @param upperLeft (Point)
     * @param width (double)
     * @param height (double)
     */
    // Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
        Point upRight = new Point(upperLeft.getX()
                + width, upperLeft.getY());
        Point downRight = new Point(upRight.getX(),
                upRight.getY() - height);
        Point downLeft = new Point(downRight.getX()
                - width, downRight.getY());
        // Represent rectangle using Lines.
        this.upLine = new Line(upperLeft, upRight);
        this.downLine = new Line(downLeft, downRight);
        this.rightLine = new Line(upRight, downRight);
        this.leftLine = new Line(upperLeft, downLeft);
        this.upperLeft = upperLeft;
        this.height = height;
        this.width = width;
    }

    /**
     * getWidth returns width of current rectangle.
     * @return width (double).
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * getHeight returns heught of current rectangle.
     * @return height (double).
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * getDownLine returns down line of current rectangle.
     * @return downLine (Geometry.Line).
     */
    public Line getDownLine() {
        return this.downLine;
    }
    /**
     * getUpLine returns up line of current rectangle.
     * @return upLine (Geometry.Line).
     */
    public Line getUpLine() {
        return this.upLine;
    }

    /**
     * getLeftLine returns left line of current rectangle.
     * @return leftLine (Geometry.Line).
     */
    public Line getLeftLine() {
        return this.leftLine;
    }

    /**
     * getRightLine returns right line of current rectangle.
     * @return rightLine (Geometry.Line).
     */
    public Line getRightLine() {
        return this.rightLine;
    }

    /**
     * getUpperLeft returns upperLeft of current rectangle.
     * @return upperLeft (Geometry.Point).
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * setUpperLeft updates upperLeft of current rectangle.
     * @param  upperLeft (Geometry.Point).
     */
    public void setUpperLeft(Point upperLeft) {
        this.upperLeft = upperLeft;
    }
    /**
     * intersectionPoints returns list of intersection points
     * of line with current rectangle.
     * @param line (Geometry.Line).
     * @return intersectionPoints (type List).
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> intersectionsPoints = new ArrayList<Point>();
        Point intersecWithDown = line.intersectionWith(this.getDownLine());
        Point intersecWithUp = line.intersectionWith(this.getUpLine());
        Point intersecWithRight = line.intersectionWith(this.getRightLine());
        Point intersecWithLeft = line.intersectionWith(this.getLeftLine());
        if (intersecWithDown != null) {
            intersectionsPoints.add(intersecWithDown);
        }
        if (intersecWithUp != null) {
            intersectionsPoints.add(intersecWithUp);
        }
        if (intersecWithLeft != null) {
            intersectionsPoints.add(intersecWithLeft);
        }
        if (intersecWithRight != null) {
            intersectionsPoints.add(intersecWithRight);
        }
        return intersectionsPoints;
    }

    /**
     * drawOn draws current rectangle on given surface.
     * @param d (DrawSurface).
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle((int) this.upperLeft.getX(),
                (int) (this.upperLeft.getY()), (int) width, (int) height);
    }
}
