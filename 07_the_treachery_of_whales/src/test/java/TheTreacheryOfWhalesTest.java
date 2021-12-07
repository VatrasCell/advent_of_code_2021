import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TheTreacheryOfWhalesTest {

    @Test
    void getMedian() {

        //arrange
        List<Integer> sortedIntegers = Arrays.asList(16, 1, 2, 0, 4, 2, 7, 1, 2, 14);
        Collections.sort(sortedIntegers);

        //act
        int result = TheTreacheryOfWhales.getMedian(sortedIntegers);

        //assert
        assertEquals(2, result);
    }

    @Test
    void calculateFuelNeed() {
        //arrange
        List<Integer> integers = Arrays.asList(16, 1, 2, 0, 4, 2, 7, 1, 2, 14);

        //act
        int result = TheTreacheryOfWhales.calculateFuelNeed(integers);

        //assert
        assertEquals(37, result);
    }

    @Test
    void calculateFuelNeedBiggerCosts() {
        //arrange
        List<Integer> integers = Arrays.asList(16, 1, 2, 0, 4, 2, 7, 1, 2, 14);

        //act
        double result = TheTreacheryOfWhales.calculateFuelNeedBiggerCosts(integers);

        //assert
        assertEquals(168, result);
    }
}