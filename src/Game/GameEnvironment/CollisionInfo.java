package Game.GameEnvironment;

import Geometry.Point;

/**
 * Game.GameEnvironment.CollisionInfo has collisionPoint and collisionObject as its members.
 * @author Itamar Berger 322554403
 * @version 1.0
 * @since 2023-04-24
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Game.GameEnvironment.CollisionInfo defines collsion point and collision  object.
     * @param collisionPoint (Geometry.Point).
     * @param collisionObject (Game.GameEnvironment.Collidable).
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionObject = collisionObject;
        this.collisionPoint = collisionPoint;

    }


    /**
     * setCollisionObject updates collision object.
     * @param c (Game.GameEnvironment.Collidable).
     */
    public void setCollisionObject(Collidable c) {
        this.collisionObject = c;
    }

    /**
     *  collisionPoint returns collision point.
     * @return  collisionPoint (Geometry.Point).
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * collisionObject returns collision object.
     *
     * @return collisionObject (Game.GameEnvironment.Collidable).
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }

    /**
     * setCollisionPoint updates collision point.
     *
     * @param  collision (Geometry.Point).
     */
    public void setCollisionPoint(Point collision) {
        this.collisionPoint = collision;
    }
}
