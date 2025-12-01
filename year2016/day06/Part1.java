package year2016.day06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        String firstLine = myReader.nextLine();
        char[][] allCharsCounter = new char[firstLine.length()][26];
        countAllLetters(allCharsCounter, firstLine);
        while (myReader.hasNextLine()) {
            countAllLetters(allCharsCounter, myReader.nextLine());
        }
        myReader.close();
        System.out.println(getMostFrequentLetters(allCharsCounter));
    }

    private static void countAllLetters(char[][] allCharsCounter, String line) {
        for (int i = 0; i < line.length(); i++) {
            allCharsCounter[i][line.charAt(i) - 'a']++;
        }
    }

    private static String getMostFrequentLetters(char[][] allCharsCounter) {
        String output = "";
        for (int i = 0; i < allCharsCounter.length; i++) {
            int maxFreq = 0;
            int maxFreqIndex = -1;
            for (int j = 0; j < allCharsCounter[0].length; j++) {
                if (allCharsCounter[i][j] > maxFreq) {
                    maxFreq = allCharsCounter[i][j];
                    maxFreqIndex = j;
                }
            }
            output += (char) ('a' + maxFreqIndex);
        }
        return output;
    }
}