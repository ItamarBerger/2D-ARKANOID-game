package Game.Score;

import Game.GameEnvironment.Block;
import Game.Counters.Counter;
import Game.GameEnvironment.Sprite;
import Geometry.Point;
import Geometry.Rectangle;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 *  ScoreIndicator is a score board  with a counter, stating current score.
 *
 * @author Itamar Berger 322554403
 * @version 1.0
 * @since 2023-05-24
 */
public class ScoreIndicator implements Sprite {

   static final int HEIGHT_OF_HORIZONTAL_BORDERS = 800, WIDTH_OF_BORDERS = 30;
    private Counter score;
    private  Color color = Color.cyan;
    private String levelName;

    private Block block = new Block(new Rectangle(new Point(0, 30),
            HEIGHT_OF_HORIZONTAL_BORDERS, WIDTH_OF_BORDERS));

    public ScoreIndicator(String levelName, Counter score){
        this.levelName = levelName;
        this.score = score;
    }
    public ScoreIndicator(){
        this.score = score;
    }
    @Override
    public void drawOn(DrawSurface d) {
        this.block.drawOn(d);
        d.drawText(380, 19, "Game.Score: "
                + String.valueOf(this.score.getValue()), 15);
        if(this.levelName!=null) {
            d.drawText(600, 19, "Level Name: " + this.levelName, 15);
        }
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public void timePassed() {
        return;
    }

    /**
     * Updates score of current ScoreIndicator.
     * @param score (Counter) represents updated score.
     */
    public void setScore(Counter score) {
        this.score = score;
    }
}
