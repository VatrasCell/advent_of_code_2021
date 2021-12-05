import java.util.ArrayList;
import java.util.List;

public class Vent {

    private final Point startPoint;
    private final Point endPoint;

    public Vent(String ventLine) {
        String[] points = ventLine.split(" -> ");
        String[] startPoints = points[0].split(",");
        String[] endPoints = points[1].split(",");

        this.startPoint = new Point(Integer.parseInt(startPoints[0]), Integer.parseInt(startPoints[1]));
        this.endPoint = new Point(Integer.parseInt(endPoints[0]), Integer.parseInt(endPoints[1]));
    }

    public List<Point> getPoints() {
        List<Point> points = new ArrayList<>();
        points.add(startPoint);

        int deltaX = endPoint.getX() - startPoint.getX();
        int deltaY = endPoint.getY() - startPoint.getY();

        int count;
        if (deltaX != 0) {
            count = Math.abs(deltaX) - 1;
        } else {
            count = Math.abs(deltaY) - 1;
        }

        int x = startPoint.getX();
        int y = startPoint.getY();

        for (int i = 0; i < count; ++i) {
            points.add(new Point(
                    deltaX == 0 ? x : deltaX < 0 ? --x : ++x,
                    deltaY == 0 ? y : deltaY < 0 ? --y : ++y)
            );
        }

        points.add(endPoint);
        return points;
    }

    public boolean isDiagonal() {
        int deltaX = endPoint.getX() - startPoint.getX();
        int deltaY = endPoint.getY() - startPoint.getY();

        return deltaX != 0 && deltaY != 0;
    }
}
