package year2025.day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part2 {

    public Part2() throws FileNotFoundException {
        long counter = 0;
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            counter += findMax(myReader.nextLine());
        }
        myReader.close();
        System.out.println(counter);
    }

    private static long findMax(String batteries) {
        long counter = 0;
        int lastBatteryIndex = -1;
        for (int i = 0; i < 12; i++) {
            int maxBattery = 0;
            int maxBatteryIndex = -1;
            for (int j = lastBatteryIndex + 1; j < batteries.length() - 11 + i; j++) {
                if (batteries.charAt(j) - '0' > maxBattery) {
                    maxBattery = batteries.charAt(j) - '0';
                    maxBatteryIndex = j;
                }
            }
            counter = counter * 10 + maxBattery;
            lastBatteryIndex = maxBatteryIndex;
        }
        return counter;
    }
}