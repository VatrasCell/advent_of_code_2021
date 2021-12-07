import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TheTreacheryOfWhales {

    public static void main(String[] args) {
        //first part
        System.out.println(calculateFuelNeed(readInputValuesToList("input.data")));
    }

    public static int calculateFuelNeed(List<Integer> integers) {
        Collections.sort(integers);
        int lastElement = integers.get(integers.size() - 1) + 1;
        int[] counts = new int[lastElement];
        int fuel = 0;
        int median = getMedian(integers);

        for (Integer integer : integers) {
            ++counts[integer];
        }

        for (int i = 0; i < counts.length; ++i) {
            fuel += counts[i] * Math.abs(median - i);
        }

        return fuel;
    }

    public static int getMedian(List<Integer> sortedIntegers) {
        double median;
        double halfSize = (double) sortedIntegers.size() / 2;
        if (sortedIntegers.size() % 2 == 0) {
            median = ((double) sortedIntegers.get((int) halfSize) + (double) sortedIntegers.get((int) halfSize - 1)) / 2;
        } else {
            median = (double) sortedIntegers.get((int) halfSize);
        }
        return (int) median;
    }

    public static List<Integer> readInputValuesToList(String path) {
        List<Integer> list = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(getURL(path).getFile()))) {
            list = Arrays.stream(reader.readLine().split(",")).map(Integer::parseInt).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static URL getURL(String path) {
        return Objects.requireNonNull(TheTreacheryOfWhales.class.getClassLoader().getResource(path));
    }
}
