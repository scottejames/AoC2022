package com.scottejames.aoc2022;

import com.scottejames.aoc.util.AbstractDay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day1 extends AbstractDay {
    List<String> sample;


    public Day1() throws IOException {
        super(1);
        sample = List.of("1000",
                "2000",
                "3000",
                "",
                "4000",
                "",
                "5000",
                "6000",
                "",
                "7000",
                "8000",
                "9000",
                "",
                "10000");
    }

    public List<Integer> parse(List<String> input){
        List<Integer> cals = new ArrayList<>();

        int currCal = 0;
        for (String s: input){
            if (s.length() == 0){
                cals.add(currCal);
                currCal = 0;
            }else {
                int i = Integer.parseInt(s);
                currCal += i;
            }
        }
        Collections.sort(cals,Collections.reverseOrder());
        return cals;
    }
    @Override
    public String solvePart1() {
        List<String> input = getListString();
        List<Integer> cals = parse(input);
        return String.valueOf(cals.get(0));

    }
    @Override
    public String solvePart2() {
        List<String> input = getListString();
        List<Integer> cals = parse(input);
        return String.valueOf(cals.get(0) + cals.get(1) + cals.get(2));
    }
}
