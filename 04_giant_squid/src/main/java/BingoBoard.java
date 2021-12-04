import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BingoBoard {

    private final int[][] hits = new int[5][5];
    private List<List<Integer>> board = new ArrayList<>();

    public BingoBoard(List<List<Integer>> board) {
        this.board = board;
    }

    public BingoBoard() {
    }

    public List<List<Integer>> getBoard() {
        return board;
    }

    public int[][] getHits() {
        return hits;
    }

    public void addLine(String line) {
        board.add(Arrays.stream(
                        line.trim()
                                .replaceAll(" +", " ")
                                .split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList())
        );
    }

    public boolean isBingo(int number) {
        Map<String, Integer> hit = writeHit(number);
        return checkHits(hit);
    }

    public Map<String, Integer> writeHit(int number) {
        Map<String, Integer> hit = new HashMap<>();
        for (int i = 0; i < board.size(); ++i) {
            List<Integer> row = board.get(i);
            if (row.contains(number)) {
                hits[i][row.indexOf(number)] = 1;
                hit.put("x", row.indexOf(number));
                hit.put("y", i);
            }
        }

        return hit;
    }

    public int getSumOfAllUnmarkedNumbers() {
        int sum = 0;
        for (int y = 0; y < 5; ++y) {
            for (int x = 0; x < 5; ++x) {
                if (hits[y][x] == 0) {
                    sum += board.get(y).get(x);
                }
            }
        }

        return sum;
    }

    private boolean checkHits(Map<String, Integer> hit) {
        if (hit.isEmpty()) {
            return false;
        }

        return checkHorizontals(hit.get("x")) || checkVerticals(hit.get("y"));
    }

    private boolean checkHorizontals(int x) {
        for (int i = 0; i < 5; ++i) {
            if (hits[i][x] == 0) {
                return false;
            }
        }

        return true;
    }

    private boolean checkVerticals(int y) {
        for (int i = 0; i < 5; ++i) {
            if (hits[y][i] == 0) {
                return false;
            }
        }

        return true;
    }
}
