package year2025.day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part2 {

    public Part2() throws FileNotFoundException {
        int pointerArrow = 50;
        int counter = 0;
        boolean wasZero = false;
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            pointerArrow = pointerArrow + (line.startsWith("L") ? -1 : 1) * Integer.parseInt(line.substring(1));
            boolean countedAlready = false;
            if (pointerArrow > 99) {
                countedAlready = true;
                counter += pointerArrow / 100;
                pointerArrow %= 100;
            }
            if (pointerArrow < 0) {
                countedAlready = true;
                counter += -pointerArrow / 100 + (wasZero ? 0 : 1);
                pointerArrow = ((-pointerArrow / 100 + 1) * 100 + pointerArrow) % 100;
            }
            if (pointerArrow == 0) {
                wasZero = true;
                if (!countedAlready) {
                    counter++;
                }
            } else {
                wasZero = false;
            }
        }
        myReader.close();
        if (pointerArrow == 0) {
            counter++;
        }
        System.out.println(counter);
    }
}