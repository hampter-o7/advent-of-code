package year2016.day08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import helperFunctions.HelperFunctions;

public class Part2 {

    public Part2() throws FileNotFoundException {
        boolean[][] littleScreen = new boolean[6][50];
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            handleOperation(littleScreen, myReader.nextLine());
        }
        myReader.close();
        HelperFunctions.printBoolArray(littleScreen);
    }

    private static void handleOperation(boolean[][] littleScreen, String operation) {
        if (operation.startsWith("rect")) {
            String[] size = operation.substring(5).split("x");
            for (int i = 0; i < Integer.parseInt(size[1]); i++) {
                for (int j = 0; j < Integer.parseInt(size[0]); j++) {
                    littleScreen[i][j] = true;
                }
            }
        } else if (operation.startsWith("rotate column x=")) {
            String[] column = operation.substring(16).split(" by ");
            int index = Integer.parseInt(column[0]);
            int shift = Integer.parseInt(column[1]);
            boolean[] oldColumn = new boolean[littleScreen.length];
            for (int i = 0; i < littleScreen.length; i++) {
                oldColumn[(i + shift) % oldColumn.length] = littleScreen[(i + shift) % oldColumn.length][index];
                if (shift > i) {
                    littleScreen[(i + shift) % oldColumn.length][index] = littleScreen[i][index];
                } else {
                    littleScreen[(i + shift) % oldColumn.length][index] = oldColumn[i];
                }
            }
        } else if (operation.startsWith("rotate row y=")) {
            String[] row = operation.substring(13).split(" by ");
            int index = Integer.parseInt(row[0]);
            int shift = Integer.parseInt(row[1]);
            boolean[] oldRow = new boolean[littleScreen[0].length];
            for (int i = 0; i < littleScreen[0].length; i++) {
                oldRow[(i + shift) % oldRow.length] = littleScreen[index][(i + shift) % oldRow.length];
                if (shift > i) {
                    littleScreen[index][(i + shift) % oldRow.length] = littleScreen[index][i];
                } else {
                    littleScreen[index][(i + shift) % oldRow.length] = oldRow[i];
                }
            }
        }
    }
}