package year2025.day06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part2 {

    public Part2() throws FileNotFoundException {
        ArrayList<String> mathProblems = new ArrayList<>();
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            mathProblems.add(myReader.nextLine());
        }
        myReader.close();
        System.out.println(solveMathProblems(mathProblems));
    }

    private static Long solveMathProblems(ArrayList<String> mathProblems) {
        long sum = 0;
        for (int i = 0; i < mathProblems.get(0).length(); i++) {
            boolean amIMultiplying = mathProblems.getLast().charAt(i) == '*';
            long oneProblemSum = amIMultiplying ? 1 : 0;
            while (true) {
                long number = 0;
                for (int j = 0; j < mathProblems.size() - 1; j++) {
                    if (mathProblems.get(j).charAt(i) == ' ') {
                        continue;
                    }
                    number = number * 10 + mathProblems.get(j).charAt(i) - '0';
                }
                if (number == 0) {
                    sum += oneProblemSum;
                    break;
                }
                if (amIMultiplying) {
                    oneProblemSum *= number;
                } else {
                    oneProblemSum += number;
                }
                i++;
                if (i == mathProblems.get(0).length()) {
                    sum += oneProblemSum;
                    break;
                }
            }
        }
        return sum;
    }
}