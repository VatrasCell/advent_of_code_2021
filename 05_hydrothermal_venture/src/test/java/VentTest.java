import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class VentTest {

    @Test
    void getPoints() {

        //arrange
        Vent vent = new Vent("692,826 -> 692,915");

        //act
        List<Point> result = vent.getPoints();

        assertEquals(90, result.size());
    }

    @Test
    void getPointsVerticalReverseSmall() {

        //arrange
        Vent vent = new Vent("2,2 -> 2,1");
        List<Point> points = Arrays.asList(new Point(2, 2), new Point(2, 1));

        //act
        List<Point> result = vent.getPoints();

        assertArrayEquals(points.toArray(), result.toArray());
    }

    @Test
    void getPointsVertical() {

        //arrange
        Vent vent = new Vent("1,1 -> 1,3");
        List<Point> points = Arrays.asList(new Point(1, 1), new Point(1, 2), new Point(1, 3));

        //act
        List<Point> result = vent.getPoints();

        assertArrayEquals(points.toArray(), result.toArray());
    }

    @Test
    void getPointsVertical2() {

        //arrange
        Vent vent = new Vent("1,1 -> 1,7");
        List<Point> points = Arrays.asList(new Point(1, 1), new Point(1, 2), new Point(1, 3),
                new Point(1, 4), new Point(1, 5), new Point(1, 6), new Point(1, 7));

        //act
        List<Point> result = vent.getPoints();

        assertArrayEquals(points.toArray(), result.toArray());
    }

    @Test
    void getPointsVerticalReverse() {

        //arrange
        Vent vent = new Vent("1,3 -> 1,1");
        List<Point> points = Arrays.asList(new Point(1, 3), new Point(1, 2), new Point(1, 1));

        //act
        List<Point> result = vent.getPoints();

        assertArrayEquals(points.toArray(), result.toArray());
    }

    @Test
    void getPointsVerticalReverse2() {

        //arrange
        Vent vent = new Vent("1,7 -> 1,1");
        List<Point> points = Arrays.asList(new Point(1, 7), new Point(1, 6), new Point(1, 5),
                new Point(1, 4), new Point(1, 3), new Point(1, 2), new Point(1, 1));

        //act
        List<Point> result = vent.getPoints();

        assertArrayEquals(points.toArray(), result.toArray());
    }

    @Test
    void getPointsHorizontal() {

        //arrange
        Vent vent = new Vent("7,7 -> 9,7");
        List<Point> points = Arrays.asList(new Point(7, 7), new Point(8, 7), new Point(9, 7));

        //act
        List<Point> result = vent.getPoints();

        assertArrayEquals(points.toArray(), result.toArray());
    }

    @Test
    void getPointsHorizontal2() {

        //arrange
        Vent vent = new Vent("1,7 -> 9,7");
        List<Point> points = Arrays.asList(new Point(1, 7), new Point(2, 7), new Point(3, 7),
                new Point(4, 7), new Point(5, 7), new Point(6, 7), new Point(7, 7),
                new Point(8, 7), new Point(9, 7));

        //act
        List<Point> result = vent.getPoints();

        assertArrayEquals(points.toArray(), result.toArray());
    }

    @Test
    void getPointsHorizontalReverse() {

        //arrange
        Vent vent = new Vent("9,7 -> 7,7");
        List<Point> points = Arrays.asList(new Point(9, 7), new Point(8, 7), new Point(7, 7));

        //act
        List<Point> result = vent.getPoints();

        assertArrayEquals(points.toArray(), result.toArray());
    }

    @Test
    void getPointsHorizontalReverse2() {

        //arrange
        Vent vent = new Vent("9,7 -> 1,7");
        List<Point> points = Arrays.asList(new Point(9, 7), new Point(8, 7), new Point(7, 7),
                new Point(6, 7), new Point(5, 7), new Point(4, 7), new Point(3, 7),
                new Point(2, 7), new Point(1, 7));

        //act
        List<Point> result = vent.getPoints();

        assertArrayEquals(points.toArray(), result.toArray());
    }

    @Test
    void getPointsDiagonal() {

        //arrange
        Vent vent = new Vent("8,0 -> 0,8");
        List<Point> points = Arrays.asList(new Point(8, 0), new Point(7, 1), new Point(6, 2),
                new Point(5, 3), new Point(4, 4), new Point(3, 5), new Point(2, 6),
                new Point(1, 7), new Point(0, 8));

        //act
        List<Point> result = vent.getPoints();

        assertArrayEquals(points.toArray(), result.toArray());
    }
}