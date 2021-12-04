import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BingoBoardTest {

    @Test
    void addLine() {

        //arrange
        BingoBoard bingoBoard = new BingoBoard();
        List<Integer> row1 = Arrays.asList(22, 13, 17, 11, 0);
        List<Integer> row2 = Arrays.asList(8, 2, 23, 4, 24);

        //act
        bingoBoard.addLine("22 13 17 11  0");
        bingoBoard.addLine(" 8  2 23  4 24");

        //assert
        assertArrayEquals(row1.toArray(), bingoBoard.getBoard().get(0).toArray());
        assertArrayEquals(row2.toArray(), bingoBoard.getBoard().get(1).toArray());
    }

    @Test
    void isBingo() {
        //arrange
        List<List<Integer>> board = Arrays.asList(
                Arrays.asList(22, 13, 17, 11, 0),
                Arrays.asList(8, 2, 23, 4, 24),
                Arrays.asList(21, 9, 14, 16, 7),
                Arrays.asList(6, 10, 3, 18, 5),
                Arrays.asList(1, 12, 20, 15, 19));

        BingoBoard bingoBoard = new BingoBoard(board);

        //act
        boolean result = bingoBoard.isBingo(7);

        //assert
        assertFalse(result);
    }

    @Test
    void isBingo2() {

        //arrange
        List<List<Integer>> board = Arrays.asList(
                Arrays.asList(14, 21, 17, 24, 4),
                Arrays.asList(10, 16, 15, 9, 19),
                Arrays.asList(18, 8, 23, 26, 20),
                Arrays.asList(22, 11, 13, 6, 5),
                Arrays.asList(2, 0, 12, 3, 7));

        List<Integer> numbers = Arrays.asList(7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21, 24, 10, 16, 13, 6, 15, 25, 12, 22, 18, 20, 8, 19, 3, 26, 1);

        BingoBoard bingoBoard = new BingoBoard(board);

        //act
        for (int number : numbers) {
            boolean result = bingoBoard.isBingo(number);

            if (number == 24) {
                assertTrue(result, String.format("Number: %s", number));
                break;
            } else {
                assertFalse(result, String.format("Number: %s", number));
            }
        }

    }

    @Test
    void writeHit() {

        //arrange
        List<List<Integer>> board = Arrays.asList(
                Arrays.asList(22, 13, 17, 11, 0),
                Arrays.asList(8, 2, 23, 4, 24),
                Arrays.asList(21, 9, 14, 16, 7),
                Arrays.asList(6, 10, 3, 18, 5),
                Arrays.asList(1, 12, 20, 15, 19));

        BingoBoard bingoBoard = new BingoBoard(board);

        //act
        Map<String, Integer> result = bingoBoard.writeHit(7);

        //assert
        assertEquals(2, result.get("y"));
        assertEquals(4, result.get("x"));
        assertEquals(1, bingoBoard.getHits()[2][4]);

    }

    @Test
    void getSumOfAllUnmarkedNumbers() {

        //arrange
        List<List<Integer>> board = Arrays.asList(
                Arrays.asList(14, 21, 17, 24, 4),
                Arrays.asList(10, 16, 15, 9, 19),
                Arrays.asList(18, 8, 23, 26, 20),
                Arrays.asList(22, 11, 13, 6, 5),
                Arrays.asList(2, 0, 12, 3, 7));

        List<Integer> numbers = Arrays.asList(7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21, 24, 10, 16, 13, 6, 15, 25, 12, 22, 18, 20, 8, 19, 3, 26, 1);

        BingoBoard bingoBoard = new BingoBoard(board);

        for (int number : numbers) {
            boolean result = bingoBoard.isBingo(number);

            if (number == 24) {
                assertTrue(result, String.format("Number: %s", number));
                break;
            } else {
                assertFalse(result, String.format("Number: %s", number));
            }
        }

        //act
        int result = bingoBoard.getSumOfAllUnmarkedNumbers();

        assertEquals(188, result);
    }
}