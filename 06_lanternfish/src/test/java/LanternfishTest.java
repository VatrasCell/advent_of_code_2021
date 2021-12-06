import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LanternfishTest {

    @Test
    void processLifeCycle() {

        //assert
        List<Integer> lanternfishs = new LinkedList<>(Arrays.asList(3, 4, 3, 1, 2));
        List<Integer> lanternfishsAfter18Days = Arrays.asList(6, 0, 6, 4, 5, 6, 0, 1, 1, 2, 6, 0, 1, 1, 1, 2, 2, 3, 3, 4, 6, 7, 8, 8, 8, 8);

        //act
        List<Integer> result = Lanternfish.processLifeCycle(lanternfishs, 18);

        //assert
        assertArrayEquals(lanternfishsAfter18Days.toArray(), result.toArray());

    }

    @Test
    void processLifeCycleResult() {

        //assert
        List<Integer> lanternfishs = new LinkedList<>(Arrays.asList(3, 4, 3, 1, 2));

        //act
        List<Integer> result = Lanternfish.processLifeCycle(lanternfishs, 80);

        //assert
        assertEquals(5934, result.size());

    }

    @Test
    void readInputValuesToList() {

        //assert
        List<Integer> lanternfishs = new LinkedList<>(Arrays.asList(3, 4, 3, 1, 2));

        //act
        List<Integer> result = Lanternfish.readInputValuesToList("test_input.data");

        //assert
        assertArrayEquals(lanternfishs.toArray(), result.toArray());
    }
}