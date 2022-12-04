package com.scottejames.aoc2022;

import com.scottejames.aoc.util.AbstractDay;

import java.io.IOException;
import java.util.List;

public class Day4 extends AbstractDay {
    List<String> sample;
    public Day4() throws IOException {
        super(4);

        sample = List.of("2-4,6-8",
                "2-3,4-5",
                "5-7,7-9",
                "2-8,3-7",
                "6-6,4-6",
                "2-6,4-8");

    }

    public Integer[] parse(String s){
        Integer[] result = new Integer[4];
        String lhs = s.split(",")[0];
        String rhs = s.split(",")[1];
        result[0] = Integer.valueOf(lhs.split("-")[0]);
        result[1] = Integer.valueOf(lhs.split("-")[1]);
        result[2] = Integer.valueOf(rhs.split("-")[0]);
        result[3] = Integer.valueOf(rhs.split("-")[1]);
        return result;
    }
    @Override
    public String solvePart1() {
        List<String> input = getListString();
        int result = 0;
        for (String s: input){
            Integer[] data = parse(s);
            if ((data[0] <= data[2] && data[1] >= data[3]) ||
                (data[2] <= data[0] && data[3] >= data[1])){
                result += 1;
            }
        }
        return String.valueOf(result);
    }

    @Override
    public String solvePart2() {
        List<String> input = getListString();
        int result = 0;
        for (String s: input){
            Integer[] data = parse(s);
            if ((data[0] <= data[3]) && (data[2] <= data[1])){
                result += 1;
            }
        }
        return String.valueOf(result);    }
}
