import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.stream.Collectors;

public class SyntaxScoring {

    public static final List<String> OPENER = Arrays.asList("(", "[", "{", "<");
    public static final List<String> CLOSER = Arrays.asList(")", "]", "}", ">");

    public static void main(String[] args) {
        //first step
        System.out.println(calcSyntaxScore(Objects.requireNonNull(readInputValuesToList("input.data"))));

        //second step
        System.out.println(calcSyntaxScoreForAutoComplete(Objects.requireNonNull(readInputValuesToList("input.data"))));
    }

    public static int calcSyntaxScore(List<String> lines) {
        int score = 0;
        for (String line : lines) {
            score += calcSyntaxScore(line);
        }
        return score;
    }

    public static long calcSyntaxScoreForAutoComplete(List<String> lines) {
        List<Long> scores = new ArrayList<>();
        for (String line : lines) {
            long score = calcSyntaxScoreForAutoComplete(line);
            if (score != 0) {
                scores.add(score);
            }
        }
        Collections.sort(scores);
        return scores.get(scores.size() / 2);
    }

    public static int getScore(String chaR) {
        switch (chaR) {
            case ")":
                return 3;
            case "]":
                return 57;
            case "}":
                return 1197;
            case ">":
                return 25137;
            default:
                return 0;
        }
    }

    public static int getScoreForAutoComplete(String chaR) {
        switch (chaR) {
            case ")":
                return 1;
            case "]":
                return 2;
            case "}":
                return 3;
            case ">":
                return 4;
            default:
                return 0;
        }
    }

    public static long calcSyntaxScoreForAutoComplete(String line) {
        Stack<String> stack = new Stack<>();
        String[] chars = line.split("");
        for (String chaR : chars) {
            if (OPENER.contains(chaR)) {
                stack.push(chaR);
            } else {
                int index = CLOSER.indexOf(chaR);
                if (!stack.pop().equals(OPENER.get(index))) {
                    return 0;
                }
            }
        }

        long score = 0;
        while (!stack.isEmpty()) {
            score *= 5;

            String chaR = stack.pop();
            int index = OPENER.indexOf(chaR);
            score += getScoreForAutoComplete(CLOSER.get(index));
        }

        return score;
    }

    public static int calcSyntaxScore(String line) {
        Stack<String> stack = new Stack<>();
        String[] chars = line.split("");
        for (String chaR : chars) {
            if (OPENER.contains(chaR)) {
                stack.push(chaR);
            } else {
                int index = CLOSER.indexOf(chaR);
                if (!stack.pop().equals(OPENER.get(index))) {
                    return getScore(chaR);
                }
            }
        }

        return 0;
    }

    public static List<String> readInputValuesToList(String path) {

        try (BufferedReader reader = new BufferedReader(new FileReader(getURL(path).getFile()))) {
            return reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static URL getURL(String path) {
        return Objects.requireNonNull(SyntaxScoring.class.getClassLoader().getResource(path));
    }
}
