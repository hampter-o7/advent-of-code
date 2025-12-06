package year2016.day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class Part1 {

    public Part1() throws FileNotFoundException {
        ArrayList<String> labels = new ArrayList<>();
        labels.add("E");
        ArrayList<Integer> startState = new ArrayList<>();
        startState.add(0);
        setStartState(startState, labels);
        System.out.println(
                findAllStates(new State(0, startState.stream().mapToInt(Integer::intValue).toArray()), labels));
    }

    private static void setStartState(ArrayList<Integer> startState, ArrayList<String> labels)
            throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        for (int i = 0; i < 4; i++) {
            String input = myReader.nextLine();
            String[] split = input.split(",");
            for (int j = 0; j < split.length; j++) {
                String[] split1 = split[j].split(" ");
                if (split1[split1.length - 1].startsWith("microchip")) {
                    String element = split1[split1.length - 2].substring(0, 1).toUpperCase();
                    if (labels.contains(element + "M")) {
                        startState.set(labels.indexOf(element + "M"), i);
                    } else {
                        labels.add(element + "G");
                        labels.add(element + "M");
                        startState.add(i);
                        startState.add(i);
                    }
                } else if (split1[split1.length - 1].startsWith("generator")) {
                    String element = split1[split1.length - 2].substring(0, 1).toUpperCase();
                    if (labels.contains(element + "G")) {
                        startState.set(labels.indexOf(element + "G"), i);
                    } else {
                        labels.add(element + "G");
                        labels.add(element + "M");
                        startState.add(i);
                        startState.add(i);
                    }
                }
            }
        }
        myReader.close();
    }

    private int findAllStates(State startState, ArrayList<String> labels) {
        Set<State> visitedStates = new HashSet<>();
        PriorityQueue<State> queue = new PriorityQueue<>((a, b) -> a.minimalStepsToEnd() - b.minimalStepsToEnd());
        queue.add(startState);
        while (!queue.isEmpty()) {
            State currentState = queue.poll();
            if (visitedStates.contains(currentState)) {
                continue;
            }
            visitedStates.add(currentState);
            if (checkCurrentStateAsFinal(currentState)) {
                return currentState.getStepCounter();
            }
            int[] state = currentState.getState();
            ArrayList<int[]> permutations = getAllPermutations(state);
            for (int[] permutation : permutations) {
                generateState(permutation, 1, currentState, state, visitedStates, labels, queue);
                generateState(permutation, -1, currentState, state, visitedStates, labels, queue);
            }
        }
        return -1;
    }

    private static ArrayList<int[]> getAllPermutations(int[] state) {
        ArrayList<Integer> canMove = new ArrayList<>();
        for (int i = 1; i < state.length; i++) {
            if (state[i] == state[0]) {
                canMove.add(i);
            }
        }
        ArrayList<int[]> permutations = new ArrayList<>();
        for (int i = 0; i < canMove.size(); i++) {
            permutations.add(new int[] { canMove.get(i) });
            for (int j = i + 1; j < canMove.size(); j++) {
                permutations.add(new int[] { canMove.get(i), canMove.get(j) });
            }
        }
        return permutations;
    }

    private static void generateState(int[] permutation, int difference, State currentState, int[] baseState,
            Set<State> visitedStates, ArrayList<String> labels, PriorityQueue<State> queue) {
        int[] newState = baseState.clone();
        newState[0] += difference;
        for (int index : permutation) {
            newState[index] += difference;
        }
        State newPossibleState = new State(currentState.getStepCounter() + 1, newState);
        if (newPossibleState.isValid() && !currentState.getConnectingStates().contains(newPossibleState)) {
            if (visitedStates.contains(newPossibleState)) {
                return;
            }
            currentState.addConnectingStates(newPossibleState);
            newPossibleState.addConnectingStates(currentState);
            queue.add(newPossibleState);
        }
    }

    private static boolean checkCurrentStateAsFinal(State state) {
        for (int a : state.getState()) {
            if (a != 3) {
                return false;
            }
        }
        return true;
    }
}