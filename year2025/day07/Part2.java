package year2025.day07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import helperFunctions.HelperFunctions;

public class Part2 {

    public Part2() throws FileNotFoundException {
        ArrayList<String> input = new ArrayList<>();
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            input.add(myReader.nextLine());
        }
        myReader.close();
        Map<String, Long> memo = new HashMap<>();
        System.out.println(calculateNumberOfSplits(input.get(0).indexOf("S"), 0,
                HelperFunctions.changeArrayListToCharArray(input), memo));
    }

    private static Long calculateNumberOfSplits(int start, int depth, char[][] tachyonManifold,
            Map<String, Long> memo) {
        if (depth == tachyonManifold.length) {
            return 1L;
        }
        if (memo.containsKey(start + "," + depth)) {
            return memo.get(start + "," + depth);
        }
        if (tachyonManifold[depth][start] != '^') {
            return calculateNumberOfSplits(start, depth + 2, tachyonManifold, memo);
        }
        Long sum = 0L;
        sum += calculateNumberOfSplits(start - 1, depth + 2, tachyonManifold, memo);
        sum += calculateNumberOfSplits(start + 1, depth + 2, tachyonManifold, memo);
        memo.put(start + "," + depth, sum);
        return sum;
    }
}