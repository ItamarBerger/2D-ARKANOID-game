package Game.Levels;


import Game.Animation.AnimationRunner;
import Game.Counters.Counter;
import Game.Screens.EndScreen;
import Game.Screens.KeyPressStoppableAnimation;
import Game.Screens.StartScreen;
import biuoop.KeyboardSensor;
import java.util.List;

/**
 * @author Itamar Berger 322554403
 * @version 1.0
 * @since 2023-04-24
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;

    /**
     * Create GameFlow with a Runner and a keyboard sensor.
     * @param ar (AnimationRunner)
     * @param ks (KeyboardSensor)
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
    }

    /**
     * Run given levels using current gameFlow's runner.
     * @param levels (List of LevelInformation).
     */
    public void runLevels(List<LevelInformation> levels) {
        boolean didPlayerLost = false;
        Counter overallScore = new Counter(0);
        this.animationRunner.run(new KeyPressStoppableAnimation(
                this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                new StartScreen(this.keyboardSensor), null));
        // Run all levels in given list.
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo,
                    this.keyboardSensor,
                    this.animationRunner, overallScore);
            level.initialize();
            level.run();
            if (level.getCounterBalls().getValue() <= 0) {
                didPlayerLost = true;
                break;
            }
        }
        // Present endScreen by game's statue at the end.
        if (didPlayerLost) {
            // If player has lost - present game over screen.
            this.animationRunner.run(new KeyPressStoppableAnimation(
                    this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                    new EndScreen(
                    false, overallScore,
                    this.animationRunner.getGui().getKeyboardSensor()),
                    "Game Over. Your score is: "
                            + overallScore.getValue()));
        } else {
            // If player has won - present winning screen.
            this.animationRunner.run(new KeyPressStoppableAnimation(
                    this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                    new EndScreen(
                    true, overallScore,
                    this.animationRunner.getGui().getKeyboardSensor()),
                    "You Win! Your score is : "
                            + overallScore.getValue()));
        }
        this.animationRunner.getGui().close();
    }
}
