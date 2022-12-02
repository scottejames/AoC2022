package com.scottejames.aoc2022;

import com.scottejames.aoc.util.AbstractDay;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day2 extends AbstractDay {
    List<String> sample;

    final private Map<String, Integer> rulesOne;
    final private Map<String, Integer> rulesTwo;
    public Day2() throws IOException{
        super(2);
        sample = List.of("A Y",
                "B X",
                "C Z");

        rulesOne = new HashMap<>();
        rulesOne.put("A X", 4); // Draw ROCK       3 + 1
        rulesOne.put("A Y", 8); // WIN             6 + 2
        rulesOne.put("A Z", 3); // LOSE            0 + 3
        rulesOne.put("B X", 1); // LOSE            0 + 1
        rulesOne.put("B Y", 5); // Draw PAPER      3 + 2
        rulesOne.put("B Z", 9); // WIN             6 + 3
        rulesOne.put("C X", 7); // WIN             6 + 1
        rulesOne.put("C Y", 2); // LOSE            0 + 2
        rulesOne.put("C Z", 6); // Draw SCISSORS   3 + 3

        rulesTwo = new HashMap<>();
        rulesTwo.put("A X", 3); // Scissors Lose
        rulesTwo.put("A Y", 4); // Rock Tie
        rulesTwo.put("A Z", 8); // Paper Win
        rulesTwo.put("B X", 1); // Rock Lose
        rulesTwo.put("B Y", 5); // Paper Tie
        rulesTwo.put("B Z", 9); // Scissors Win
        rulesTwo.put("C X", 2); // Paper Loses
        rulesTwo.put("C Y", 6); // Scissors ties
        rulesTwo.put("C Z", 7); // Rock Wins
    }

    @Override
    public String solvePart1() {
        List<String> input = getListString();
        int accum = 0;
        for (String s: input){
            int score = rulesOne.get(s);
            accum += score;
        }
        return String.valueOf(accum);
    }

    @Override
    public String solvePart2() {
        List<String> input = getListString();
        int accum = 0;
        for (String s: input){
            int score = rulesTwo.get(s);
            accum += score;
        }
        return String.valueOf(accum);    }
}
