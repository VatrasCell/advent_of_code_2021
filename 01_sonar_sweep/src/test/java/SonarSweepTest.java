import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SonarSweepTest {

    @Test
    void measureIncrement() {

        //arrange
        List<Integer> measurements = Arrays.asList(199, 200, 208, 210, 200, 207, 240, 269, 260, 263);

        //act
        int result = SonarSweep.measureIncrement(measurements);

        //assert
        assertEquals(7, result);
    }

    @Test
    void readInputValuesToList() {

        //arrange
        List<Integer> measurements = Arrays.asList(199, 200, 208, 210, 200, 207, 240, 269, 260, 263);

        //act
        List<Integer> result = SonarSweep.readInputValuesToList("test_input.data");

        //assert
        assertEquals(measurements, result);
    }
}