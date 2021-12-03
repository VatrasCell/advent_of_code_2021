import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BinaryRateCalculatorTest {

    final List<DiagnosticBinary> binaries = Arrays.asList(
            new DiagnosticBinary("00100"),
            new DiagnosticBinary("11110"),
            new DiagnosticBinary("10110"),
            new DiagnosticBinary("10111"),
            new DiagnosticBinary("10101"),
            new DiagnosticBinary("01111"),
            new DiagnosticBinary("00111"),
            new DiagnosticBinary("11100"),
            new DiagnosticBinary("10000"),
            new DiagnosticBinary("11001"),
            new DiagnosticBinary("00010"),
            new DiagnosticBinary("01010"));

    @Test
    void calculateGammaRate() {

        //arrange
        BinaryRateCalculator calculator = new BinaryRateCalculator(binaries);

        //act
        String result = calculator.calculateGammaRate();

        assertEquals("10110", result);
    }

    @Test
    void calculateEpsilonRate() {

        //arrange
        BinaryRateCalculator calculator = new BinaryRateCalculator(binaries);

        //act
        String result = calculator.calculateEpsilonRate();

        assertEquals("01001", result);
    }

    @Test
    void filterBinariesForOxygenGeneratorRating() {

        //arrange
        final List<DiagnosticBinary> filteredBinaries = Arrays.asList(
                new DiagnosticBinary("11110"),
                new DiagnosticBinary("10110"),
                new DiagnosticBinary("10111"),
                new DiagnosticBinary("10101"),
                new DiagnosticBinary("11100"),
                new DiagnosticBinary("10000"),
                new DiagnosticBinary("11001"));

        BinaryRateCalculator calculator = new BinaryRateCalculator(binaries);

        //act
        calculator.filterBinariesForOxygenGeneratorRating(0);
        List<DiagnosticBinary> result = calculator.getBinaries();

        //assert
        assertArrayEquals(filteredBinaries.toArray(), result.toArray());
    }

    @Test
    void filterBinariesForOxygenGeneratorRating2() {

        //arrange
        final List<DiagnosticBinary> inputBinaries = Arrays.asList(
                new DiagnosticBinary("11110"),
                new DiagnosticBinary("10110"),
                new DiagnosticBinary("10111"),
                new DiagnosticBinary("10101"),
                new DiagnosticBinary("11100"),
                new DiagnosticBinary("10000"),
                new DiagnosticBinary("11001"));

        final List<DiagnosticBinary> filteredBinaries = Arrays.asList(
                new DiagnosticBinary("10110"),
                new DiagnosticBinary("10111"),
                new DiagnosticBinary("10101"),
                new DiagnosticBinary("10000"));

        BinaryRateCalculator calculator = new BinaryRateCalculator(inputBinaries);

        //act
        calculator.filterBinariesForOxygenGeneratorRating(1);
        List<DiagnosticBinary> result = calculator.getBinaries();

        //assert
        assertArrayEquals(filteredBinaries.toArray(), result.toArray());
    }

    @Test
    void findOxygenGeneratorRating() {

        //arrange
        BinaryRateCalculator calculator = new BinaryRateCalculator(binaries);

        //act
        String result = calculator.findOxygenGeneratorRating();

        //assert
        assertEquals("10111", result);
    }

    @Test
    void filterBinariesForCO2ScrubberRating() {

        //arrange
        final List<DiagnosticBinary> filteredBinaries = Arrays.asList(
                new DiagnosticBinary("00100"),
                new DiagnosticBinary("01111"),
                new DiagnosticBinary("00111"),
                new DiagnosticBinary("00010"),
                new DiagnosticBinary("01010"));

        BinaryRateCalculator calculator = new BinaryRateCalculator(binaries);

        //act
        calculator.filterBinariesForCO2ScrubberRating(0);
        List<DiagnosticBinary> result = calculator.getBinaries();

        //assert
        assertArrayEquals(filteredBinaries.toArray(), result.toArray());
    }

    @Test
    void filterBinariesForCO2ScrubberRating2() {

        //arrange
        final List<DiagnosticBinary> inputBinaries = Arrays.asList(
                new DiagnosticBinary("00100"),
                new DiagnosticBinary("01111"),
                new DiagnosticBinary("00111"),
                new DiagnosticBinary("00010"),
                new DiagnosticBinary("01010"));

        final List<DiagnosticBinary> filteredBinaries = Arrays.asList(
                new DiagnosticBinary("01111"),
                new DiagnosticBinary("01010"));

        BinaryRateCalculator calculator = new BinaryRateCalculator(inputBinaries);

        //act
        calculator.filterBinariesForCO2ScrubberRating(1);
        List<DiagnosticBinary> result = calculator.getBinaries();

        //assert
        assertArrayEquals(filteredBinaries.toArray(), result.toArray());
    }

    @Test
    void findCO2ScrubberRating() {

        //arrange
        BinaryRateCalculator calculator = new BinaryRateCalculator(binaries);

        //act
        String result = calculator.findCO2ScrubberRating();

        //assert
        assertEquals("01010", result);
    }
}