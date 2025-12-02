package year2016.day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        ArrayList<Bot> bots = new ArrayList<>();
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            addNewBots(bots, myReader.nextLine());
        }
        myReader.close();
        System.out.println(getBotComparingNumbers(bots, 61, 17));
    }

    private static void addNewBots(ArrayList<Bot> bots, String instruction) {
        String[] split = instruction.split(" ");
        if (instruction.startsWith("value")) {
            Bot bot = findBotByNumber(bots, Integer.parseInt(split[5]));
            bot.addHeldChip(Integer.parseInt(split[1]));
        } else {
            Bot bot = findBotByNumber(bots, Integer.parseInt(split[1]));
            Bot low = split[5].equals("output") ? new Bot(-Integer.parseInt(split[6]) - 1)
                    : findBotByNumber(bots, Integer.parseInt(split[6]));
            Bot high = split[10].equals("output") ? new Bot(-Integer.parseInt(split[11]) - 1)
                    : findBotByNumber(bots, Integer.parseInt(split[11]));
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

    private static int getBotComparingNumbers(ArrayList<Bot> bots, int num1, int num2) {
        for (Bot bot : bots) {
            bot.distributeChips();
        }
        for (Bot bot : bots) {
            int[] heldChips = bot.getHeldChips();
            if (heldChips[0] == num1 && heldChips[1] == num2 || heldChips[0] == num2 && heldChips[1] == num1) {
                return bot.getBotNumber();
            }
        }
        return -1;
    }
}