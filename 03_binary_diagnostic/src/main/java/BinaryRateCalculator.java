import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BinaryRateCalculator {

    private final Map<Integer, Integer> countOfOnes = new HashMap<>();
    private final Map<Integer, Integer> countOfZeros = new HashMap<>();
    private final int length;
    private List<DiagnosticBinary> binaries;

    public BinaryRateCalculator(List<DiagnosticBinary> binaries) {
        this.binaries = binaries;
        this.length = binaries.get(0).getLength();
        calculateBitCounts();
    }

    public List<DiagnosticBinary> getBinaries() {
        return binaries;
    }

    public String calculateGammaRate() {
        StringBuilder binary = new StringBuilder();
        for (int i = 0; i < length; ++i) {
            binary.append(countOfOnes.get(i) > countOfZeros.get(i) ? "1" : "0");
        }

        return binary.toString();
    }

    public String calculateEpsilonRate() {
        StringBuilder binary = new StringBuilder();
        for (int i = 0; i < length; ++i) {
            binary.append(countOfOnes.get(i) < countOfZeros.get(i) ? "1" : "0");
        }

        return binary.toString();
    }

    public String findOxygenGeneratorRating() {
        int i = 0;
        while (binaries.size() > 1) {
            filterBinariesForOxygenGeneratorRating(i);
            ++i;
        }

        return binaries.get(0).getBinary();
    }

    public void filterBinariesForOxygenGeneratorRating(int pos) {
        if (countOfOnes.get(pos) >= countOfZeros.get(pos)) {
            binaries = binaries.stream()
                    .filter(diagnosticBinary -> diagnosticBinary.getBit(pos) == 1)
                    .collect(Collectors.toList());
        } else {
            binaries = binaries.stream()
                    .filter(diagnosticBinary -> diagnosticBinary.getBit(pos) == 0)
                    .collect(Collectors.toList());
        }

        calculateBitCounts();
    }

    public String findCO2ScrubberRating() {
        int i = 0;
        while (binaries.size() > 1) {
            filterBinariesForCO2ScrubberRating(i);
            ++i;
        }

        return binaries.get(0).getBinary();
    }

    public void filterBinariesForCO2ScrubberRating(int pos) {
        if (countOfOnes.get(pos) < countOfZeros.get(pos)) {
            binaries = binaries.stream()
                    .filter(diagnosticBinary -> diagnosticBinary.getBit(pos) == 1)
                    .collect(Collectors.toList());
        } else {
            binaries = binaries.stream()
                    .filter(diagnosticBinary -> diagnosticBinary.getBit(pos) == 0)
                    .collect(Collectors.toList());
        }

        calculateBitCounts();
    }

    private void calculateBitCounts() {
        for (int i = 0; i < length; ++i) {
            countOfOnes.put(i, 0);
            countOfZeros.put(i, 0);
            for (DiagnosticBinary binary : binaries) {
                int bit = binary.getBit(i);
                if (bit == 0) {
                    countOfZeros.put(i, countOfZeros.get(i) + 1);
                } else {
                    countOfOnes.put(i, countOfOnes.get(i) + 1);
                }
            }
        }
    }
}
