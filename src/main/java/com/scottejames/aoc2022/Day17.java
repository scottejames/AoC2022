package com.scottejames.aoc2022;

import com.scottejames.aoc.util.AbstractDay;

import java.io.IOException;

public class Day17 extends AbstractDay {
    String sample = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>";
    Integer[][][] shapes = new Integer[][][] {
            { {0, 0}, {1, 0}, {2, 0}, {3, 0} },
            { {1, 0}, {1, 1}, {0, 1}, {2, 1}, {1, 2} },
            { {2, 2}, {2, 1}, {2, 0}, {1, 0}, {0, 0} },
            { {0, 0}, {0, 1}, {0, 2}, {0, 3} },
            { {0, 0}, {0, 1}, {1, 0}, {1, 1} }
    };
    public Day17() throws IOException{
        super(17);

    }
    @Override
    public String solvePart1() {
        return null;
    }

    @Override
    public String solvePart2() {
        return null;
    }
}
