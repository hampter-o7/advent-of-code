package year2025.day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        int pointerArrow = 50;
        int counter = 0;
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            pointerArrow = (pointerArrow + (line.startsWith("L") ? -1 : 1) * Integer.parseInt(line.substring(1))) % 100;
            if (pointerArrow < 0) {
                pointerArrow += 100;
            }
            if (pointerArrow == 0) {
                counter++;
            }
        }
        myReader.close();
        System.out.println(counter);
    }
}