package year2025.day07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import helperFunctions.HelperFunctions;

public class Part1 {

    public Part1() throws FileNotFoundException {
        ArrayList<String> input = new ArrayList<>();
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            input.add(myReader.nextLine());
        }
        myReader.close();
        System.out.println(
                calculateNumberOfSplits(input.get(0).indexOf("S"), HelperFunctions.changeArrayListToCharArray(input)));
    }

    private static int calculateNumberOfSplits(int start, char[][] tachyonManifold) {
        ArrayList<Integer> splitBeams = new ArrayList<>();
        splitBeams.add(start);
        int counter = 0;
        for (int i = 1; i < tachyonManifold.length; i++) {
            for (int j = splitBeams.size() - 1; j >= 0; j--) {
                int beam = splitBeams.get(j);
                if (tachyonManifold[i][beam] == '^') {
                    counter++;
                    splitBeams.remove(j);
                    if (!splitBeams.contains(beam + 1)) {
                        splitBeams.add(beam + 1);
                    }
                    if (!splitBeams.contains(beam - 1)) {
                        splitBeams.add(beam - 1);
                    }
                }
            }
        }
        return counter;
    }
}