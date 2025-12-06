package year2025.day06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        ArrayList<String[]> mathProblems = new ArrayList<>();
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            mathProblems.add(myReader.nextLine().trim().split("\\s+"));
        }
        myReader.close();
        System.out.println(solveMathProblems(mathProblems));
    }

    private static Long solveMathProblems(ArrayList<String[]> mathProblems) {
        long sum = 0;
        for (int i = 0; i < mathProblems.get(0).length; i++) {
            boolean amIMultiplying = mathProblems.getLast()[i].equals("*");
            long oneProblemSum = amIMultiplying ? 1 : 0;
            for (int j = 0; j < mathProblems.size() - 1; j++) {
                if (amIMultiplying) {
                    oneProblemSum *= Integer.parseInt(mathProblems.get(j)[i]);
                } else {
                    oneProblemSum += Integer.parseInt(mathProblems.get(j)[i]);
                }
            }
            sum += oneProblemSum;
        }
        return sum;
    }
}