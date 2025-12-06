package year2016.day13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        int favoriteNumber = Integer.parseInt(myReader.nextLine());
        myReader.close();
        System.out.println(findShortestPath(favoriteNumber, 31, 39));
    }

    private static int findShortestPath(int favoriteNumber, int goalX, int goalY) {
        Set<String> visited = new HashSet<>();
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(
                (a, b) -> distanceToGoal(a, goalX, goalY) - distanceToGoal(b, goalX, goalY));
        priorityQueue.offer(new int[] { 1, 1, 0 });
        while (!priorityQueue.isEmpty()) {
            int[] currentPosition = priorityQueue.poll();
            if (currentPosition[0] == goalX && currentPosition[1] == goalY) {
                return currentPosition[2];
            }
            if (visited.contains(currentPosition[0] + "," + currentPosition[1]) || currentPosition[0] < 0
                    || currentPosition[1] < 0) {
                continue;
            }
            visited.add(currentPosition[0] + "," + currentPosition[1]);
            if (!calculateIfSpaceEmpty(favoriteNumber, currentPosition[0], currentPosition[1])) {
                continue;
            }
            priorityQueue.add(new int[] { currentPosition[0] + 1, currentPosition[1], currentPosition[2] + 1 });
            priorityQueue.add(new int[] { currentPosition[0] - 1, currentPosition[1], currentPosition[2] + 1 });
            priorityQueue.add(new int[] { currentPosition[0], currentPosition[1] + 1, currentPosition[2] + 1 });
            priorityQueue.add(new int[] { currentPosition[0], currentPosition[1] - 1, currentPosition[2] + 1 });
        }
        return -1;
    }

    private static int distanceToGoal(int[] position, int goalX, int goalY) {
        return Math.abs(goalY - position[0]) + Math.abs(goalX - position[1]) + position[2];
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