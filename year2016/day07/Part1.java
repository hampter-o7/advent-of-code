package year2016.day07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        int counter = 0;
        while (myReader.hasNextLine()) {
            if (isTLSSupported(myReader.nextLine())) {
                counter++;
            }
        }
        myReader.close();
        System.out.println(counter);
    }

    private static boolean isTLSSupported(String string) {
        boolean hasABBA = false;
        boolean isInMiddle = false;
        for (int i = 0; i < string.length() - 3; i++) {
            String subString = string.substring(i, i + 4);
            if (subString.contains("[")) {
                i += 3;
                isInMiddle = true;
                continue;
            } else if (subString.contains("]")) {
                i += 3;
                isInMiddle = false;
                continue;
            }
            if (containsABBA(subString)) {
                if (isInMiddle) {
                    return false;
                }
                hasABBA = true;
            }
        }
        return hasABBA;
    }

    private static boolean containsABBA(String string) {
        return string.charAt(0) == string.charAt(3) &&
                string.charAt(1) == string.charAt(2) &&
                string.charAt(0) != string.charAt(1);
    }
}