import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GiantSquid {

    public static void main(String[] args) {

        //first step
        System.out.println(playBingo("input.data"));

        //second step
        System.out.println(playBingoBad("input.data"));
    }

    public static int playBingo(String path) {
        List<Integer> numbers = readInputValuesToList(path);
        List<BingoBoard> bingoBoards = readInputValuesToBingoBoardList(path);

        for (int number : numbers) {
            for (BingoBoard bingoBoard : bingoBoards) {
                if (bingoBoard.isBingo(number)) {
                    return bingoBoard.getSumOfAllUnmarkedNumbers() * number;
                }
            }
        }

        return -1;
    }

    public static int playBingoBad(String path) {
        List<Integer> numbers = readInputValuesToList(path);
        List<BingoBoard> bingoBoards = readInputValuesToBingoBoardList(path);
        int winCount = 0;

        for (int number : numbers) {
            for (BingoBoard bingoBoard : bingoBoards) {
                if (!bingoBoard.isBingo()) {
                    if (bingoBoard.isBingo(number)) {
                        bingoBoard.setBingo(true);
                        ++winCount;
                        if (winCount == bingoBoards.size()) {
                            return bingoBoard.getSumOfAllUnmarkedNumbers() * number;
                        }
                    }
                }

            }
        }

        return -1;
    }

    public static List<Integer> readInputValuesToList(String path) {
        List<Integer> numbers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(getURL(path).getFile()))) {
            numbers = Stream.of(reader.readLine().split(",")).map(Integer::parseInt).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return numbers;
    }

    public static List<BingoBoard> readInputValuesToBingoBoardList(String path) {
        List<BingoBoard> bingoBoards = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(getURL(path).getFile()))) {
            reader.readLine();
            reader.readLine();
            String line;
            BingoBoard bingoBoard = new BingoBoard();
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty() || line.isBlank()) {
                    bingoBoards.add(bingoBoard);
                    bingoBoard = new BingoBoard();
                } else {
                    bingoBoard.addLine(line);
                }
            }
            bingoBoards.add(bingoBoard);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bingoBoards;
    }

    public static URL getURL(String path) {
        return Objects.requireNonNull(GiantSquid.class.getClassLoader().getResource(path));
    }
}
