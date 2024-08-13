package Game.GameEnvironment;
import Game.Levels.GameLevel;
import Geometry.Point;
import Geometry.Rectangle;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * Block is a class contains members such as rectangle and color of block.
 *
 * @author Itamar Berger 322554403
 * @version 1.0
 * @since 2023-04-24
 */
public class Block implements Collidable, Sprite, HitNotifier {
    static final double ZERO = 0, EPSILON = 0.001;
    private Rectangle rectangle;
    private Color color;
    private List<HitListener> hitListeners;

    private boolean isBorder;

    /**
     * timePassed would move blocks in game (in future).
     */
    @Override
    public void timePassed() {

    }

    /**
     * Block is a constructor, defining given rectangle as block's shape.
     * @param isBorder (boolean).
     * @param rec represents rectangle.
     */
    public Block(Rectangle rec, boolean isBorder) {
        this.rectangle = rec;
        this.hitListeners = new ArrayList<>();
        this.isBorder = isBorder;
    }

    /**
     * A constructor of Block with a given rectangle.
     * @param rec (Rectangle).
     */
    public Block(Rectangle rec) {
        this.rectangle = rec;
        this.hitListeners = new ArrayList<>();
        this.isBorder = false;
    }


    /**
     * getRectangle is a get method, which returns block's rectangle.
     *
     * @return rectangle (Rectangle type).
     */
    public Rectangle getRectangle() {
        return rectangle;
    }

    /**
     * getCollisionRectangle is a get method, which returns block's rectangle.
     *
     * @return rectangle (Rectangle type).
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * getColor is a get method, which returns block's color.
     *
     * @return color (Color type).
     */

    @Override
    public Color getColor() {
        return this.color;
    }

    /**
     * setRectangle is a set method, which sets block's rectangle.
     *
     * @param r1 (Rectangle type).
     */
    public void setRectangle(Rectangle r1) {
        this.rectangle = r1;
    }

    /**
     * setColor is a function which sets block's color.
     *
     * @param c represents given color.
     */
    public void setColor(Color c) {
        this.color = c;
    }

    /**
     * hit is a method, designated to notify an object that we collided with
     * it at collisionPoint with a given velocity.
     *
     * @param collisionPoint  (Point).
     * @param currentVelocity (Velocity) represents current velocity of object.
     * @return newVel (Velocity) represents new velocity after collision.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        double newDx = currentVelocity.getDx();
        double newDy = currentVelocity.getDy();


        // If collision is horizontal - change dy component.
        if ((this.rectangle.getUpLine().distanceFromPoint(collisionPoint)
                <= EPSILON)
                || (this.rectangle.getDownLine()
                .distanceFromPoint(collisionPoint)) <= EPSILON) {
            newDy = -newDy;
        }
        //If collision isn't horizontal - change dx component.
        if ((this.rectangle.getRightLine().distanceFromPoint(collisionPoint))
                <= EPSILON || (this.rectangle.getLeftLine()
                .distanceFromPoint(collisionPoint)) <= EPSILON) {
            newDx = -newDx;
        }
        if (newDx != currentVelocity.getDx()
                || newDy != currentVelocity.getDy()) {
            this.notifyHit(hitter);
        }
        Velocity newVel = new Velocity(newDx, newDy);
        return newVel;
    }


    /**
     * drawOn is a method, designated to draw block on given surface.
     *
     * @param d (DrawSurface).
     */
    @Override
    public void drawOn(DrawSurface d) {

        // First, draw rectangle on surface.
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY()
                        - (int) this.rectangle.getHeight(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());

        d.setColor(Color.BLACK);
        // Draw around edges of rectangle.
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY()
                        - (int) this.rectangle.getHeight(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
    }

    /**
     * Remove current block from game.
     *
     * @param gameLevel (Game).
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public boolean isBorder() {
        return this.isBorder;
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners =
                new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
