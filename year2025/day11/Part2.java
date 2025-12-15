package year2025.day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part2 {

    public Part2() throws FileNotFoundException {
        ArrayList<Device> allDevices = new ArrayList<>();
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            parseInput(allDevices, myReader.nextLine());
        }
        myReader.close();
        System.out.println(getAllPaths(allDevices));
    }

    private static void parseInput(ArrayList<Device> allDevices, String input) {
        String deviceName = input.split(": ")[0];
        String[] connectedDevices = input.split(": ")[1].split(" ");
        Device device = getDeviceByName(allDevices, deviceName);
        for (int i = 0; i < connectedDevices.length; i++) {
            device.addConnectedDevice(getDeviceByName(allDevices, connectedDevices[i]));
        }
    }

    private static Device getDeviceByName(ArrayList<Device> allDevices, String name) {
        for (Device device : allDevices) {
            if (device.getName().equals(name)) {
                return device;
            }
        }
        Device newDevice = new Device(name);
        allDevices.add(newDevice);
        return newDevice;
    }

    private static long getAllPaths(ArrayList<Device> allDevices) {
        Device[] importantDevices = new Device[4];
        for (Device device : allDevices) {
            if (device.getName().equals("svr")) {
                importantDevices[0] = device;
            } else if (device.getName().equals("fft")) {
                importantDevices[1] = device;
            } else if (device.getName().equals("dac")) {
                importantDevices[2] = device;
            } else if (device.getName().equals("out")) {
                importantDevices[3] = device;
            }
        }
        long paths12 = importantDevices[1].getPathsToX(importantDevices[2].getName());
        resetPathToX(allDevices);
        long paths21 = importantDevices[2].getPathsToX(importantDevices[1].getName());
        if (paths12 != 0) {
            resetPathToX(allDevices);
            long paths01 = importantDevices[0].getPathsToX(importantDevices[1].getName());
            resetPathToX(allDevices);
            long paths23 = importantDevices[2].getPathsToX(importantDevices[3].getName());
            return paths01 * paths12 * paths23;
        }
        resetPathToX(allDevices);
        long paths02 = importantDevices[0].getPathsToX(importantDevices[2].getName());
        resetPathToX(allDevices);
        long paths13 = importantDevices[1].getPathsToX(importantDevices[3].getName());
        return paths02 * paths21 * paths13;
    }

    private static void resetPathToX(ArrayList<Device> allDevices) {
        for (Device device : allDevices) {
            device.resetPathToX();
        }
    }
}