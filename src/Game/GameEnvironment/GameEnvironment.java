package Game.GameEnvironment;

import Geometry.Line;
import Geometry.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Game.GameEnvironment.GameEnvironment has collidables list and a paddle.
 *
 * @author Itamar Berger 322554403
 * @version 1.0
 * @since 2023-04-24
 */

public class GameEnvironment {
    static final int ZERO = 0, ONE = 1;
    private java.util.List<Collidable> collidables;
    private Paddle paddle;

    /**
     * Game.GameEnvironment.GameEnvironment is a constructor,
     * defining collidables as given list.
     *
     * @param collidables (List of Game.GameEnvironment.Collidable type).
     */
    public GameEnvironment(java.util.List<Collidable> collidables) {
        this.collidables = collidables;
    }

    /**
     * Game.GameEnvironment.GameEnvironment is a default constructor,
     * defining collidables as null.
     */

    public GameEnvironment() {
        this.collidables = null;
    }

    /**
     * getPaddle returns paddle of Game.GameEnvironment.GameEnvironment.
     *
     * @return paddle (Game.GameEnvironment.Paddle).
     */
    public Paddle getPaddle() {
        return this.paddle;
    }

    /**
     * addCollidable adds given collidable to environment's collidables.
     *
     * @param c (Game.GameEnvironment.Collidable).
     */
    public void addCollidable(Collidable c) {
        // If collidables is empty - create new one and add given collidable.
        if (this.collidables == null) {
            this.collidables = new ArrayList<Collidable>();
        }
        this.collidables.add(c);
    }

    /**
     * getCollidables returns list of encironment's collidables.
     *
     * @return collidables (list of collidables).
     */
    public java.util.List<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * setPaddle updates environment's paddle.
     *
     * @param p (Game.GameEnvironment.Paddle).
     */

    public void setPaddle(Paddle p) {
        this.paddle = p;
    }


    /**
     * bubbleSortCollidableByDistance sorts collidable array,
     * by distance from start of a given line.
     * (closest collidable to start  would be at index 0 of colllidables array).
     *
     * @param arr     (Game.GameEnvironment.Collidable[]). array of collidables.
     * @param traject (Geometry.Line).
     */
    public static void bubbleSortCollidableByDistance(Collidable[] arr,
                                                      Line traject) {
        Collidable temp = null;
        // Bubble sort.
        for (int i = ZERO; i < arr.length - ONE; i++) {
            for (int j = ZERO; j < arr.length - i - ONE; j++) {

                /*
                 * Check which one of two collidables is the closest
                 * to start of traject.
                 */

                if (traject.start().distance(traject.
                        closestIntersectionToStartOfLine((arr[j])
                                .getCollisionRectangle()))
                        > traject.start().distance(traject.
                        closestIntersectionToStartOfLine((arr[j
                                + ONE]).getCollisionRectangle()))) {
                    // Swap collidables locations in array.
                    temp = arr[j];
                    arr[j] = arr[j + ONE];
                    arr[j + ONE] = temp;
                }
            }
        }
    }

    /**
     * getClosestCollision returns info of closest collision to start
     * of trajectory line.
     *
     * @param trajectory (Geometry.Line).
     * @return collisionInfo (Game.GameEnvironment.CollisionInfo type),
     * closeset collision's info.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // First copy collidables into an array structure.
        Collidable[] collidables1 = new Collidable[this.collidables.size()];
        for (int i = ZERO; i < collidables1.length; i++) {
            collidables1[i] = this.collidables.get(i);
        }
        // Sort collisions by distance from start of trajecy.
        bubbleSortCollidableByDistance(collidables1, trajectory);
        // Reset collidables as sorted above.
        this.collidables.clear();
        for (int i = ZERO; i < collidables1.length; i++) {
            this.collidables.add(collidables1[i]);
        }
        // If closest collision isn't met with trajectory - return null.
        if (trajectory.closestIntersectionToStartOfLine(
                collidables1[ZERO].getCollisionRectangle())
                == null) {
            return null;
        }
        Point collisionPoint = trajectory.
                closestIntersectionToStartOfLine((collidables1[ZERO]).
                        getCollisionRectangle());

        CollisionInfo c = new CollisionInfo(collisionPoint, collidables1[ZERO]);
        return c;
    }

    /**
     * Upates list of collidables of current gameEnvironment.
     *
     * @param l (List of Collidable).
     */
    public void setCollidables(List<Collidable> l) {
        this.collidables = l;
    }
}
