import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DiveTest {

    @Test
    void readInputValuesToList() {

        //arrange
        List<MoveCommand> moveCommands = Arrays.asList(
                new MoveCommand(MoveEnum.FORWARD, 5),
                new MoveCommand(MoveEnum.DOWN, 5),
                new MoveCommand(MoveEnum.FORWARD, 8),
                new MoveCommand(MoveEnum.UP, 3),
                new MoveCommand(MoveEnum.DOWN, 8),
                new MoveCommand(MoveEnum.FORWARD, 2));

        //act
        List<MoveCommand> result = Dive.readInputValuesToList("test_input.data");

        //assert
        assertArrayEquals(moveCommands.toArray(), result.toArray());
    }

    @Test
    void calculateMovementStepOne() {

        //arrange
        List<MoveCommand> moveCommands = Arrays.asList(
                new MoveCommand(MoveEnum.FORWARD, 5),
                new MoveCommand(MoveEnum.DOWN, 5),
                new MoveCommand(MoveEnum.FORWARD, 8),
                new MoveCommand(MoveEnum.UP, 3),
                new MoveCommand(MoveEnum.DOWN, 8),
                new MoveCommand(MoveEnum.FORWARD, 2));

        //act
        Map<String, Integer> result = Dive.calculateMovementStepOne(moveCommands);

        //assert
        assertEquals(15, result.get(Dive.HORIZONTAL));
        assertEquals(10, result.get(Dive.DEPTH));
    }

    @Test
    void calculateMovementStepTwo() {

        //arrange
        List<MoveCommand> moveCommands = Arrays.asList(
                new MoveCommand(MoveEnum.FORWARD, 5),
                new MoveCommand(MoveEnum.DOWN, 5),
                new MoveCommand(MoveEnum.FORWARD, 8),
                new MoveCommand(MoveEnum.UP, 3),
                new MoveCommand(MoveEnum.DOWN, 8),
                new MoveCommand(MoveEnum.FORWARD, 2));

        //act
        Map<String, Integer> result = Dive.calculateMovementStepTwo(moveCommands);

        //assert
        assertEquals(15, result.get(Dive.HORIZONTAL));
        assertEquals(60, result.get(Dive.DEPTH));
    }

    @Test
    void multiplyResultsStepOne() {

        //act
        int result = Dive.multiplyResultsForStepOne("test_input.data");

        assertEquals(150, result);
    }

    @Test
    void multiplyResultsStepTwo() {

        //act
        int result = Dive.multiplyResultsForStepTwo("test_input.data");

        assertEquals(900, result);
    }
}