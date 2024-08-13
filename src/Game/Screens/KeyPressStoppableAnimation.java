package Game.Screens;

import Game.Animation.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * @author Itamar Berger 322554403
 * @version 1.0
 * @since 2023-04-24
 */
public class KeyPressStoppableAnimation implements Animation {
    private Animation animation;
    private KeyboardSensor keyboardSensor;
    private String key;
    private boolean stop;
    private boolean isAlreadyPressed;
    private String message;

    /**
     * Create KeyPressStoppableAnimation with given sensor,animation and key.
     * @param sensor (KeyboardSensor)
     * @param key (String)
     * @param animation (Animation)
     * @param message (String)
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor,
                                      String key, Animation animation,
                                      String message) {
        this.animation = animation;
        this.key = key;
        this.keyboardSensor = sensor;
        this.stop = false;
        this.message = message;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.keyboardSensor.isPressed(key)
                && !this.isAlreadyPressed) {
            this.stop = true;
        }
        if (!this.keyboardSensor.isPressed(key)) {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }


}
