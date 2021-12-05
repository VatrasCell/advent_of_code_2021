import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HydrothermalVenture {

    public static void main(String[] args) {

        //first step
        List<Vent> vents = readInputValuesToList("input.data");
        HydrothermalField field = new HydrothermalField(vents, 1000);
        field.mapVentsOnField(false);
        System.out.println(field.getDangerousAreasCount());
    }

    public static List<Vent> readInputValuesToList(String path) {
        List<Vent> vents = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(getURL(path).getFile()))) {
            reader.lines().forEach(s -> vents.add(new Vent(s)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return vents;
    }

    public static URL getURL(String path) {
        return Objects.requireNonNull(HydrothermalVenture.class.getClassLoader().getResource(path));
    }
}
