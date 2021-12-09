import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SmokeBasinTest {

    @Test
    void readInputValuesToList() {

        //act
        List<List<Integer>> result = SmokeBasin.readInputValuesToList("test_input.data");

        //assert
        assertEquals(7, result.size());
    }

    @Test
    void isLowestNumber() {

        //act
        boolean result = SmokeBasin.isLowestNumber(5, new ArrayList<>(Arrays.asList(8, 8, 6, 6)));

        //assert
        assertTrue(result);
    }

    @Test
    void getAdjacentNumbers() {

        //arrange
        List<List<Integer>> field = SmokeBasin.readInputValuesToList("test_input.data");

        //act
        List<Integer> result = SmokeBasin.getAdjacentNumbers(field, 3, 3);

        //assert
        assertArrayEquals(Arrays.asList(8, 6, 8, 6).toArray(), result.toArray());
    }

    @Test
    void getAdjacentNumbersEdge() {

        //arrange
        List<List<Integer>> field = SmokeBasin.readInputValuesToList("test_input.data");

        //act
        List<Integer> result = SmokeBasin.getAdjacentNumbers(field, 2, 1);

        //assert
        assertArrayEquals(Arrays.asList(9, 9, 2, 9).toArray(), result.toArray());
    }

    @Test
    void getLowPoints() {

        //arrange
        List<List<Integer>> field = SmokeBasin.readInputValuesToList("test_input.data");

        //act
        List<Integer> result = SmokeBasin.getLowPoints(field);

        //assert
        assertArrayEquals(Arrays.asList(1, 0, 5, 5).toArray(), result.toArray());
    }

    @Test
    void calcResult() {

        //act
        int result = SmokeBasin.calcResultPartOne(new ArrayList<>(Arrays.asList(1, 0, 5, 5)));

        //assert
        assertEquals(15, result);
    }

    @Test
    void getLowPointPositions() {

        //arrange
        List<List<Integer>> field = SmokeBasin.readInputValuesToList("test_input.data");

        //act
        List<Point> result = SmokeBasin.getLowPointPositions(field);

        //assert
        assertEquals(4, result.size());
    }

    @Test
    void getBasinSizes() {

        //arrange
        List<List<Integer>> field = SmokeBasin.readInputValuesToList("test_input.data");
        List<Point> lowPointPositions = SmokeBasin.getLowPointPositions(field);

        //act
        List<Integer> result = SmokeBasin.getBasinSizes(field, lowPointPositions);

        //assert
        assertArrayEquals(Arrays.asList(3, 9, 14, 9).toArray(), result.toArray());
    }

    @Test
    void calcResultPartTwo() {

        //arrange
        List<List<Integer>> field = SmokeBasin.readInputValuesToList("test_input.data");
        List<Point> lowPointPositions = SmokeBasin.getLowPointPositions(field);
        List<Integer> basinSizes = SmokeBasin.getBasinSizes(field, lowPointPositions);

        //act
        int result = SmokeBasin.calcResultPartTwo(basinSizes);

        //assert
        assertEquals(1134, result);

    }
}