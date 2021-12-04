import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GiantSquidTest {

    @Test
    void readInputValuesToList() {

        //arrange
        List<Integer> numbers = Arrays.asList(7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21, 24, 10, 16, 13, 6, 15, 25, 12, 22, 18, 20, 8, 19, 3, 26, 1);

        //act
        List<Integer> result = GiantSquid.readInputValuesToList("test_input.data");

        //assert
        assertArrayEquals(numbers.toArray(), result.toArray());
    }

    @Test
    void readInputValuesToBingoBoard() {

        //act
        List<BingoBoard> result = GiantSquid.readInputValuesToBingoBoard("test_input.data");

        //assert
        assertEquals(3, result.size());
    }

    @Test
    void playBingo() {

        //act
        int result = GiantSquid.playBingo("test_input.data");

        //assert
        assertEquals(4512, result);
    }

    @Test
    void playBingoBad() {
        //act
        int result = GiantSquid.playBingoBad("test_input.data");

        //assert
        assertEquals(1924, result);
    }
}