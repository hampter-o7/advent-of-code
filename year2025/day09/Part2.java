package year2025.day09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import helperFunctions.Pair;

public class Part2 {

    public Part2() throws FileNotFoundException {
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
        ArrayList<long[]> allSquares = new ArrayList<>();
        for (int i = 0; i < redSquares.size(); i++) {
            Pair<Integer, Integer> square1 = redSquares.get(i);
            for (int j = i + 1; j < redSquares.size(); j++) {
                Pair<Integer, Integer> square2 = redSquares.get(j);
                allSquares.add(new long[] { (long) (Math.abs(square1.getLeft() - square2.getLeft()) + 1)
                        * (long) (Math.abs(square1.getRight() - square2.getRight()) + 1),
                        Math.min(square1.getLeft(), square2.getLeft()),
                        Math.max(square1.getLeft(), square2.getLeft()),
                        Math.min(square1.getRight(), square2.getRight()),
                        Math.max(square1.getRight(), square2.getRight()) });
            }
        }
        Pair<Integer, Integer> lastSquare = redSquares.getLast();
        ArrayList<Pair<Integer, Integer>> newAdditions = new ArrayList<>();
        for (int i = 0; i < redSquares.size(); i++) {
            Pair<Integer, Integer> square = redSquares.get(i);
            boolean isSameX = lastSquare.getLeft() == square.getLeft();
            int diff = Math
                    .abs(isSameX ? lastSquare.getRight() - square.getRight() : lastSquare.getLeft() - square.getLeft());
            while (diff > 1000) {
                diff -= 1000;
                newAdditions.add(new Pair<Integer, Integer>(
                        isSameX ? square.getLeft() : Math.max(square.getLeft(), lastSquare.getLeft()) - diff,
                        isSameX ? Math.max(square.getRight(), lastSquare.getRight()) - diff : square.getRight()));
            }
            lastSquare = square;
        }
        redSquares.addAll(newAdditions);
        allSquares.sort((a, b) -> Long.compare(b[0], a[0]));
        outside: for (long[] square : allSquares) {
            for (Pair<Integer, Integer> redSquare : redSquares) {
                if (square[1] < redSquare.getLeft() && square[2] > redSquare.getLeft()
                        && square[3] < redSquare.getRight() && square[4] > redSquare.getRight()) {
                    continue outside;
                }
            }
            return square[0];
        }
        return -1;
    }
}