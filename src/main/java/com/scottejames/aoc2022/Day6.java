package com.scottejames.aoc2022;

import com.scottejames.aoc.util.AbstractDay;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day6 extends AbstractDay {

    private String sample;
    public Day6() throws IOException {
        super(6);
        sample = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw";

    }
    public boolean isUnique(String sample){
        Set<Character> set = new HashSet();
        sample.chars().forEach(c->set.add((char)c));
        if (set.size() == sample.length()){
            return true;
        } else {
            return false;
        }
    }
    @Override
    public String solvePart1() {
        String input = getListString().get(0);
        int packetSize = 4;
        for (int i = 0; i< input.length()- packetSize;i++){
            String test = input.substring(i,i+packetSize);
            if (isUnique(test)){
                return String.valueOf(i + packetSize);
            }
        }
        return "End of File";
    }

    @Override
    public String solvePart2() {
        String input = getListString().get(0);
        int packetSize = 14;
        for (int i = 0; i< input.length()- packetSize;i++){
            String test = input.substring(i,i+packetSize);
            if (isUnique(test)){
                return String.valueOf(i + packetSize);
            }
        }
        return "End of File";    }
}
