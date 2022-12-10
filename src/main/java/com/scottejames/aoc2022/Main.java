package com.scottejames.aoc2022;


import com.scottejames.aoc.util.AbstractDay;

import java.io.IOException;

public class Main {

    public static void main(String [] args) throws IOException {
        AbstractDay day = new Day10();

        String solnOne = day.solvePart1();
        String solnTwo = day.solvePart2();

        System.out.println("Part 1: " +  solnOne);
        System.out.println("Part 2: " +  solnTwo);


    }
}