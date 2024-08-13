package Game.GameEnvironment;

import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * SpriteCollection has list of sprites.
 * @author Itamar Berger 322554403
 * @version 1.0
 * @since 2023-04-24
 */
public class SpriteCollection {
    private List<Sprite> spriteList;


    /**
     * Game.GameEnvironment.SpriteCollection is a default constructor.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<Sprite>();
    }

    /**
     * getSpriteList() returns spriteList of current SpriteCollection.
     *
     * @return sprite list (List).
     */
    public List<Sprite> getSpriteList() {
        return this.spriteList;
    }

    /**
     * setSpriteList updates spriteList of current SpriteCollection.
     *
     * @param l (List) represents updated list of sprites.
     */
    public void setSpriteList(List<Sprite> l) {
        this.spriteList = l;
    }

    /**
     * addSprite adds given sprite to current SpriteCollection's sprites list.
     *
     * @param s (Game.GameEnvironment.Sprite).
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * notifyAllTimePassed calls timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> l = new ArrayList<>(this.spriteList);
        for (Sprite s : l) {
            if (s == null) {
                continue;
            }
            s.timePassed();
        }
    }

    /**
     * drawAllOn calls drawOn(d) on all sprites.
     *
     * @param d (DrawSurface).
     */
    public void drawAllOn(DrawSurface d) {

        for (Sprite s : this.spriteList) {
            if (s == null) {
                continue;
            }
            if (s.getColor() != null) {
                d.setColor(s.getColor());
                s.drawOn(d);
            }
        }

    }
}

