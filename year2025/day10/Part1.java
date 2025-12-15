package year2025.day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Part1 {

    public Part1() throws FileNotFoundException {
        int counter = 0;
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            counter += getShortestSequence(myReader.nextLine());
        }
        myReader.close();
        System.out.println(counter);
    }

    private static int getShortestSequence(String input) {
        String[] split1 = input.split("\\] \\(");
        String[] split2 = split1[1].split("\\) \\{")[0].split("\\) \\(");
        boolean[] correctButtonsPressed = new boolean[split1[0].length() - 1];
        for (int i = 1; i < split1[0].length(); i++) {
            correctButtonsPressed[i - 1] = split1[0].charAt(i) == '#';
        }
        ArrayList<int[]> buttons = new ArrayList<>();
        for (String button : split2) {
            String[] split3 = button.split(",");
            int[] a = new int[split3.length];
            for (int i = 0; i < split3.length; i++) {
                a[i] = Integer.parseInt(split3[i]);
            }
            buttons.add(a);
        }
        Set<Machine> visited = new HashSet<>();
        Queue<Machine> queue = new LinkedList<>();
        queue.add(new Machine(new boolean[correctButtonsPressed.length], new ArrayList<>()));
        while (!queue.isEmpty()) {
            Machine currentMachine = queue.poll();
            boolean[] currentState = currentMachine.getCurrentState();
            if (visited.contains(currentMachine)) {
                continue;
            }
            visited.add(currentMachine);
            boolean isCorrect = true;
            for (int i = 0; i < correctButtonsPressed.length; i++) {
                if (correctButtonsPressed[i] != currentState[i]) {
                    isCorrect = false;
                    break;
                }
            }
            if (isCorrect) {
                return currentMachine.getButtonsPressed().size();
            }
            for (int i = 0; i < buttons.size(); i++) {
                boolean[] newState = currentMachine.getCurrentState().clone();
                for (int stateChange : buttons.get(i)) {
                    newState[stateChange] = !newState[stateChange];
                }
                ArrayList<Integer> newButtonsPressed = new ArrayList<>();
                newButtonsPressed.addAll(currentMachine.getButtonsPressed());
                newButtonsPressed.add(i);
                queue.add(new Machine(newState, newButtonsPressed));
            }
        }
        return -1;
    }
}