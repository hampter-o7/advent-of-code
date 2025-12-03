package year2025.day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        int counter = 0;
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            counter += findMax(myReader.nextLine());
        }
        myReader.close();
        System.out.println(counter);
    }

    private static int findMax(String batteries) {
        int maxFirstBattery = 0;
        int maxFirstBatteryIndex = -1;
        for (int i = 0; i < batteries.length() - 1; i++) {
            if (batteries.charAt(i) - '0' > maxFirstBattery) {
                maxFirstBattery = batteries.charAt(i) - '0';
                maxFirstBatteryIndex = i;
            }
        }
        int maxLastBattery = 0;
        for (int i = maxFirstBatteryIndex + 1; i < batteries.length(); i++) {
            if (batteries.charAt(i) - '0' > maxLastBattery) {
                maxLastBattery = batteries.charAt(i) - '0';
            }
        }
        return maxFirstBattery * 10 + maxLastBattery;
    }
}