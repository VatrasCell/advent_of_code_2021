import java.util.List;

public class HydrothermalField {

    private final int[][] field;
    private final List<Vent> vents;
    private int dangerousAreasCount = 0;

    public HydrothermalField(List<Vent> vents, int size) {
        this.field = new int[size][size];
        this.vents = vents;
    }

    public void mapVentsOnField(boolean withDiagonals) {
        for (Vent vent : vents) {
            if (withDiagonals || !vent.isDiagonal()) {
                for (Point point : vent.getPoints()) {
                    if (field[point.getY()][point.getX()] == 1) {
                        ++dangerousAreasCount;
                    }
                    ++field[point.getY()][point.getX()];

                }
            }
        }
    }

    public int getDangerousAreasCount() {
        return dangerousAreasCount;
    }
}
