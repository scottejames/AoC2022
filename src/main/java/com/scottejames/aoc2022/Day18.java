package com.scottejames.aoc2022;

import com.scottejames.aoc.util.AbstractDay;
import com.scottejames.aoc.util.Point3d;

import java.io.IOException;
import java.util.ArrayList;

public class Day18 extends AbstractDay {
    public ArrayList<Point3d> cubes;
    public Day18() throws IOException {
        super(18);
        cubes = new ArrayList<>();
        for (String line: sample.split("\n")){
            System.out.println("> " + line);
        }
    }
    @Override
    public String solvePart1() {
        return null;
    }

    @Override
    public String solvePart2() {
        return null;
    }
    static String sample = """
            2,2,2
            1,2,2
            3,2,2
            2,1,2
            2,3,2
            2,2,1
            2,2,3
            2,2,4
            2,2,6
            1,2,5
            3,2,5
            2,1,5
            2,3,5
            """;
}
