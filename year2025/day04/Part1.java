package year2025.day04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import helperFunctions.HelperFunctions;

public class Part1 {

    public Part1() throws FileNotFoundException {
        ArrayList<String> TPString = new ArrayList<>();
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            TPString.add(myReader.nextLine());
        }
        myReader.close();
        boolean[][] TP = HelperFunctions.changeArrayListToBoolArray(TPString, '@');
        System.out.println(checkTPAccess(TP));
    }

    private static int checkTPAccess(boolean[][] TP) {
        int counter = 0;
        for (int i = 0; i < TP.length; i++) {
            for (int j = 0; j < TP[i].length; j++) {
                if (TP[i][j]) {
                    counter += isRollAccessible(TP, i, j) ? 1 : 0;
                }
            }
        }
        return counter;
    }

    private static boolean isRollAccessible(boolean[][] TP, int x, int y) {
        int counter = 0;
        for (int i = Math.max(x - 1, 0); i <= Math.min(x + 1, TP.length - 1); i++) {
            for (int j = Math.max(y - 1, 0); j <= Math.min(y + 1, TP[0].length - 1); j++) {
                counter += TP[i][j] ? 1 : 0;
            }
        }
        return counter < 5;
    }
}