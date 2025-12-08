package year2016.day14;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import helperFunctions.Pair;

public class Part2 {

    public Part2() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        String salt = myReader.nextLine();
        myReader.close();
        System.out.println(find64thKey(salt));
    }

    private static int find64thKey(String salt) {
        int finalIndex = -1;
        ArrayList<Pair<Integer, Character>> possibleKeys = new ArrayList<>();
        ArrayList<Integer> keys = new ArrayList<>();
        for (int i = 0; finalIndex == -1 || i <= finalIndex; i++) {
            String hash = getMD5Hash(salt + i);
            for (int j = 0; j < 2016; j++) {
                hash = getMD5Hash(hash);
            }
            char a = hasNCharsInARow(hash, 3);
            if (a == ' ') {
                continue;
            }
            ArrayList<Integer> removeIndexes = new ArrayList<>();
            for (Pair<Integer, Character> possibleKey : possibleKeys) {
                if (i - possibleKey.getLeft() <= 1000 && hash.contains((possibleKey.getRight() + "").repeat(5))) {
                    keys.add(possibleKey.getLeft());
                    removeIndexes.add(possibleKeys.indexOf(possibleKey));
                    if (keys.size() >= 64) {
                        if (finalIndex == -1) {
                            finalIndex = i + 1000;
                        }
                    }
                }
            }
            for (int removeIndex : removeIndexes.reversed()) {
                possibleKeys.remove(removeIndex);
            }
            possibleKeys.add(new Pair<Integer, Character>(i, a));
        }
        keys.sort((a, b) -> a - b);
        return keys.get(63);
    }

    private static String getMD5Hash(String key) {
        byte[] bytesOfMessage;
        byte[] theMD5digest = null;
        try {
            bytesOfMessage = (key).getBytes("UTF-8");
            theMD5digest = MessageDigest.getInstance("MD5").digest(bytesOfMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        BigInteger bigInt = new BigInteger(1, theMD5digest);
        String hash = bigInt.toString(16);
        while (hash.length() != 32) {
            hash = '0' + hash;
        }
        return hash;
    }

    private static char hasNCharsInARow(String key, int n) {
        Matcher matcher = Pattern.compile("(.)" + "\\1".repeat(n - 1)).matcher(key);
        if (!matcher.find()) {
            return ' ';
        }
        return key.charAt(matcher.start());
    }
}