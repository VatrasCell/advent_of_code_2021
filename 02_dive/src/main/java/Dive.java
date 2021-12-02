import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Dive {

    public static final String HORIZONTAL = "horizontal";
    public static final String DEPTH = "depth";
    public static final String AIM = "aim";

    public static void main(String[] args) {

        //fist step
        System.out.println(multiplyResultsForStepOne("input.data"));

        //second step
        System.out.println(multiplyResultsForStepTwo("input.data"));
    }

    public static int multiplyResultsForStepOne(String path) {
        return multiplyResults(calculateMovementStepOne(readInputValuesToList(path)));
    }

    public static int multiplyResultsForStepTwo(String path) {
        return multiplyResults(calculateMovementStepTwo(readInputValuesToList(path)));
    }

    public static int multiplyResults(Map<String, Integer> calculatedMovement) {
        return calculatedMovement.get(HORIZONTAL) * calculatedMovement.get(DEPTH);
    }

    public static Map<String, Integer> calculateMovementStepOne(List<MoveCommand> moveCommands) {
        Map<String, Integer> calculatedMovement = new HashMap<>();
        calculatedMovement.put(HORIZONTAL, 0);
        calculatedMovement.put(DEPTH, 0);

        for (MoveCommand moveCommand : moveCommands) {
            switch (moveCommand.getMoveEnum()) {
                case FORWARD:
                    calculatedMovement.put(HORIZONTAL, calculatedMovement.get(HORIZONTAL) + moveCommand.getSteps());
                    break;
                case DOWN:
                    calculatedMovement.put(DEPTH, calculatedMovement.get(DEPTH) + moveCommand.getSteps());
                    break;
                case UP:
                    calculatedMovement.put(DEPTH, calculatedMovement.get(DEPTH) - moveCommand.getSteps());
                    break;
            }
        }

        return calculatedMovement;
    }

    public static Map<String, Integer> calculateMovementStepTwo(List<MoveCommand> moveCommands) {
        Map<String, Integer> calculatedMovement = new HashMap<>();
        calculatedMovement.put(HORIZONTAL, 0);
        calculatedMovement.put(DEPTH, 0);
        calculatedMovement.put(AIM, 0);

        for (MoveCommand moveCommand : moveCommands) {
            switch (moveCommand.getMoveEnum()) {
                case FORWARD:
                    calculatedMovement.put(HORIZONTAL, calculatedMovement.get(HORIZONTAL) + moveCommand.getSteps());
                    calculatedMovement.put(DEPTH, calculatedMovement.get(DEPTH) +
                            (calculatedMovement.get(AIM) * moveCommand.getSteps()));
                    break;
                case DOWN:
                    calculatedMovement.put(AIM, calculatedMovement.get(AIM) + moveCommand.getSteps());
                    break;
                case UP:
                    calculatedMovement.put(AIM, calculatedMovement.get(AIM) - moveCommand.getSteps());
                    break;
            }
        }

        return calculatedMovement;
    }

    public static List<MoveCommand> readInputValuesToList(String path) {
        List<MoveCommand> moveCommands = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(getURL(path).getFile()))) {
            reader.lines().forEach(s -> {
                String[] strings = s.split(" ");
                moveCommands.add(new MoveCommand(MoveEnum.toEnum(strings[0]), Integer.parseInt(strings[1])));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return moveCommands;
    }

    public static URL getURL(String path) {
        return Objects.requireNonNull(Dive.class.getClassLoader().getResource(path));
    }
}
