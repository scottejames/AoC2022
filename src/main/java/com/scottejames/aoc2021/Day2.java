package com.scottejames.aoc2021;

import com.scottejames.aoc.util.AbstractDay;
import com.scottejames.aoc.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Day2 extends AbstractDay {
    enum Direction{
        FORWARD,
        UP,
        DOWN
    }
    List <Pair<Direction,Integer>> instructions = new ArrayList<>();
    public Day2() throws IOException {
        super(2);
        List<String> input = getListString();
        for (String line: input){
            String[] split = line.split(" ");
            String instruction = split[0];
            Integer distance = Integer.valueOf(split[1]);
            switch (instruction) {
                case "forward":
                    instructions.add(new Pair<>(Direction.FORWARD, distance));
                    break;
                case "down":
                    instructions.add(new Pair<>(Direction.DOWN, distance));
                    break;
                case "up":
                    instructions.add(new Pair<>(Direction.UP, distance));
                    break;
            }
        }
    }
    @Override
    public String solvePart1() {

        int depth = 0;
        int horiz = 0;
        for (var i: instructions) {
            switch (i.getLhs()) {
                case UP:
                    depth -= i.getRhs();
                    break;
                case DOWN:
                    depth += i.getRhs();
                    break;
                case FORWARD:
                    horiz += i.getRhs();
                    break;
            }
        }
        return String.valueOf(depth * horiz);
    }

    @Override
    public String solvePart2() {
        int depth = 0;
        int horiz = 0;
        int aim = 0;
        for (var i: instructions) {
            switch (i.getLhs()) {
                case UP:
                    aim -= i.getRhs();
                    break;
                case DOWN:
                    aim += i.getRhs();
                    break;
                case FORWARD:
                    horiz += i.getRhs();
                    depth += (aim * i.getRhs());
                    break;
            }
        }
        return String.valueOf(depth * horiz);    }

}
