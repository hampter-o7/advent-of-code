package year2025.day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        ArrayList<Device> allDevices = new ArrayList<>();
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            parseInput(allDevices, myReader.nextLine());
        }
        myReader.close();
        for (Device device : allDevices) {
            if (device.getName().equals("you")) {
                System.out.println(device.getPathsToOut());
                break;
            }
        }
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
}