package year2016.day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part2 {

    public Part2() throws FileNotFoundException {
        ArrayList<Bot> bots = new ArrayList<>();
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            addNewBots(bots, myReader.nextLine());
        }
        myReader.close();
        System.out.println(getBotComparingNumbers(bots));
    }

    private static void addNewBots(ArrayList<Bot> bots, String instruction) {
        String[] split = instruction.split(" ");
        if (instruction.startsWith("value")) {
            Bot bot = findBotByNumber(bots, Integer.parseInt(split[5]));
            bot.addHeldChip(Integer.parseInt(split[1]));
        } else {
            Bot bot = findBotByNumber(bots, Integer.parseInt(split[1]));
            boolean isFirstOutput = split[5].equals("output");
            Bot low = findBotByNumber(bots,
                    (isFirstOutput ? -1 : 1) * Integer.parseInt(split[6]) - (isFirstOutput ? 1 : 0));
            boolean isSecondOutput = split[10].equals("output");
            Bot high = findBotByNumber(bots,
                    (isSecondOutput ? -1 : 1) * Integer.parseInt(split[11]) - (isSecondOutput ? 1 : 0));
            bot.setLow(low);
            bot.setHigh(high);
        }
    }

    private static Bot findBotByNumber(ArrayList<Bot> bots, int botNumber) {
        for (Bot bot : bots) {
            if (bot.getBotNumber() == botNumber) {
                return bot;
            }
        }
        Bot newBot = new Bot(botNumber);
        bots.add(newBot);
        return newBot;
    }

    private static int getBotComparingNumbers(ArrayList<Bot> bots) {
        for (Bot bot : bots) {
            bot.distributeChips();
        }
        int output = 1;
        for (Bot bot : bots) {
            if (bot.getBotNumber() == -1 || bot.getBotNumber() == -2 || bot.getBotNumber() == -3) {
                output *= bot.getHeldChips()[0];
            }
        }
        return output;
    }
}