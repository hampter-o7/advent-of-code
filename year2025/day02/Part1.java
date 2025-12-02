package year2025.day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        System.out.println(addAllInvalidIDs(myReader.nextLine()));
        myReader.close();
    }

    private static long addAllInvalidIDs(String stringIDs) {
        long count = 0;
        for (String IDRange : stringIDs.split(",")) {
            String[] startStopIDRange = IDRange.split("-");
            for (long i = Long.parseLong(startStopIDRange[0]); i <= Long.parseLong(startStopIDRange[1]); i++) {
                count += checkIfIDIsValid(i + "");
            }
        }
        return count;
    }

    private static long checkIfIDIsValid(String ID) {
        if (ID.length() % 2 == 1) {
            return 0;
        }
        if (ID.substring(0, ID.length() / 2).equals(ID.substring(ID.length() / 2))) {
            return Long.parseLong(ID);
        }
        return 0;
    }
}