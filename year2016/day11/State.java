package year2016.day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class State {
    private int stepCounter;
    private int[] state;
    private Set<State> connectingStates = new HashSet<>();

    public State(int stateNumber, int[] state) {
        this.stepCounter = stateNumber;
        this.state = state;
    }

    public int getStepCounter() {
        return this.stepCounter;
    }

    public int[] getState() {
        return this.state;
    }

    public Set<State> getConnectingStates() {
        return this.connectingStates;
    }

    public void addConnectingStates(State connectingState) {
        this.connectingStates.add(connectingState);
    }

    public String toString(ArrayList<String> labels) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("State ID: " + stepCounter + "\nConnected states: ");
        for (State state : connectingStates) {
            stringBuilder.append(state.stepCounter + " ");
        }
        stringBuilder.append('\n' + (labels == null ? "" : labels.toString() + "\n "));
        for (int i = 0; i < state.length; i++) {
            stringBuilder.append((state[i] + 1) + "   ");
        }
        stringBuilder.append(" \n");
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return Arrays.equals(this.state, ((State) (obj)).state);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(this.state);
    }

    public boolean isValid() {
        for (int i = 2; i < state.length; i += 2) {
            if (state[i] != state[i - 1]) {
                for (int j = 1; j < state.length; j += 2) {
                    if (i - 1 == j) {
                        continue;
                    }
                    if (state[i] == state[j]) {
                        return false;
                    }
                }
            }
        }
        for (int i = 0; i < state.length; i++) {
            if (state[i] < 0 || state[i] > 3) {
                return false;
            }
        }
        return true;
    }

    public int minimalStepsToEnd() {
        int min = 0;
        int[] floor = new int[4];
        for (int i = 0; i < this.state.length; i++) {
            floor[this.state[i]]++;
        }
        for (int i = 0; i < floor.length - 1; i++) {
            min += (floor[i] / 2 * 2) * (4 - i) + floor[i] % 2;
            floor[i + 1] += floor[i] % 2;
        }
        return min + stepCounter;
    }
}
