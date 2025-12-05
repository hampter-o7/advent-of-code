package year2025.day05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part2 {

    public Part2() throws FileNotFoundException {
        ArrayList<Long[]> ranges = new ArrayList<>();
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            if (line.isBlank()) {
                break;
            }
            addNewRange(ranges, line);
        }
        myReader.close();
        Long allFreshIDs = 0L;
        for (Long[] range : ranges) {
            allFreshIDs += range[1] - range[0] + 1;
        }
        System.out.println(allFreshIDs);
    }

    private static void addNewRange(ArrayList<Long[]> ranges, String line) {
        Long min = Long.parseLong(line.split("-")[0]);
        Long max = Long.parseLong(line.split("-")[1]);
        boolean isConnected = false;
        for (Long[] range : ranges) {
            if (range[0] <= min && range[1] >= min) {
                isConnected = true;
                range[1] = Math.max(max, range[1]);
                break;
            }
            if (range[0] <= max && range[1] >= max) {
                isConnected = true;
                range[0] = Math.min(min, range[0]);
                break;
            }
        }
        if (!isConnected) {
            ranges.add(new Long[] { min, max });
        } else {
            while (connectOtherRanges(ranges)) {
            }
        }
    }

    private static boolean connectOtherRanges(ArrayList<Long[]> ranges) {
        for (Long[] range1 : ranges) {
            for (Long[] range2 : ranges) {
                if (range1 == range2) {
                    continue;
                }
                if (range1[0] <= range2[0] && range1[1] >= range2[0]) {
                    range1[1] = Math.max(range2[1], range1[1]);
                    ranges.remove(range2);
                    return true;
                }
                if (range1[0] <= range2[1] && range1[1] >= range2[1]) {
                    range1[0] = Math.min(range2[0], range1[0]);
                    ranges.remove(range2);
                    return true;
                }
            }
        }
        return false;
    }
}