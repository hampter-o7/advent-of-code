package year2025.day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        int[] presentsSizes = new int[6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                presentsSizes[i] += myReader.nextLine().chars().filter(ch -> ch == '#').count();
            }
        }
        int counter = 0;
        while (myReader.hasNextLine()) {
            counter += isPossibleToPack(myReader.nextLine(), presentsSizes);
        }
        myReader.close();
        System.out.println(counter);
    }

    private static int isPossibleToPack(String input, int[] presentsSizes) {
        String[] split1 = input.split(":")[0].split("x");
        String[] split2 = input.split(": ")[1].split(" ");
        double size = Integer.parseInt(split1[0]) * Integer.parseInt(split1[1]);
        double presentsSize = 0;
        for (int i = 0; i < split2.length; i++) {
            presentsSize += Integer.parseInt(split2[i]) * presentsSizes[i];
        }
        return size * 0.95 < presentsSize ? 0 : 1;
    }
}