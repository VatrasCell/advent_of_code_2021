import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Dive {

    public static void main(String[] args) {

        //first step
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

    public static int multiplyResults(CalculatedMovement calculatedMovement) {
        return calculatedMovement.getHorizontal() * calculatedMovement.getDepth();
    }

    public static CalculatedMovement calculateMovementStepOne(List<MoveCommand> moveCommands) {
        CalculatedMovement calculatedMovement = new CalculatedMovement();

        for (MoveCommand moveCommand : moveCommands) {
            switch (moveCommand.getMoveEnum()) {
                case FORWARD:
                    calculatedMovement.addHorizontal(moveCommand.getSteps());
                    break;
                case DOWN:
                    calculatedMovement.addDepth(moveCommand.getSteps());
                    break;
                case UP:
                    calculatedMovement.addDepth(-moveCommand.getSteps());
                    break;
            }
        }

        return calculatedMovement;
    }

    public static CalculatedMovement calculateMovementStepTwo(List<MoveCommand> moveCommands) {
        CalculatedMovement calculatedMovement = new CalculatedMovement();

        for (MoveCommand moveCommand : moveCommands) {
            switch (moveCommand.getMoveEnum()) {
                case FORWARD:
                    calculatedMovement.addHorizontal(moveCommand.getSteps());
                    calculatedMovement.addDepth(calculatedMovement.getAim() * moveCommand.getSteps());
                    break;
                case DOWN:
                    calculatedMovement.addAim(moveCommand.getSteps());
                    break;
                case UP:
                    calculatedMovement.addAim(-moveCommand.getSteps());
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
