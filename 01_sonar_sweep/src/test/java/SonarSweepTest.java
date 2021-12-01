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
    void measureIncrementWithWindows() {

        //arrange
        List<Integer> measurements = Arrays.asList(199, 200, 208, 210, 200, 207, 240, 269, 260, 263);

        //act
        int result = SonarSweep.measureIncrement(SonarSweep.getThreeMeasurementSlidingWindows(measurements));

        //assert
        assertEquals(5, result);
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

    @Test
    void getThreeMeasurementSlidingWindows() {

        //arrange
        List<Integer> measurements = Arrays.asList(199, 200, 208, 210, 200, 207, 240, 269, 260, 263);
        List<Integer> windows = Arrays.asList(607, 618, 618, 617, 647, 716, 769, 792);

        //act
        List<Integer> result = SonarSweep.getThreeMeasurementSlidingWindows(measurements);

        //assert
        assertEquals(windows, result);
    }
}