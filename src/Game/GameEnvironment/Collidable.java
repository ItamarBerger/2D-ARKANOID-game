package Game.GameEnvironment;

import Geometry.Point;
import Geometry.Rectangle;

/**
 * Game.GameEnvironment.Collidable includes hit method and getCollisionRectangle.
 * @author Itamar Berger 322554403
 * @version 1.0
 * @since 2023-04-24
 */
public interface Collidable {


    /**
     * getCollisionRectangle returns the "collision shape" of the object.
     *
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * hit is a method calculating new velocity of object by given velocity
     * and collision point.
     *
     * @param collisionPoint (collision Geometry.Point)
     * @param currentVelocity (currentVelocity of ball)
     * @param hitter (Game.GameEnvironment.Ball.Game.GameEnvironment.Ball).
     * @return newVel (Game.GameEnvironment.Velocity)
     * represents new velocity of collided object.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * Add hit listener to current Collidable.
     * @param hl (HitListener).
     */
    void addHitListener(HitListener hl);

    /**
     * Returns true if Collidable is a border of game. false otherwise.
     * @return true/false (boolean).
     */
    boolean isBorder();
}

