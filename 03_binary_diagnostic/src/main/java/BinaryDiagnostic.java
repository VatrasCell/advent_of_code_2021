import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BinaryDiagnostic {

    public static void main(String[] args) {

        //first step
        BinaryRateCalculator calculator = new BinaryRateCalculator(readInputValuesToList("input.data"));
        System.out.println(
                calculatePowerConsumption(
                        calculator.calculateGammaRate(),
                        calculator.calculateEpsilonRate())
        );

        //second step

        BinaryRateCalculator calculator2 = new BinaryRateCalculator(readInputValuesToList("input.data"));
        BinaryRateCalculator calculator3 = new BinaryRateCalculator(readInputValuesToList("input.data"));
        System.out.println(
                calculateLifeSupportRating(
                        calculator2.findOxygenGeneratorRating(),
                        calculator3.findCO2ScrubberRating())
        );
    }

    public static int calculatePowerConsumption(String gammaRate, String epsilonRate) {
        return binaryToDecimal(gammaRate) * binaryToDecimal(epsilonRate);
    }

    public static int calculateLifeSupportRating(String oxygenGeneratorRating, String CO2ScrubberRating) {
        return binaryToDecimal(oxygenGeneratorRating) * binaryToDecimal(CO2ScrubberRating);
    }

    public static int binaryToDecimal(String binary) {
        String[] reverseBits = new StringBuilder(binary).reverse().toString().split("");
        int result = 0;

        for (int i = 0; i < reverseBits.length; ++i) {
            if (reverseBits[i].equals("1")) {
                result += Math.pow(2, i);
            }
        }

        return result;
    }

    public static List<DiagnosticBinary> readInputValuesToList(String path) {
        List<DiagnosticBinary> binaries = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(getURL(path).getFile()))) {
            reader.lines().forEach(s -> binaries.add(new DiagnosticBinary(s)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return binaries;
    }

    public static URL getURL(String path) {
        return Objects.requireNonNull(BinaryDiagnostic.class.getClassLoader().getResource(path));
    }
}
