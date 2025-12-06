package year2016.day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part2 {

    public Part2() throws FileNotFoundException {
        ArrayList<String> operations = new ArrayList<>();
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            operations.add(myReader.nextLine());
        }
        myReader.close();
        System.err.println(executeProgram(operations));
    }

    private static int executeProgram(ArrayList<String> operations) {
        int pointer = 0;
        int[] registers = { 0, 0, 1, 0 };
        while (pointer >= 0 && pointer < operations.size()) {
            String[] operation = operations.get(pointer).split(" ");
            if (operation[0].startsWith("cpy")) {
                registers[operation[2].charAt(0) - 'a'] = (operation[1].charAt(0) - 'a' >= 0)
                        ? registers[operation[1].charAt(0) - 'a']
                        : Integer.parseInt(operation[1]);
            } else if (operation[0].startsWith("inc")) {
                registers[operation[1].charAt(0) - 'a']++;
            } else if (operation[0].startsWith("dec")) {
                registers[operation[1].charAt(0) - 'a']--;
            } else if (((operation[1].charAt(0) - 'a' < 0) && Integer.parseInt(operation[1]) != 0)
                    || registers[operation[1].charAt(0) - 'a'] != 0) {
                pointer += Integer.parseInt(operation[2]);
                continue;
            }
            pointer++;
        }
        return registers[0];
    }
}