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
