package year2025.day08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        ArrayList<int[]> lights = new ArrayList<>();
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            lights.add(convertStringToIntArray(myReader.nextLine()));
        }
        myReader.close();
        System.out.println(calculateAllPathLengths(lights));
    }

    private static int[] convertStringToIntArray(String input) {
        String[] split = input.split(",");
        int[] coordinates = new int[3];
        for (int i = 0; i < coordinates.length; i++) {
            coordinates[i] = Integer.parseInt(split[i]);
        }
        return coordinates;
    }

    private static int calculateAllPathLengths(ArrayList<int[]> lights) {
        PriorityQueue<double[]> allPathLengths = new PriorityQueue<>((a, b) -> Double.compare(a[0], b[0]));
        for (int i = 0; i < lights.size(); i++) {
            for (int j = i + 1; j < lights.size(); j++) {
                allPathLengths.add(new double[] { calculateDistance(lights.get(i), lights.get(j)), i, j });
            }
        }
        ArrayList<ArrayList<Integer>> groups = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            double[] shortest = allPathLengths.poll();
            int group1 = findGroup(groups, (int) shortest[1]);
            int group2 = findGroup(groups, (int) shortest[2]);
            if (group1 == -1 && group2 == -1) {
                ArrayList<Integer> newGroup = new ArrayList<>();
                newGroup.add((int) shortest[1]);
                newGroup.add((int) shortest[2]);
                groups.add(newGroup);
            } else if (group1 == -1) {
                groups.get(group2).add((int) shortest[1]);
            } else if (group2 == -1) {
                groups.get(group1).add((int) shortest[2]);
            } else if (group1 == group2) {
            } else {
                ArrayList<Integer> newGroup = new ArrayList<>();
                newGroup.addAll(groups.get(group1));
                newGroup.addAll(groups.get(group2));
                if (group2 > group1) {
                    groups.remove(group2);
                    groups.remove(group1);
                } else {
                    groups.remove(group1);
                    groups.remove(group2);
                }
                groups.add(newGroup);
            }
        }
        Collections.sort(groups, (a, b) -> Integer.compare(b.size(), a.size()));
        return groups.get(0).size() * groups.get(1).size() * groups.get(2).size();
    }

    private static double calculateDistance(int[] coords1, int[] coords2) {
        return Math.sqrt(Math.pow(coords1[0] - coords2[0], 2) + Math.pow(coords1[1] - coords2[1], 2)
                + Math.pow(coords1[2] - coords2[2], 2));
    }

    private static int findGroup(ArrayList<ArrayList<Integer>> groups, int index) {
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).contains(index)) {
                return i;
            }
        }
        return -1;
    }
}