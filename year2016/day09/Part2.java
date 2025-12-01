package year2016.day09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

    public Part2() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "test2.txt");
        Scanner myReader = new Scanner(inputPath);
        System.out.println(decompressFile2(myReader.nextLine()));
        myReader.close();
    }

    private static long decompressFile2(String string) {
        StringBuilder stringBuilder = new StringBuilder(string);
        Pattern pattern = Pattern.compile("\\([0-9]+x[0-9]+\\)");
        Matcher matcher = pattern.matcher(stringBuilder);
        while (matcher.find()) {
            String[] compressionString = stringBuilder.substring(matcher.start() + 1, matcher.end() - 1).split("x");
            int range = Integer.parseInt(compressionString[0]);
            int repeats = Integer.parseInt(compressionString[1]);
            matcher.appendReplacement(stringBuilder,
                    stringBuilder.substring(matcher.end(), matcher.end() + range).repeat(repeats - 1));
            stringBuilder.replace(matcher.start(), matcher.end(), "");
            matcher = pattern.matcher(stringBuilder);
        }
        return stringBuilder.length();
    }

    private static long decompressFile(String string) {
        long counter = 0;
        StringBuilder stringBuilder = new StringBuilder(string);
        Pattern pattern = Pattern.compile("\\([0-9]+x[0-9]+\\)");
        Matcher matcher = pattern.matcher(stringBuilder);
        while (matcher.find()) {
            String[] compressionString = stringBuilder.substring(matcher.start() + 1, matcher.end() - 1).split("x");
            int range = Integer.parseInt(compressionString[0]);
            int repeats = Integer.parseInt(compressionString[1]);
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