import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Lanternfish {

    public static void main(String[] args) {

        //first step
        System.out.println(processLifeCycle(readInputValuesToList("input.data"), 80).size());

        //second step
        System.out.println(processLifeCycleFast(readInputValuesToList("input.data"), 256));
    }

    public static long processLifeCycleFast(List<Integer> lanternfishs, int days) {
        long[] lanternfishAgeCounts = new long[9];
        for (Integer integer : lanternfishs) {
            ++lanternfishAgeCounts[integer];
        }

        for (int i = 0; i <= days; ++i) {
            long zeros = lanternfishAgeCounts[0];
            shiftLeft(lanternfishAgeCounts);
            lanternfishAgeCounts[6] += zeros;
            lanternfishAgeCounts[8] += zeros;
        }

        long result = 0;
        for (int i = 0; i < 8; ++i) {
            result += lanternfishAgeCounts[i];
        }

        return result;
    }

    private static void shiftLeft(long[] lanternfishAgeCounts) {
        for (int i = 1; i <= 8; ++i) {
            lanternfishAgeCounts[i - 1] = lanternfishAgeCounts[i];
        }
        lanternfishAgeCounts[8] = 0;
    }

    public static List<Integer> processLifeCycle(List<Integer> lanternfishs, int days) {
        for (int i = 0; i < days; ++i) {
            for (int j = 0; j < lanternfishs.size(); ++j) {
                if (lanternfishs.get(j) > 0) {
                    lanternfishs.set(j, lanternfishs.get(j) - 1);
                } else {
                    lanternfishs.set(j, 6);
                    lanternfishs.add(9);
                }
            }
        }
        return lanternfishs;
    }

    public static List<Integer> readInputValuesToList(String path) {
        List<Integer> lanternfishs = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(getURL(path).getFile()))) {
            lanternfishs = Arrays.stream(reader.readLine().split(",")).map(Integer::parseInt).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lanternfishs;
    }

    public static URL getURL(String path) {
        return Objects.requireNonNull(Lanternfish.class.getClassLoader().getResource(path));
    }
}
