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

public class SmokeBasin {

    public static void main(String[] args) {

        //first step
        System.out.println(calcResultPartOne(getLowPoints(readInputValuesToList("input.data"))));

        //second step
        List<List<Integer>> field = SmokeBasin.readInputValuesToList("input.data");
        List<Point> lowPointPositions = SmokeBasin.getLowPointPositions(field);
        List<Integer> basinSizes = SmokeBasin.getBasinSizes(field, lowPointPositions);
        System.out.println(SmokeBasin.calcResultPartTwo(basinSizes));
    }

    public static int calcResultPartOne(List<Integer> lowPoints) {
        return lowPoints.stream().reduce(0, Integer::sum) + lowPoints.size();
    }

    public static List<Integer> getLowPoints(List<List<Integer>> field) {
        List<Integer> lowPoints = new ArrayList<>();
        for (int y = 0; y < field.size(); ++y) {
            for (int x = 0; x < field.get(0).size(); ++x) {
                if (isLowestNumber(field.get(y).get(x), getAdjacentNumbers(field, x, y))) {
                    lowPoints.add(field.get(y).get(x));
                }
            }
        }

        return lowPoints;
    }

    public static int calcResultPartTwo(List<Integer> basinSizes) {
        basinSizes = basinSizes.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
        return basinSizes.get(0) * basinSizes.get(1) * basinSizes.get(2);
    }

    public static List<Integer> getBasinSizes(List<List<Integer>> field, List<Point> lowPoints) {
        List<Integer> basinSizes = new ArrayList<>();
        for (Point point : lowPoints) {
            basinSizes.add((int) calcBasinSize(field, point, getAdjacentPoints(field, point.getX(), point.getY())).stream().distinct().count());
        }

        return basinSizes;
    }

    public static List<Point> getLowPointPositions(List<List<Integer>> field) {
        List<Point> lowPoints = new ArrayList<>();
        for (int y = 0; y < field.size(); ++y) {
            for (int x = 0; x < field.get(0).size(); ++x) {
                if (isLowestNumber(field.get(y).get(x), getAdjacentNumbers(field, x, y))) {
                    lowPoints.add(new Point(x, y));
                }
            }
        }

        return lowPoints;
    }

    public static List<Point> calcBasinSize(List<List<Integer>> field, Point point, List<Point> adjacentPoints) {
        List<Point> basinPositions = new ArrayList<>();
        basinPositions.add(point);
        int nextFieldValue = field.get(point.getY()).get(point.getX()) + 1;

        for (Point adjacentPoint : adjacentPoints) {
            if (field.get(adjacentPoint.getY()).get(adjacentPoint.getX()) == nextFieldValue && nextFieldValue < 9) {
                basinPositions.addAll(calcBasinSize(field, adjacentPoint, getAdjacentPoints(field, adjacentPoint.getX(), adjacentPoint.getY())));
            }
        }

        return basinPositions;
    }

    public static boolean isLowestNumber(int number, List<Integer> adjacentNumbers) {
        adjacentNumbers.add(number);
        adjacentNumbers.sort(null);
        return number == adjacentNumbers.get(0) && adjacentNumbers.get(0) < adjacentNumbers.get(1);
    }

    public static List<Integer> getAdjacentNumbers(List<List<Integer>> field, int x, int y) {
        List<Integer> adjacentNumbers = new ArrayList<>();
        try {
            adjacentNumbers.add(field.get(y - 1).get(x));
        } catch (IndexOutOfBoundsException e) {
            //
        }

        try {
            adjacentNumbers.add(field.get(y + 1).get(x));
        } catch (IndexOutOfBoundsException e) {
            //
        }

        try {
            adjacentNumbers.add(field.get(y).get(x - 1));
        } catch (IndexOutOfBoundsException e) {
            //
        }

        try {
            adjacentNumbers.add(field.get(y).get(x + 1));
        } catch (IndexOutOfBoundsException e) {
            //
        }

        return adjacentNumbers;
    }

    public static List<Point> getAdjacentPoints(List<List<Integer>> field, int x, int y) {
        List<Point> adjacentNumbers = new ArrayList<>();
        int value = field.get(y).get(x);
        try {
            if (field.get(y - 1).get(x) > value) {
                adjacentNumbers.add(new Point(x, y - 1));
            }
        } catch (IndexOutOfBoundsException e) {
            //
        }

        try {
            if (field.get(y + 1).get(x) > value) {
                adjacentNumbers.add(new Point(x, y + 1));
            }
        } catch (IndexOutOfBoundsException e) {
            //
        }

        try {
            if (field.get(y).get(x - 1) > value) {
                adjacentNumbers.add(new Point(x - 1, y));
            }
        } catch (IndexOutOfBoundsException e) {
            //
        }

        try {
            if (field.get(y).get(x + 1) > value) {
                adjacentNumbers.add(new Point(x + 1, y));
            }
        } catch (IndexOutOfBoundsException e) {
            //
        }

        return adjacentNumbers;
    }

    public static List<List<Integer>> readInputValuesToList(String path) {
        List<List<Integer>> field = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(getURL(path).getFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                List<Integer> integers = Arrays.stream(String.format("9%s9", line).split(""))
                        .map(Integer::parseInt).collect(Collectors.toList());
                field.add(integers);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.reverse(field);
        field.add(getBoarder(field.get(0).size()));
        Collections.reverse(field);
        field.add(getBoarder(field.get(0).size()));

        return field;
    }

    public static URL getURL(String path) {
        return Objects.requireNonNull(SmokeBasin.class.getClassLoader().getResource(path));
    }

    public static List<Integer> getBoarder(int length) {
        List<Integer> boarder = new ArrayList<>();

        for (int i = 0; i < length; ++i) {
            boarder.add(9);
        }

        return boarder;
    }
}
