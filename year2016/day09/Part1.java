package year2016.day09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        System.out.println(decompressFile(myReader.nextLine()));
        myReader.close();
    }

    private static long decompressFile(String string) {
        StringBuilder stringBuilder = new StringBuilder(string);
        Pattern pattern = Pattern.compile("\\([0-9]+x[0-9]+\\)");
        Matcher matcher = pattern.matcher(stringBuilder);
        while (matcher.find()) {
            String[] compressionString = stringBuilder.substring(matcher.start() + 1, matcher.end() - 1).split("x");
            int range = Integer.parseInt(compressionString[0]);
            int repeats = Integer.parseInt(compressionString[1]);
            stringBuilder.replace(matcher.start(), matcher.end(),
                    stringBuilder.substring(matcher.end(), matcher.end() + range).repeat(repeats - 1));
            matcher = pattern.matcher(stringBuilder).region(
                    matcher.end() + range * repeats - (matcher.end() - matcher.start()), stringBuilder.length());
        }
        return stringBuilder.length();
    }
}