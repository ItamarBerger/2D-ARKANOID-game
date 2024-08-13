package Game.Animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * Class is responsible to run animations upon GUI screen.
 *
 * @author Itamar Berger 322554403
 * @version 1.0
 * @since 2023-04-24
 */

public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;
    private boolean running;

    /**
     * Create AnimationRunner with given GUI and isRunning flag.
     * @param gui (GUI).
     * @param running (boolean)
     */
    public AnimationRunner(GUI gui, boolean running) {
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
        this.gui = gui;
        this.running = running;
    }

    /**
     * Run current runner's animation upon its GUI screen
     * until animation should stop.
     * @param animation (Animation).
     */

    public void run(Animation animation) {
        boolean flag = false;
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        // While animation shouldn't stop - repeatedly doOneFrame and sleep.
        while (!flag) {
            if (animation.shouldStop()) {
                flag = !flag;
            }
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();
            animation.doOneFrame(d);
            this.gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * Return current runner's GUI.
     * @return gui (GUI).
     */

    public GUI getGui() {
        return gui;
    }

    /**
     * Update isRunning flag with given boolean parameter.
     * @param isRunning (boolean)
     */
    public void setIsRunning(boolean isRunning) {
        this.running = isRunning;
    }

    /**
     * Return if current runner is running.
     * @return true/false (boolean).
     */
    public boolean getIsRunning() {
        return this.running;
    }
}
