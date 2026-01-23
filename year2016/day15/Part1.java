package year2016.day15;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        ArrayList<int[]> disks = new ArrayList<>();
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            disks.add(parseInput(myReader.nextLine()));
        }
        myReader.close();
        System.out.println(calculatePerfectTime(disks));
    }

    private static int[] parseInput(String input) {
        String[] split = input.split(" ");
        return new int[] { Integer.parseInt(split[3]),
                Integer.parseInt(split[11].substring(0, split[11].length() - 1)) };
    }

    private static int calculatePerfectTime(ArrayList<int[]> disks) {
        int[] offset = new int[disks.size()];
        int[] period = new int[disks.size()];
        for (int i = 0; i < disks.size(); i++) {
            int[] disk = disks.get(i);
            period[i] = disk[0];
            offset[i] = (disk[1] + i + 1) % disk[0];
        }
        int currentOffset = offset[0];
        int currentPeriod = period[0];
        for (int i = 1; i < disks.size(); i++) {
            int[] lcmPeriod = lcmWithOffset(currentOffset, currentPeriod, offset[i], period[i]);
            currentOffset = lcmPeriod[0];
            currentPeriod = lcmPeriod[1];
        }
        return currentPeriod - currentOffset;
    }

    static int[] lcmWithOffset(int r1, int m1, int r2, int m2) {
        int g = gcd(m1, m2);
        int m3 = m1 / g;
        int m4 = m2 / g;
        int inv = modInverse(m3, m4);
        int t = ((r2 - r1) / g * inv) % m4;
        if (t < 0)
            t += m4;

        int x = r1 + m1 * t;
        int period = lcm(m1, m2);
        return new int[] { x % period, period };
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    static int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }

    static int modInverse(int a, int m) {
        int[] vals = extendedGCD(a, m);
        int x = vals[1];
        return (x % m + m) % m;
    }

    static int[] extendedGCD(int a, int b) {
        if (a == 0)
            return new int[] { b, 0, 1 };
        int[] vals = extendedGCD(b % a, a);
        int g = vals[0], x = vals[2] - (b / a) * vals[1], y = vals[1];
        return new int[] { g, x, y };
    }
}