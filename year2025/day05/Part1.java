package year2025.day05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        boolean isIngredient = false;
        ArrayList<Long[]> ranges = new ArrayList<>();
        int counter = 0;
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            if (line.isBlank()) {
                isIngredient = true;
                continue;
            }
            if (!isIngredient) {
                addNewRange(ranges, line);
            } else {
                counter += checkIfFresh(ranges, line) ? 1 : 0;
            }
        }
        myReader.close();
        System.out.println(counter);
    }

    private static void addNewRange(ArrayList<Long[]> ranges, String line) {
        Long min = Long.parseLong(line.split("-")[0]);
        Long max = Long.parseLong(line.split("-")[1]);
        for (Long[] range : ranges) {
            if (range[0] <= min && range[1] >= min) {
                range[1] = Math.max(max, range[1]);
                return;
            }
            if (range[0] <= max && range[1] >= max) {
                range[0] = Math.min(min, range[0]);
                return;
            }
        }
        ranges.add(new Long[] { min, max });
    }

    private static boolean checkIfFresh(ArrayList<Long[]> ranges, String line) {
        Long ingredient = Long.parseLong(line);
        for (Long[] range : ranges) {
            if (range[0] <= ingredient && range[1] >= ingredient) {
                return true;
            }
        }
        return false;
    }
}