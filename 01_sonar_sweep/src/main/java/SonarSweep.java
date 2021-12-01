import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SonarSweep {

    public static void main(String[] args) {

        List<Integer> measurements = readInputValuesToList("input.data");

        //first part
        System.out.println(measureIncrement(measurements));

        //second part
        System.out.println(measureIncrement(getThreeMeasurementSlidingWindows(measurements)));
    }

    public static int measureIncrement(List<Integer> measurements) {
        int incrementCount = 0;

        for (int i = 1; i < measurements.size(); ++i) {
            if (measurements.get(i - 1) < measurements.get(i)) {
                ++incrementCount;
            }
        }

        return incrementCount;
    }

    public static List<Integer> getThreeMeasurementSlidingWindows(List<Integer> measurements) {
        List<Integer> windows = new ArrayList<>();
        for (int i = 2; i < measurements.size(); ++i) {
            windows.add(measurements.get(i - 2) + measurements.get(i - 1) + measurements.get(i));
        }

        return windows;
    }

    public static List<Integer> readInputValuesToList(String path) {
        List<Integer> integers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(getURL(path).getFile()))) {
            reader.lines().forEach(s -> integers.add(Integer.valueOf(s)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return integers;
    }

    public static URL getURL(String path) {
        return Objects.requireNonNull(SonarSweep.class.getClassLoader().getResource(path));
    }
}
