package year2025.day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part2 {

    public Part2() throws FileNotFoundException {
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
        if (ID.length() < 2) {
            return 0;
        }
        for (int i = 1; i <= ID.length() / 2; i++) {
            if (ID.length() % i == 1) {
                continue;
            }
            if (ID.equals(ID.substring(0, i).repeat(ID.length() / i))) {
                return Long.parseLong(ID);
            }
        }
        return 0;
    }
}