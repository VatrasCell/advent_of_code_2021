import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BinaryDiagnosticTest {

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
    void readInputValuesToList() {

        //act
        List<DiagnosticBinary> result = BinaryDiagnostic.readInputValuesToList("test_input.data");

        //assert
        assertArrayEquals(binaries.toArray(), result.toArray());
    }

    @Test
    void binaryToDecimal() {

        //arrange
        String binary = "10110";

        //act
        int result = BinaryDiagnostic.binaryToDecimal(binary);

        //assert
        assertEquals(22, result);
    }

    @Test
    void binaryToDecimal2() {

        //arrange
        String binary = "01001";

        //act
        int result = BinaryDiagnostic.binaryToDecimal(binary);

        //assert
        assertEquals(9, result);
    }

    @Test
    void calculatePowerConsumption() {

        //arrange
        BinaryRateCalculator calculator = new BinaryRateCalculator(binaries);

        //act
        int result = BinaryDiagnostic.calculatePowerConsumption(
                calculator.calculateGammaRate(),
                calculator.calculateEpsilonRate());

        //assert
        assertEquals(198, result);
    }

    @Test
    void calculateLifeSupportRating() {

        //arrange
        BinaryRateCalculator calculator = new BinaryRateCalculator(binaries);
        BinaryRateCalculator calculator2 = new BinaryRateCalculator(binaries);

        //act
        int result = BinaryDiagnostic.calculateLifeSupportRating(
                calculator.findOxygenGeneratorRating(),
                calculator2.findCO2ScrubberRating());

        //assert
        assertEquals(230, result);
    }
}