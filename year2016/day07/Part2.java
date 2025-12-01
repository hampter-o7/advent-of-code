package year2016.day07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part2 {

    public Part2() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        int counter = 0;
        while (myReader.hasNextLine()) {
            if (isSSLSupported(myReader.nextLine())) {
                counter++;
            }
        }
        myReader.close();
        System.out.println(counter);
    }

    private static boolean isSSLSupported(String string) {
        ArrayList<String> outside = new ArrayList<>();
        ArrayList<String> inside = new ArrayList<>();
        boolean isInMiddle = false;
        for (int i = 0; i < string.length() - 2; i++) {
            String subString = string.substring(i, i + 3);
            if (subString.contains("[")) {
                i += 2;
                isInMiddle = true;
                continue;
            } else if (subString.contains("]")) {
                i += 2;
                isInMiddle = false;
                continue;
            }
            if (containsABA(subString)) {
                if (isInMiddle) {
                    inside.add(subString.substring(1));
                } else {
                    outside.add(subString.substring(0, 2));
                }
            }
        }
        for (String outsideString : outside) {
            for (String insideString : inside) {
                if (outsideString.equals(insideString)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean containsABA(String string) {
        return string.charAt(0) == string.charAt(2) && string.charAt(0) != string.charAt(1);
    }
}