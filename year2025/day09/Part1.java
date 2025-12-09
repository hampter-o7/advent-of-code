package year2025.day09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import helperFunctions.Pair;

public class Part1 {

    public Part1() throws FileNotFoundException {
        ArrayList<Pair<Integer, Integer>> redSquares = new ArrayList<>();
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            getRedSquare(redSquares, myReader.nextLine());
        }
        myReader.close();
        System.out.println(getBiggestRedSquare(redSquares));
    }

    private static void getRedSquare(ArrayList<Pair<Integer, Integer>> redSquares, String input) {
        String[] split = input.split(",");
        redSquares.add(new Pair<Integer, Integer>(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
    }

    private static long getBiggestRedSquare(ArrayList<Pair<Integer, Integer>> redSquares) {
        ArrayList<Long> allSquares = new ArrayList<>();
        for (int i = 0; i < redSquares.size(); i++) {
            Pair<Integer, Integer> square1 = redSquares.get(i);
            for (int j = i + 1; j < redSquares.size(); j++) {
                Pair<Integer, Integer> square2 = redSquares.get(j);
                allSquares.add((long) (Math.abs(square1.getLeft() - square2.getLeft()) + 1)
                        * (long) (Math.abs(square1.getRight() - square2.getRight()) + 1));
            }
        }
        allSquares.sort((a, b) -> Long.compare(b, a));
        return allSquares.getFirst();
    }
}