package Game.GameEnvironment;

import Geometry.Rectangle;
import biuoop.DrawSurface;

/**
 * Class inherits Block and draws blocks with no boundary at the edges.
 * @author Itamar Berger 322554403
 * @version 1.0
 * @since 2023-04-24
 */

public class BlockWithoutBoundaries extends Block {

    /**
     * Create a block with no boundaries.
     * @param rec (Rectangle).
     */
    public BlockWithoutBoundaries(Rectangle rec) {
        super(rec);
    }

    /**
     * Draw a block with no boundaries upon given surface.
     * @param d (DrawSurface).
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.fillRectangle((int) super.getRectangle().getUpperLeft().getX(),
                (int) super.getRectangle().getUpperLeft().getY()
                        - (int) super.getRectangle().getHeight(),
                (int) super.getRectangle().getWidth(),
                (int) super.getRectangle().getHeight());
    }
}
