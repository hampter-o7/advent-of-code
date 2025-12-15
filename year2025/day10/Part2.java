package year2025.day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Part2 {

    public Part2() throws FileNotFoundException {
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
        String[] split1 = input.split("\\) \\{");
        String[] split2 = split1[0].split("\\] \\(")[1].split("\\) \\(");
        String[] split3 = split1[1].substring(0, split1[1].length() - 1).split(",");
        int[] correctButtonsPressed = new int[split3.length];
        for (int i = 0; i < split3.length; i++) {
            correctButtonsPressed[i] = Integer.parseInt(split3[i]);
        }
        ArrayList<int[]> buttons = new ArrayList<>();
        for (String button : split2) {
            String[] split4 = button.split(",");
            int[] a = new int[split4.length];
            for (int i = 0; i < split4.length; i++) {
                a[i] = Integer.parseInt(split4[i]);
            }
            buttons.add(a);
        }
        return getMinimalButtonsPressed(correctButtonsPressed, buttons);
    }

    private static int getMinimalButtonsPressed(int[] correctButtonsPressed, ArrayList<int[]> buttons) {
        boolean isFinished = true;
        for (int i = 0; i < correctButtonsPressed.length; i++) {
            if (correctButtonsPressed[i] != 0) {
                isFinished = false;
            }
            if (correctButtonsPressed[i] < 0) {
                return -1;
            }
        }
        if (isFinished) {
            return 0;
        }
        boolean[] pattern = createPattern(correctButtonsPressed);
        ArrayList<ArrayList<Integer>> patternSequence = getShortestSequenceOfPattern(pattern, buttons);
        if (patternSequence.isEmpty()) {
            return -1;
        }
        int smallestSum = -1;
        for (ArrayList<Integer> patternSeq : patternSequence) {
            int[] correctButtons = correctButtonsPressed.clone();
            int sum = 0;
            sum += patternSeq.size();
            for (int seq : patternSeq) {
                for (int inc : buttons.get(seq)) {
                    correctButtons[inc]--;
                }
            }
            for (int i = 0; i < correctButtons.length; i++) {
                correctButtons[i] /= 2;
            }
            int a = getMinimalButtonsPressed(correctButtons, buttons);
            if (a != -1) {
                sum += 2 * a;
                smallestSum = smallestSum == -1 ? sum : Math.min(smallestSum, sum);
            }
        }
        return smallestSum;
    }

    private static ArrayList<ArrayList<Integer>> getShortestSequenceOfPattern(boolean[] correctButtonsPressed,
            ArrayList<int[]> buttons) {
        ArrayList<ArrayList<Integer>> solutions = new ArrayList<>();
        Queue<Machine> queue = new LinkedList<>();
        queue.add(new Machine(new boolean[correctButtonsPressed.length], new ArrayList<>()));
        while (!queue.isEmpty()) {
            Machine currentMachine = queue.poll();
            ArrayList<Integer> pressed = currentMachine.getButtonsPressed();
            if (Arrays.equals(correctButtonsPressed, currentMachine.getCurrentState())) {
                solutions.add(pressed);
            }
            for (int i = pressed.isEmpty() ? 0 : pressed.getLast() + 1; i < buttons.size(); i++) {
                boolean[] newState = currentMachine.getCurrentState().clone();
                ArrayList<Integer> newButtonsPressed = new ArrayList<>(pressed);
                for (int button : buttons.get(i)) {
                    newState[button] = !newState[button];
                }
                newButtonsPressed.add(i);
                queue.add(new Machine(newState, newButtonsPressed));
            }
        }
        return solutions;
    }

    private static boolean[] createPattern(int[] correctButtonsPressed) {
        boolean[] pattern = new boolean[correctButtonsPressed.length];
        for (int i = 0; i < pattern.length; i++) {
            pattern[i] = correctButtonsPressed[i] % 2 == 1;
        }
        return pattern;
    }
}