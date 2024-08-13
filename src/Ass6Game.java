import Game.Animation.AnimationRunner;
import Game.Levels.GameFlow;
import Game.Levels.GameLevel1;
import Game.Levels.GameLevel2;
import Game.Levels.GameLevel3;
import Game.Levels.LevelInformation;
import biuoop.GUI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Itamar Berger 322554403
 * @version 1.0
 * @since 2023-04-24
 */
public class Ass6Game {

    static final List<LevelInformation> LEVEL_INFORMATION_LIST =
            new ArrayList<>();

    /**
     * Given a String array, convert to int and run levels in requested order.
     * @param levelInformations (List of LevelInformation)
     * @param args (String [])
     */
    @SuppressWarnings("checkstyle:MethodParamPad")
    public static void runLevelsByRequestedOrded(
            List<LevelInformation> levelInformations, String[] args) {

        List<LevelInformation> updatedListOfLevels = new ArrayList<>();
        GUI g = new GUI("Ass6Flow", 800, 600);
        AnimationRunner r = new AnimationRunner(g, true);
        GameFlow flow = new GameFlow(r, r.getGui().getKeyboardSensor());
        int countNumOfLevels = 0;
        for (int i = 0; i < args.length; i++) {

            try {
                updatedListOfLevels.add(levelInformations.
                        get(Integer.parseInt(args[i]) - 1));
                countNumOfLevels++;
            } catch (Exception ignored) {

            }
        }
        if (countNumOfLevels == 0) {
            g.close();
        } else {
            flow.runLevels(updatedListOfLevels);
        }
    }

    /**
     * Given args array, run arknoid game in requested order in args.
     * @param args (String[])
     */
    public static void main(String[] args) {
        GUI g = new GUI("Ass6Flow", 800, 600);
        AnimationRunner r = new AnimationRunner(g, true);
        GameFlow flow = new GameFlow(r, r.getGui().getKeyboardSensor());
        LEVEL_INFORMATION_LIST.add(new GameLevel1());
        LEVEL_INFORMATION_LIST.add(new GameLevel2());
        LEVEL_INFORMATION_LIST.add(new GameLevel3());

        // If no arguments were found.
        if (args.length == 0 || args[0].equals("${args}")) {
            flow.runLevels(LEVEL_INFORMATION_LIST);
        } else {
            runLevelsByRequestedOrded(LEVEL_INFORMATION_LIST, args);
        }
    }
}



