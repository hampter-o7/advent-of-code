package year2016.day13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Part2 {

    public Part2() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        int favoriteNumber = Integer.parseInt(myReader.nextLine());
        myReader.close();
        System.out.println(findNumberOfSpotsInFifty(favoriteNumber));
    }

    private static int findNumberOfSpotsInFifty(int favoriteNumber) {
        ArrayList<String> visited = new ArrayList<>();
        Queue<int[]> priorityQueue = new LinkedList<>();
        priorityQueue.offer(new int[] { 1, 1, 0 });
        while (!priorityQueue.isEmpty()) {
            int[] currentPosition = priorityQueue.poll();
            if (visited.contains(currentPosition[0] + "," + currentPosition[1])
                    || currentPosition[0] < 0 || currentPosition[1] < 0 || currentPosition[2] > 50
                    || !calculateIfSpaceEmpty(favoriteNumber, currentPosition[0], currentPosition[1])) {
                continue;
            }
            visited.add(currentPosition[0] + "," + currentPosition[1]);
            priorityQueue.add(new int[] { currentPosition[0] + 1, currentPosition[1], currentPosition[2] + 1 });
            priorityQueue.add(new int[] { currentPosition[0] - 1, currentPosition[1], currentPosition[2] + 1 });
            priorityQueue.add(new int[] { currentPosition[0], currentPosition[1] + 1, currentPosition[2] + 1 });
            priorityQueue.add(new int[] { currentPosition[0], currentPosition[1] - 1, currentPosition[2] + 1 });
        }
        return visited.size();
    }

    private static boolean calculateIfSpaceEmpty(int favoriteNumber, int x, int y) {
        String number = Integer.toBinaryString(x * x + 3 * x + 2 * x * y + y + y * y + favoriteNumber);
        int counter = 0;
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) == '1') {
                counter++;
            }
        }
        return counter % 2 == 0;
    }
}