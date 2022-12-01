package com.scottejames.aoc2021;

import com.scottejames.aoc.util.AbstractDay;

import java.io.IOException;
import java.util.List;

public class Day1 extends AbstractDay {

    List<Integer> sample;
    public Day1() throws IOException {
        super(1);
        sample = List.of(199,
                200,
                208,
                210,
                200,
                207,
                240,
                269,
                260,
                263);
    }
    @Override
    public String solvePart1() {
        List<Integer> input = getListInteger();

        Integer prior = Integer.MAX_VALUE;
        Integer count = 0;

        for (Integer i: input){
            if (i> prior) count++;
            prior = i;
        }

        return String.valueOf(count);
    }

    @Override
    public String solvePart2() {
        List<Integer> input = getListInteger();

        Integer prior = Integer.MAX_VALUE;
        Integer count = 0;


        for (int i = 0; i < input.size()-2;i++){
            int curr = input.get(i) + input.get(i+1) + input.get(i+2);

            if (curr > prior) count++;
            prior = curr;
        }

        return String.valueOf(count);
    }
}
