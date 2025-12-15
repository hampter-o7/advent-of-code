package year2025.day10;

import java.util.ArrayList;
import java.util.Arrays;

public class Machine {
    private boolean[] currentState;
    private ArrayList<Integer> buttonsPressed;

    public Machine(boolean[] currentState, ArrayList<Integer> buttonsPressed) {
        this.currentState = currentState;
        this.buttonsPressed = buttonsPressed;
    }

    public boolean[] getCurrentState() {
        return this.currentState;
    }

    public ArrayList<Integer> getButtonsPressed() {
        return this.buttonsPressed;
    }

    @Override
    public boolean equals(Object o) {
        return Arrays.equals(this.currentState, ((Machine) o).currentState);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(this.currentState);
    }

    @Override
    public String toString() {
        return "Machine [currentState=" + Arrays.toString(this.currentState) + ", buttonsPressed=" + this.buttonsPressed
                + "]\n";
    }
}
