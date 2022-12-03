package com.scottejames.aoc2022;

import com.scottejames.aoc.util.AbstractDay;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day3 extends AbstractDay {
    List<String> sample;
    public int getPriority(char c){
        if (!Character.isUpperCase(c)){
            return 1 + c - 'a';
        } else {
            return 27 + c - 'A';
        }
    }
    public Day3() throws IOException {
        super(3);
        sample = List.of("vJrwpWtwJgWrhcsFMMfFFhFp",
                "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
                "PmmdzqPrVvPwwTWBwg",
                "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
                "ttgJtRGJQctTZtZT",
                "CrZsJsPPZsGzwwsLwLmpwMDw");
    }
    @Override
    public String solvePart1() {
        List<String> input = getListString();

        int result = 0;
        for (String s: input){
            int length = s.length();
            String lhs = s.substring(0,length /2 );
            String rhs = s.substring(length /2 ,length);

            Set<Character> lhsSet = new HashSet<>();
            Set<Character> rhsSet = new HashSet<>();
            lhs.chars().forEach(c -> lhsSet.add((char)c));
            rhs.chars().forEach(c -> rhsSet.add((char)c));

            lhsSet.retainAll(rhsSet);

            Character c = (Character) lhsSet.toArray()[0];


            result += getPriority(c);
        }

        return String.valueOf(result);
    }

    @Override
    public String solvePart2() {
        List<String> input = getListString();
        int result = 0;

        for (int line = 0; line < input.size(); line+=3){
            String one = input.get(line);
            String two = input.get(line+1);
            String three = input.get(line+2);

            Set<Character> oneSet = new HashSet<>();
            Set<Character> twoSet = new HashSet<>();
            Set<Character> threeSet = new HashSet<>();

            one.chars().forEach(c ->oneSet.add((char)c));
            two.chars().forEach(c ->twoSet.add((char)c));
            three.chars().forEach(c ->threeSet.add((char)c));

            oneSet.retainAll(twoSet);
            oneSet.retainAll(threeSet);

            Character c = (Character) oneSet.toArray()[0];
            result += getPriority(c);

        }
        return String.valueOf(result);
    }
}
