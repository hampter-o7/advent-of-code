package year2016.day09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

    public Part2() throws FileNotFoundException {
        long start = System.nanoTime();
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        System.out.println(decompressFile(myReader.nextLine()));
        myReader.close();
        long end = System.nanoTime();
        long duration = end - start;

        System.out.println("Execution time: " + (duration / 1_000_000) + " ms");
    }

    private static long decompressFile(String string) {
        long counter = 0;
        StringBuilder stringBuilder = new StringBuilder(string);
        Pattern pattern = Pattern.compile("\\([0-9]+x[0-9]+\\)");
        Matcher matcher = pattern.matcher(stringBuilder);
        while (matcher.find()) {
            String[] compressionMarker = stringBuilder.substring(matcher.start() + 1, matcher.end() - 1).split("x");
            int range = Integer.parseInt(compressionMarker[0]);
            int repeats = Integer.parseInt(compressionMarker[1]);
            stringBuilder.replace(matcher.start(), matcher.end(),
                    stringBuilder.substring(matcher.end(), matcher.end() + range).repeat(repeats - 1));
            if (matcher.start() > 0) {
                counter += matcher.start();
                stringBuilder.replace(0, matcher.start(), "");
            }
            matcher = pattern.matcher(stringBuilder);
        }
        return counter + stringBuilder.length();
    }
}