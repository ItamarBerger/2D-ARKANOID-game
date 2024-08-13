package Game.GameEnvironment;

/**
 * Interface including methods add/remove hitListener.
 * @author Itamar Berger 322554403
 * @version 1.0
 * @since 2023-04-24
 */
public interface HitNotifier {

    /**
     * Adds hit listener to HitNotifier.
     * @param hl (HitListener)
     */
    void addHitListener(HitListener hl);
    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl (HitListener).
     */
    void removeHitListener(HitListener hl);
}

