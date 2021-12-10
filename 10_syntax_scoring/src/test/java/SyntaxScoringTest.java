import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SyntaxScoringTest {

    @Test
    void calcSyntaxScore() {

        //arrange
        List<String> lines = SyntaxScoring.readInputValuesToList("test_input.data");
        assertNotNull(lines);

        //act
        int result = SyntaxScoring.calcSyntaxScore(lines);

        //assert
        assertEquals(26397, result);
    }

    @Test
    void calcSyntaxScoreForAutoComplete() {

        //arrange
        List<String> lines = SyntaxScoring.readInputValuesToList("test_input.data");
        assertNotNull(lines);

        //act
        long result = SyntaxScoring.calcSyntaxScoreForAutoComplete(lines);

        //assert
        assertEquals(288957, result);
    }
}