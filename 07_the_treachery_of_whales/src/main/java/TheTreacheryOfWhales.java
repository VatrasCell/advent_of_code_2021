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

        //second part
        System.out.println(calculateFuelNeedBiggerCosts(readInputValuesToList("input.data")));
    }

    public static int calculateFuelNeed(List<Integer> integers) {
        Collections.sort(integers);
        int fuel = 0;
        int median = getMedian(integers);

        for (Integer integer : integers) {
            fuel += Math.abs(integer - median);
        }

        return fuel;
    }

    public static int calculateFuelNeedBiggerCosts(List<Integer> integers) {
        Collections.sort(integers);
        List<Integer> fuels = new ArrayList<>();
        int lastElement = integers.get(integers.size() - 1);
        for (int i = 0; i < lastElement; ++i) {
            int fuel = 0;
            for (Integer integer : integers) {
                int x = Math.abs(integer - i);
                fuel += (Math.pow(x, 2) / 2) + ((double) x / 2);
            }
            fuels.add(fuel);
        }

        Collections.sort(fuels);
        return fuels.get(0);
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
