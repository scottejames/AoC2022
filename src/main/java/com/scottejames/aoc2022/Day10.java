package com.scottejames.aoc2022;

import com.scottejames.aoc.util.AbstractDay;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;

public class Day10 extends AbstractDay {
    public HashMap<Integer,Integer> simulation = new HashMap<>();
    public Day10() throws IOException{
        super(10);
        List<String> input = getListString();
        simulate(input);
    }

    @Override
    public String solvePart1() {
        int sum =0;
        for (int i = 20; i <= 220; i += 40) {
            sum+=i * simulation.get(i);
        }
        return String.valueOf(sum);
    }

    @Override
    public String solvePart2() {
        int cycle = 0;
        var rowJoiner = new StringJoiner("\n");

        for (int row = 0; row < 6; ++row) {
            StringBuilder lineBuilder = new StringBuilder();
            for (int column = 0; column <40;column++){
                cycle++;
                int value = simulation.get(cycle);
                if (Math.abs(value-column)<=1)
                    lineBuilder.append("#");
                else
                    lineBuilder.append(" ");
            }
            rowJoiner.add(lineBuilder.toString());
        }

        return "\n" + rowJoiner.toString();
    }

    public void simulate(List<String> input){
        int x = 1;
        int clock = 1;
        for (String line: input){
            if (line.equals("noop")){
                simulation.put(clock++,x);
            } else {
                simulation.put(clock++,x);
                simulation.put(clock++,x);
                x += Integer.parseInt(line.split(" ")[1]);
            }
        }
    }
    private static final String simpleSample = """
            noop
            addx 3
            addx -5
            """;
    private static final String sample = """
addx 15
addx -11
addx 6
addx -3
addx 5
addx -1
addx -8
addx 13
addx 4
noop
addx -1
addx 5
addx -1
addx 5
addx -1
addx 5
addx -1
addx 5
addx -1
addx -35
addx 1
addx 24
addx -19
addx 1
addx 16
addx -11
noop
noop
addx 21
addx -15
noop
noop
addx -3
addx 9
addx 1
addx -3
addx 8
addx 1
addx 5
noop
noop
noop
noop
noop
addx -36
noop
addx 1
addx 7
noop
noop
noop
addx 2
addx 6
noop
noop
noop
noop
noop
addx 1
noop
noop
addx 7
addx 1
noop
addx -13
addx 13
addx 7
noop
addx 1
addx -33
noop
noop
noop
addx 2
noop
noop
noop
addx 8
noop
addx -1
addx 2
addx 1
noop
addx 17
addx -9
addx 1
addx 1
addx -3
addx 11
noop
noop
addx 1
noop
addx 1
noop
noop
addx -13
addx -19
addx 1
addx 3
addx 26
addx -30
addx 12
addx -1
addx 3
addx 1
noop
noop
noop
addx -9
addx 18
addx 1
addx 2
noop
noop
addx 9
noop
noop
noop
addx -1
addx 2
addx -37
addx 1
addx 3
noop
addx 15
addx -21
addx 22
addx -6
addx 1
noop
addx 2
addx 1
noop
addx -10
noop
noop
addx 20
addx 1
addx 2
addx 2
addx -6
addx -11
noop
noop
noop
""";

}