package year2025.day11;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Device {
    private String name;
    private ArrayList<Device> connectedDevices = new ArrayList<>();
    private int pathsToOut = -1;
    private long pathsToX = -1;

    public Device(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void addConnectedDevice(Device connectedDevice) {
        connectedDevices.add(connectedDevice);
    }

    public ArrayList<Device> getConnectedDevices() {
        return this.connectedDevices;
    }

    public int getPathsToOut() {
        if (this.name.equals("out")) {
            return 1;
        }
        if (this.pathsToOut != -1) {
            return this.pathsToOut;
        }
        int sum = 0;
        for (Device device : this.connectedDevices) {
            sum += device.getPathsToOut();
        }
        this.pathsToOut = sum;
        return sum;
    }

    public long getPathsToX(String x) {
        if (this.name.equals(x)) {
            return 1;
        }
        if (this.pathsToX != -1) {
            return this.pathsToX;
        }
        long sum = 0;
        for (Device device : this.connectedDevices) {
            sum += device.getPathsToX(x);
        }
        this.pathsToX = sum;
        return sum;
    }

    public void resetPathToX() {
        this.pathsToX = -1;
    }

    @Override
    public String toString() {
        return "Device [name=" + this.name + ", connectedDevices="
                + this.connectedDevices.stream().map(Device::getName).collect(Collectors.joining(", ")) + "]\n";
    }
}
